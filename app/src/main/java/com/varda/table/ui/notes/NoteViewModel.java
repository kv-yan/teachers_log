package com.varda.table.ui.notes;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.varda.table.model.Note;

import java.util.List;

/*public class NoteViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> notes;
    private final NoteDatabaseHelper databaseHelper;

    public NoteViewModel(Context context) {
        notes = new MutableLiveData<>();
        databaseHelper = new NoteDatabaseHelper(context);
        loadNotesFromDatabase();
    }

    private void loadNotesFromDatabase() {
        List<Note> noteList = databaseHelper.getAllNotes();
        notes.setValue(noteList);
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void addNote(String content) {
        long result = databaseHelper.addNote(content);
        if (result != -1) {
            List<Note> noteList = notes.getValue();
            noteList.add(new Note(content));
            notes.setValue(noteList);
        }
    }

    public void updateNote(String oldContent, String newContent) {
        databaseHelper.updateNote(oldContent, newContent);
        List<Note> noteList = notes.getValue();
        for (Note note : noteList) {
            if (note.getContent().equals(oldContent)) {
                note.setContent(newContent);
                break;
            }
        }
        notes.setValue(noteList);
    }

    public void deleteNote(String content) {
        databaseHelper.deleteNote(content);
        List<Note> noteList = notes.getValue();
        Note noteToRemove = null;
        for (Note note : noteList) {
            if (note.getContent().equals(content)) {
                noteToRemove = note;
                break;
            }
        }
        if (noteToRemove != null) {
            noteList.remove(noteToRemove);
            notes.setValue(noteList);
        }
    }
}*/
