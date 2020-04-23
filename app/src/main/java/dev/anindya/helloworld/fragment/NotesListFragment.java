package dev.anindya.helloworld.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}
