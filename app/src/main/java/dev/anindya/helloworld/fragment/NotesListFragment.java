package dev.anindya.helloworld.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anindya.helloworld.R;
import dev.anindya.helloworld.testdata.SampleNotesProvider;
import dev.anindya.helloworld.ui.NotesListAdapter;

public class NotesListFragment extends Fragment {

    @BindView(R.id.notes_list)
    RecyclerView mNotesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mNotesList.setHasFixedSize(true);
        mNotesList.setLayoutManager(new LinearLayoutManager(getContext()));
        mNotesList.setAdapter(new NotesListAdapter(SampleNotesProvider.getSampleNotes()));
    }
}
