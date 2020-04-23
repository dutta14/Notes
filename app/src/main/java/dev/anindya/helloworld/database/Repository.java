package dev.anindya.helloworld.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.anindya.helloworld.testdata.SampleNotesProvider;

public class Repository {

    private static Repository instance;
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
                    Executors.newSingleThreadExecutor());
        }
        return instance;
    }

    @VisibleForTesting
    Repository(@NonNull final NotesDatabase notesDatabase,
               @NonNull final Executor executor) {
        mNotesDatabase = notesDatabase;
        mExecutor = executor;
    }

    public LiveData<List<NoteEntity>> getNotes() {
        return mNotesDatabase.noteDao().getNotes();
    }

    /**
     * Add sample data to the repository.
     */
    public void addSampleData() {
        mExecutor.execute(() -> {
            mNotesDatabase.noteDao().insertNotes(SampleNotesProvider.getSampleNotes());
            Log.i("repository", "total entries " + mNotesDatabase.noteDao().getCount());
        });
    }
}
