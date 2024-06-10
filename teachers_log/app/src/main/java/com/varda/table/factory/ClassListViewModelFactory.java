package com.varda.table.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.varda.table.ui.classList.ClassListViewModel;
import com.varda.table.ui.classes.ClassesViewModel;


public class ClassListViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public ClassListViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ClassListViewModel.class)) {
            return (T) new ClassListViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
