package com.varda.table.activity.table;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.varda.table.database.classes.ClassesDatabaseHelper;
import com.varda.table.model.Assessment;
import com.varda.table.model.Classes;
import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TableViewModel extends AndroidViewModel {
    public int currentClassId;
    public MutableLiveData<Classes> currentClass = new MutableLiveData<>();
    public List<Student> excellentStudents = new ArrayList<>();
    public List<Student> percussiveStudents = new ArrayList<>();
    public List<Student> lazyStudents = new ArrayList<>();

    private ClassesDatabaseHelper databaseHelper;

    public TableViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new ClassesDatabaseHelper(application.getApplicationContext());
    }

    public LiveData<Classes> getClassById(int id) {
        MutableLiveData<Classes> classesLiveData = new MutableLiveData<>();
        Classes classes = databaseHelper.getClass(id);
        currentClass.setValue(classes);
        classesLiveData.setValue(classes);
        currentClassId = classes.getId();
        return classesLiveData;
    }

    public List<Student> getStudentListFromJson(String students) {
        return databaseHelper.getStudentsListFromJson(students);
    }

    public void addStudentToClass(int classId, Student student) {
        databaseHelper.addStudentToClass(classId, student);
    }

    public void saveAssessment(Student student) {
        List<Student> studentList = getStudentListFromJson(getClassById(currentClassId).getValue().getStudents());

        for (Student stItem : studentList) {
            if (Objects.equals(stItem.getParentsEmail(), student.getParentsEmail())) {
                stItem.setAssessment(student.getAssessment());
            }
        }

        databaseHelper.updateClassContent(currentClassId, studentList);
    }

    public void addNewDay(String dayOf) {
        List<Student> studentList = getStudentListFromJson(getClassById(currentClassId).getValue().getStudents());

        for (Student student : studentList) {
            student.getAssessment().add(new Assessment(dayOf, ""));
        }

        databaseHelper.updateClassContent(currentClassId,studentList);
    }

}
