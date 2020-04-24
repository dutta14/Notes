package dev.anindya.helloworld;

import static dev.anindya.helloworld.util.Constants.EDIT_NOTES_TAG;
import static dev.anindya.helloworld.util.Constants.NOTES_LIST_TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anindya.helloworld.fragment.EditNoteFragment;
import dev.anindya.helloworld.fragment.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.newNote)
    FloatingActionButton addNoteButton;

    @OnClick(R.id.newNote)
    void onNewNote() {
        Log.i("activity", "onNewNote: Adding new note");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new EditNoteFragment(), EDIT_NOTES_TAG)
                .addToBackStack(EDIT_NOTES_TAG)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new NotesListFragment(), NOTES_LIST_TAG)
                .addToBackStack(NOTES_LIST_TAG)
                .commit();

        ButterKnife.bind(this);
    }
}
