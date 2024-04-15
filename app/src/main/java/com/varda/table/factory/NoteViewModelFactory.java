package com.varda.table.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//import com.varda.table.ui.notes.NoteViewModel;

public class NoteViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public NoteViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (false/*modelClass.isAssignableFrom(NoteViewModel.class)*/) {
//            return (T) new NoteViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
