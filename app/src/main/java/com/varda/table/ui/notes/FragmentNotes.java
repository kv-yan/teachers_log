package com.varda.table.ui.notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.varda.table.adapter.NoteAdapter;
import com.varda.table.databinding.FragmentNotesBinding;

public class FragmentNotes extends Fragment {

    private FragmentNotesBinding binding;
    //    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        /*

NoteViewModelFactory factory = new NoteViewModelFactory(requireActivity().getApplication());
        noteViewModel = new ViewModelProvider(this, factory).get(NoteViewModel.class);
        RecyclerView recyclerView = binding.rvNotes;
        adapter = new NoteAdapter(new ArrayList<>(), getContext(), noteViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        noteViewModel.getNotes().observe(getViewLifecycleOwner(), notes -> adapter.setNotes(notes));

        // Handle add note button click
        binding.addNewNote.setOnClickListener(v -> {
            showAddNoteDialog();
        });
*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add Note");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String noteContent = input.getText().toString();
            if (!noteContent.isEmpty()) {
//                noteViewModel.addNote(noteContent);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}

