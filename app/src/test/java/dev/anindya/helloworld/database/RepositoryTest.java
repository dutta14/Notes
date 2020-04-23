package dev.anindya.helloworld.database;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.concurrent.Executor;

@RunWith(RobolectricTestRunner.class)
public class RepositoryTest {

    Repository repository;
    @Mock
    private NotesDatabase mockNotesDatabase;
    @Mock
    private List<NoteEntity> mockNotes;
    @Mock
    private Executor mockExecutor;
    @Mock
    private NoteDao mockDao;

    @Before
    public void setUp() {
        initMocks(this);
        repository = new Repository(mockNotesDatabase, mockNotes, mockExecutor);
    }

    @Test
    public void getNotes() {
        assertEquals(mockNotes, repository.getNotes());
    }

    @Test
    public void addSampleData() {
        final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
        when(mockNotesDatabase.noteDao()).thenReturn(mockDao);

        repository.addSampleData();
        verify(mockExecutor).execute(runnableCaptor.capture());

        final Runnable runnable = runnableCaptor.getValue();
        runnable.run();

        verify(mockDao).insertNotes(mockNotes);

    }
}