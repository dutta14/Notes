package dev.anindya.helloworld.database;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dev.anindya.helloworld.testdata.SampleNotesProvider;

@RunWith(AndroidJUnit4.class)
public class NotesDatabaseTest {

    private NotesDatabase notesDatabase;
    private NoteDao noteDao;

    /**
     * Set up the test.
     */
    @Before
    public void setUp()  {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        notesDatabase = Room.inMemoryDatabaseBuilder(context, NotesDatabase.class).build();
        noteDao = notesDatabase.noteDao();
    }

    @After
    public void tearDown() {
        notesDatabase.close();
    }

    @Test
    public void createAndRetrieveNotes() {
        noteDao.insertNotes(SampleNotesProvider.getSampleNotes());
        assertEquals(SampleNotesProvider.getSampleNotes().size(), noteDao.getCount());
    }

    @Test
    public void compareNotes() {
        noteDao.insertNotes(SampleNotesProvider.getSampleNotes());
        NoteEntity noteEntity = noteDao.getNoteById(1);
        assertEquals(SampleNotesProvider.getSampleNotes().get(0).getNoteText(),
                noteEntity.getNoteText());
    }
}