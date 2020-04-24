package dev.anindya.helloworld.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.database.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EditorViewModel extends ViewModel {

    @Getter
    private final MutableLiveData<NoteEntity> liveNoteEntity;
    private final Repository repository;
    private final Executor executor;

    /**
     * Load the data for a note ID.
     *
     * @param noteId The note ID to retrieve the note for.
     */
    public void loadData(int noteId) {
        executor.execute(() -> {
            NoteEntity entity = repository.getNoteById(noteId);
            liveNoteEntity.postValue(entity);
        });
    }

    @AllArgsConstructor
    public static class Factory implements ViewModelProvider.Factory {

        private final Context context;

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new EditorViewModel(new MutableLiveData<>(),
                    Repository.getInstance(context),
                    Executors.newSingleThreadExecutor());
        }
    }
}
