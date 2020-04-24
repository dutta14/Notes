package dev.anindya.helloworld.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anindya.helloworld.R;
import dev.anindya.helloworld.database.NoteEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotesListAdapter extends Adapter<NotesListAdapter.ViewHolder> {

    private final List<NoteEntity> noteEntities;
    private final EditNoteFragmentHandler editNoteFragmentHandler;

    public NotesListAdapter(List<NoteEntity> noteEntities, FragmentManager fragmentManager) {
        this(noteEntities, new EditNoteFragmentHandler(fragmentManager));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notes_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notesText.setText(noteEntities.get(position).getNoteText());

        holder.notesText.setOnClickListener(v -> {
            int id = noteEntities.get(position).getId();
            editNoteFragmentHandler.openFragmentWithId(id);
        });
    }

    @Override
    public int getItemCount() {
        return noteEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notes_text)
        TextView notesText;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
