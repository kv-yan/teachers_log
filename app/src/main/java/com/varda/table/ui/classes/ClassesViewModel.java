package com.varda.table.ui.classes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.varda.table.database.classes.ClassesDatabaseHelper;
import com.varda.table.model.Classes;

// ClassesViewModel.java

import java.util.ArrayList;
import java.util.List;

public class ClassesViewModel extends AndroidViewModel {
    private ClassesDatabaseHelper databaseHelper;

    public ClassesViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new ClassesDatabaseHelper(application.getApplicationContext());
    }

    // Метод для добавления класса в базу данных
    public long addClass(Classes classes) {
       return databaseHelper.addClass(classes);
    }

    public LiveData<Classes> getClassById(int id) {
        MutableLiveData<Classes> classesLiveData = new MutableLiveData<>();
        Classes classes = databaseHelper.getClass(id);
        classesLiveData.setValue(classes);
        return classesLiveData;
    }

    public LiveData<List<Classes>> getAllClasses() {
        MutableLiveData<List<Classes>> allClassesLiveData = new MutableLiveData<>();
        List<Classes> allClasses = new ArrayList<>();

        allClasses = databaseHelper.getAllClasses();
        allClassesLiveData.setValue(allClasses);
        return allClassesLiveData;
    }
}

