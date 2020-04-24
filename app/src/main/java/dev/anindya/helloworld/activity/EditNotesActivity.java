package dev.anindya.helloworld.activity;

import static dev.anindya.helloworld.util.Constants.EDIT_NOTES_TAG;
import static dev.anindya.helloworld.util.Constants.NOTE_ID_KEY;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import dev.anindya.helloworld.R;
import dev.anindya.helloworld.fragment.EditNoteFragment;

public class EditNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        Fragment fragment = new EditNoteFragment();
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(NOTE_ID_KEY, -1);
            if (id != -1) {
                Bundle bundle = new Bundle();
                bundle.putInt(NOTE_ID_KEY, id);
                fragment.setArguments(bundle);
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.edit_layout, fragment, EDIT_NOTES_TAG)
                .addToBackStack(null)
                .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        saveNote();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home || itemId == R.id.save_note) {
            saveNote();
            finish();
            return true;
        } else if (itemId == R.id.delete_note) {
            deleteNote();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(EDIT_NOTES_TAG);
        if (fragment instanceof  EditNoteFragment) {
            EditNoteFragment editNoteFragment = (EditNoteFragment) fragment;
            editNoteFragment.deleteNote();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        if (isNewNote()) {
            menu.removeItem(R.id.delete_note);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isNewNote() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(EDIT_NOTES_TAG);
        if (fragment instanceof  EditNoteFragment) {
            EditNoteFragment editNoteFragment = (EditNoteFragment) fragment;
            return editNoteFragment.isNewNote();
        }
        return false;
    }

    private void saveNote() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(EDIT_NOTES_TAG);
        if (fragment instanceof  EditNoteFragment) {
            EditNoteFragment editNoteFragment = (EditNoteFragment) fragment;
            editNoteFragment.saveNote();
        }
    }
}
