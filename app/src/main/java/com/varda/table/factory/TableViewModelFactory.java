package com.varda.table.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.varda.table.activity.table.TableViewModel;

//import com.varda.table.ui.notes.NoteViewModel;

public class TableViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public TableViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TableViewModel.class)) {
            return (T) new TableViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
