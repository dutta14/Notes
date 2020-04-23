package dev.anindya.helloworld.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.testdata.SampleNotesProvider;
import lombok.AllArgsConstructor;

public class NotesListViewModel extends ViewModel {

    private final Context mContext;

    public NotesListViewModel(Context context) {
        mContext = context;
    }

    public List<NoteEntity> getNotes() {
        return SampleNotesProvider.getSampleNotes();
    }

    @AllArgsConstructor
    public static class Factory implements ViewModelProvider.Factory {

        private final Context mContext;

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NotesListViewModel(mContext);
        }
    }
}
