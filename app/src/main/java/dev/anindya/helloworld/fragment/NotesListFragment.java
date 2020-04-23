package dev.anindya.helloworld.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anindya.helloworld.R;
import dev.anindya.helloworld.ui.NotesListAdapter;
import dev.anindya.helloworld.viewmodel.NotesListViewModel;

public class NotesListFragment extends Fragment {

    @BindView(R.id.notes_list)
    RecyclerView mNotesList;

    private NotesListViewModel mViewModel;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        mContext = getContext();
        initViewModel();
        initRecyclerView();
        return view;
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(requireActivity(),
                new NotesListViewModel.Factory(mContext))
                .get(NotesListViewModel.class);
    }

    private void initRecyclerView() {
        mNotesList.setHasFixedSize(true);
        mNotesList.setLayoutManager(new LinearLayoutManager(getContext()));
        mNotesList.setAdapter(new NotesListAdapter(mViewModel.getNotes()));
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
        return true;
    }
}
