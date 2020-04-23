package dev.anindya.helloworld;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anindya.helloworld.fragment.EditNoteFragment;
import dev.anindya.helloworld.fragment.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.newNote)
    void onNewNote() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new EditNoteFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new NotesListFragment())
                .addToBackStack(null)
                .commit();
    }
}
