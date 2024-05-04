package com.varda.table.ui.notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.varda.table.R;
import com.varda.table.adapter.NoteAdapter;
import com.varda.table.databinding.FragmentNotesBinding;
import com.varda.table.factory.NoteViewModelFactory;

import java.util.ArrayList;

public class FragmentNotes extends Fragment {

    private FragmentNotesBinding binding;
    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NoteViewModelFactory factory = new NoteViewModelFactory(requireActivity().getApplication());
        noteViewModel = new ViewModelProvider(this, factory).get(NoteViewModel.class);

        adapter = new NoteAdapter(new ArrayList<>(), getContext(), noteViewModel);
        binding.rvNotes.setAdapter(adapter);
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(getContext()));

        noteViewModel.getNotes().observe(getViewLifecycleOwner(), notes -> adapter.setNotes(notes));

        binding.addNewNote.setOnClickListener(v -> {
            showAddNoteDialog();
        });

        setActionBarTitle();
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Ավելացնել նշում");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Նշում");
        builder.setView(input);

        builder.setPositiveButton("Պահպանել", (dialog, which) -> {
            String noteContent = input.getText().toString();
            if (!noteContent.isEmpty()) {
                noteViewModel.addNote(noteContent);
            }
        });
        builder.setNegativeButton("Չեղարկել", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void setActionBarTitle() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_notes);
        }
    }
}

