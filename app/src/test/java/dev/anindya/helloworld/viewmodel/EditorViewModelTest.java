package dev.anindya.helloworld.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.concurrent.Executor;

import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.database.Repository;

public class EditorViewModelTest {

    private static final int TEST_ID = 1;
    private static final String TEST_TEXT = "test_text";
    private EditorViewModel editorViewModel;
    @Mock
    private MutableLiveData<NoteEntity> mockLiveNoteEntity;
    @Mock
    private Repository mockRepository;
    @Mock
    private Executor mockExecutor;
    @Mock
    private NoteEntity mockNoteEntity;

    @Before
    public void setUp() {
        initMocks(this);
        editorViewModel = new EditorViewModel(mockLiveNoteEntity, mockRepository, mockExecutor);
    }

    @Test
    public void loadData() {
        when(mockRepository.getNoteById(TEST_ID)).thenReturn(mockNoteEntity);
        editorViewModel.loadData(TEST_ID);

        final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutor).execute(runnableCaptor.capture());

        final Runnable runnable = runnableCaptor.getValue();
        runnable.run();
        verify(mockRepository).getNoteById(TEST_ID);
        verify(mockLiveNoteEntity).postValue(mockNoteEntity);
    }

    @Test
    public void getLiveNoteEntity() {
        assertEquals(mockLiveNoteEntity, editorViewModel.getLiveNoteEntity());
    }

    @Test
    public void saveExistingNote() {
        when(mockLiveNoteEntity.getValue()).thenReturn(mockNoteEntity);
        editorViewModel.saveNote(TEST_TEXT);
        verify(mockNoteEntity).setNoteText(TEST_TEXT.trim());
        verify(mockRepository).insertNote(mockNoteEntity);
    }
}
