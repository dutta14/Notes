package dev.anindya.helloworld.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anindya.helloworld.R;
import dev.anindya.helloworld.util.Constants;
import dev.anindya.helloworld.viewmodel.EditorViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends Fragment {

    private EditorViewModel editorViewModel;
    private Context context;

    @BindView(R.id.note_editor)
    TextView noteEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        ButterKnife.bind(this, view);
        initViewModel();
        return view;
    }

    private void initViewModel() {
        editorViewModel = new ViewModelProvider(requireActivity(),
                new EditorViewModel.Factory(context))
                .get(EditorViewModel.class);

        editorViewModel.getLiveNoteEntity().observe(this,
            noteEntity -> noteEditor.setText(noteEntity.getNoteText()));

        final Bundle arguments = getArguments();
        if (arguments != null) {
            final int noteId = arguments.getInt(Constants.NOTE_ID_KEY);
            editorViewModel.loadData(noteId);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.save_note) {
            saveNote();
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        saveNote();
    }

    private void saveNote() {
        editorViewModel.saveNote(noteEditor.getText().toString());
    }
}
