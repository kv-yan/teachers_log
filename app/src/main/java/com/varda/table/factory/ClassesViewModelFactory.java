package com.varda.table.factory;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.varda.table.ui.classes.ClassesViewModel;

public class ClassesViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;

    public ClassesViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ClassesViewModel.class)) {
            return (T) new ClassesViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
