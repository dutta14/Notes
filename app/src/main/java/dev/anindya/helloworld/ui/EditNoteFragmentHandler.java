package dev.anindya.helloworld.ui;

import static dev.anindya.helloworld.util.Constants.NOTE_ID_KEY;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import dev.anindya.helloworld.R;
import dev.anindya.helloworld.fragment.EditNoteFragment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class EditNoteFragmentHandler {

    private FragmentManager fragmentManager;

    void openFragmentWithId(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(NOTE_ID_KEY, id);
        Fragment editNoteFragment = new EditNoteFragment();
        editNoteFragment.setArguments(arguments);

        fragmentManager
                .beginTransaction()
                .replace(R.id.main_layout, editNoteFragment)
                .addToBackStack(null)
                .commit();
    }
}
