package dev.anindya.helloworld.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.database.Repository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotesListViewModel extends ViewModel {

    private final Repository repository;

    public List<NoteEntity> getNotes() {
        return repository.getNotes();
    }

    public void addSampleData() {
        repository.addSampleData();
    }

    @AllArgsConstructor
    public static class Factory implements ViewModelProvider.Factory {

        private final Context context;

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NotesListViewModel(Repository.getInstance(context));
        }
    }
}
