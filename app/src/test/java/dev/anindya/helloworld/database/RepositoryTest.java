package dev.anindya.helloworld.database;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.concurrent.Executor;

import dev.anindya.helloworld.testdata.SampleNotesProvider;

@RunWith(RobolectricTestRunner.class)
public class RepositoryTest {

    Repository repository;
    @Mock
    private NotesDatabase mockNotesDatabase;
    @Mock
    private LiveData<List<NoteEntity>> mockNotes;
    @Mock
    private Executor mockExecutor;
    @Mock
    private NoteDao mockDao;

    /**
     * Set up the test.
     */
    @Before
    public void setUp() {
        initMocks(this);
        repository = new Repository(mockNotesDatabase, mockExecutor);
        when(mockNotesDatabase.noteDao()).thenReturn(mockDao);
    }

    @Test
    public void getNotes() {
        when(mockDao.getNotes()).thenReturn(mockNotes);
        assertEquals(mockNotes, repository.getNotes());
    }

    @Test
    public void addSampleData() {
        final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);

        repository.addSampleData();
        verify(mockExecutor).execute(runnableCaptor.capture());

        final Runnable runnable = runnableCaptor.getValue();
        runnable.run();

        ArgumentCaptor<List<NoteEntity>> noteEntitiesCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockDao).insertNotes(noteEntitiesCaptor.capture());

        NoteEntity firstNote = noteEntitiesCaptor.getValue().get(0);
        assertEquals(SampleNotesProvider.getSampleNotes().get(0).getNoteText(),
                firstNote.getNoteText());

    }

    @Test
    public void deleteAllNotes() {
        final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);

        repository.deleteAllNotes();
        verify(mockExecutor).execute(runnableCaptor.capture());

        final Runnable runnable = runnableCaptor.getValue();
        runnable.run();

        verify(mockDao).deleteAll();
    }
}