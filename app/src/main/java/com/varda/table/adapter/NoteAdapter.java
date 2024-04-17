package com.varda.table.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.model.Note;
import com.varda.table.ui.notes.NoteViewModel;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notesList;
    private Context context;
    private NoteViewModel viewModel;

    public NoteAdapter(List<Note> notesList, Context context, NoteViewModel viewModel) {
        this.notesList = notesList;
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.noteContent.setText(note.getContent());

        holder.itemView.setOnLongClickListener(v -> {
            showPopupMenu(note, holder.itemView);
            return true;
        });
    }

    private void showPopupMenu(Note content, View view) {

        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.note_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_edit) {
                showEditNoteDialog(content);
                return true;
            } else if (itemId == R.id.menu_delete) {
                viewModel.deleteNote(content);
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    private void showEditNoteDialog(Note content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Note");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(content.getContent());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            content.setContent(input.getText().toString());
            viewModel.updateNote(content);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotes(List<Note> notes) {
        notesList.clear();
        notesList.addAll(notes);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.text_view_note_content);
        }
    }
}
