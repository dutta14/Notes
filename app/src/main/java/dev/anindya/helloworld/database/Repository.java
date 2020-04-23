package dev.anindya.helloworld.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.List;

import dev.anindya.helloworld.testdata.SampleNotesProvider;

public class Repository {

    private static Repository instance;
    private final Context mContext;
    private final List<NoteEntity> mNotes;

    /**
     * Gets an instance of this {@link Repository}.
     *
     * @param context A {@link Context} for the repository.
     * @return an instance of this {@link Repository}.
     */
    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context, SampleNotesProvider.getSampleNotes());
        }
        return instance;
    }

    @VisibleForTesting
    Repository(@NonNull final Context context,
               @NonNull  final List<NoteEntity> notes) {
        mContext = context;
        mNotes = notes;
    }

    public List<NoteEntity> getNotes() {
        return mNotes;
    }
}
