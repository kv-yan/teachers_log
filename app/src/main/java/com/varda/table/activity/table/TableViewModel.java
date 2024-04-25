package com.varda.table.activity.table;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.varda.table.database.classes.ClassesDatabaseHelper;
import com.varda.table.model.Classes;
import com.varda.table.model.Student;

import java.io.Closeable;
import java.util.List;

public class TableViewModel extends AndroidViewModel {
    public int currentClassId ;

    private ClassesDatabaseHelper databaseHelper;
    public TableViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new ClassesDatabaseHelper(application.getApplicationContext());
    }

    public LiveData<Classes> getClassById(int id) {
        MutableLiveData<Classes> classesLiveData = new MutableLiveData<>();
        Classes classes = databaseHelper.getClass(id);
        classesLiveData.setValue(classes);
        currentClassId = classes.getId();
        return classesLiveData;
    }

    public List<Student> getStudentListFromJson(String students ){
       return databaseHelper.getStudentsListFromJson(students);
    }

    public void addStudentToClass(int classId, Student student) {
        databaseHelper.addStudentToClass(classId, student);
    }
}
