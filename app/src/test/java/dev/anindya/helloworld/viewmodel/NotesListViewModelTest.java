package dev.anindya.helloworld.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.database.Repository;

public class NotesListViewModelTest {

    NotesListViewModel notesListViewModel;
    @Mock
    private Repository mockRepository;
    @Mock
    private List<NoteEntity> mockList;

    @Before
    public void setUp() {
        initMocks(this);
        notesListViewModel = new NotesListViewModel(mockRepository);
    }

    @Test
    public void getNotes() {
        when(mockRepository.getNotes()).thenReturn(mockList);
        List<NoteEntity> notes = notesListViewModel.getNotes();
        assertEquals(mockList, notes);
    }

    @Test
    public void addSampleData() {
        notesListViewModel.addSampleData();
        verify(mockRepository).addSampleData();
    }
}