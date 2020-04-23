package dev.anindya.helloworld.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.anindya.helloworld.testdata.SampleNotesProvider;

public class Repository {

    private static Repository instance;
    private final List<NoteEntity> mNotes;
    private NotesDatabase mNotesDatabase;

    private Executor mExecutor;

    /**
     * Gets an instance of this {@link Repository}.
     *
     * @param context A {@link Context} for the repository.
     * @return an instance of this {@link Repository}.
     */
    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(NotesDatabase.getInstance(context),
                    SampleNotesProvider.getSampleNotes(),
                    Executors.newSingleThreadExecutor());
        }
        return instance;
    }

    @VisibleForTesting
    Repository(@NonNull final NotesDatabase notesDatabase,
               @NonNull final List<NoteEntity> notes,
               @NonNull final Executor executor) {
        mNotesDatabase = notesDatabase;
        mNotes = notes;
        mExecutor = executor;
    }

    public List<NoteEntity> getNotes() {
        return mNotes;
    }

    /**
     * Add sample data to the repository.
     */
    public void addSampleData() {
        mExecutor.execute(() -> {
            mNotesDatabase.noteDao().insertNotes(mNotes);
            Log.i("repository", "total entries " + mNotesDatabase.noteDao().getCount());
        });


    }
}
