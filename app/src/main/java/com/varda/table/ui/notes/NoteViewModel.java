package com.varda.table.ui.notes;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.varda.table.database.note.NoteDatabaseHelper;
import com.varda.table.model.Note;

import java.util.List;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> notes;
    private final NoteDatabaseHelper databaseHelper;

    public NoteViewModel(Context context) {
        notes = new MutableLiveData<>();
        databaseHelper = new NoteDatabaseHelper(context);
        loadNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    private void loadNotes() {
        List<Note> notesList = databaseHelper.getAllNotes();
        notes.setValue(notesList);
    }

    public void addNote(String content) {
        databaseHelper.addNote(content);
        loadNotes();
    }

    public void updateNote(Note note) {
        databaseHelper.updateNote(note);
        loadNotes();
    }

    public void deleteNote(Note note) {
        databaseHelper.deleteNote(note);
        loadNotes();
    }
}

