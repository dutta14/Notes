package dev.anindya.helloworld.viewmodel;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;
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

    /**
     * Saves a note.
     *
     * @param text The text to save a note.
     */
    public void saveNote(String text) {
        NoteEntity note = liveNoteEntity.getValue();
        if (note == null) {
            if (TextUtils.isEmpty(text.trim())) {
                return;
            }
            note = new NoteEntity(new Date(), text.trim());
        } else {
            note.setNoteText(text.trim());
        }
        repository.insertNote(note);
    }

    public void deleteNote() {
        NoteEntity note = liveNoteEntity.getValue();
        repository.deleteNote(note);
    }

    public boolean isNewNote() {
        return liveNoteEntity.getValue() == null;
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
