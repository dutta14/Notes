package dev.anindya.helloworld.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anindya.helloworld.R;
import dev.anindya.helloworld.database.NoteEntity;
import dev.anindya.helloworld.ui.NotesListAdapter;
import dev.anindya.helloworld.viewmodel.NotesListViewModel;

public class NotesListFragment extends Fragment {

    @BindView(R.id.notes_list)
    RecyclerView mNotesList;

    private NotesListViewModel mViewModel;
    private Context mContext;
    private List<NoteEntity> notesData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        mContext = getContext();
        notesData = new ArrayList<>();
        initRecyclerView();
        initViewModel();
        return view;
    }

    private void initViewModel() {
        Observer<List<NoteEntity>> observer = noteEntities -> {
            notesData.clear();
            notesData.addAll(noteEntities);
            if (mNotesList.getAdapter() == null) {
                mNotesList.setAdapter(new NotesListAdapter(notesData,
                        getActivity().getSupportFragmentManager()));
            } else {
                mNotesList.getAdapter().notifyDataSetChanged();
            }
        };

        mViewModel = new ViewModelProvider(requireActivity(),
                new NotesListViewModel.Factory(mContext))
                .get(NotesListViewModel.class);

        mViewModel.getNotes().observe(this, observer);
    }

    private void initRecyclerView() {
        mNotesList.setHasFixedSize(true);
        mNotesList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_sample_data) {
            Toast.makeText(getContext(), "Adding data", Toast.LENGTH_SHORT).show();
            mViewModel.addSampleData();
            return true;
        }
        if (id == R.id.delete_all) {
            mViewModel.deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
