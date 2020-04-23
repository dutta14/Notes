package dev.anindya.helloworld.database;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class RepositoryTest {

    Repository repository;
    @Mock
    private Context mockContext;
    @Mock
    private List<NoteEntity> mockNotes;

    @Before
    public void setUp() {
        initMocks(this);
        repository = new Repository(mockContext, mockNotes);
    }

    @Test
    public void getNotes() {
        assertEquals(mockNotes, repository.getNotes());
    }
}