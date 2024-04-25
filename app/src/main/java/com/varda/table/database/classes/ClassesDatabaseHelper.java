package com.varda.table.database.classes;// DatabaseHelper.java

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.varda.table.model.Classes;
import com.varda.table.model.Student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "school_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CLASSES = "classes";
    private static final String KEY_CLASS_ID = "id";
    private static final String KEY_CLASS_NAME = "class_name";
    private static final String KEY_STUDENTS = "students";

    public ClassesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASSES_TABLE = "CREATE TABLE " + TABLE_CLASSES + "(" + KEY_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CLASS_NAME + " TEXT," + KEY_STUDENTS + " TEXT" + ")";
        db.execSQL(CREATE_CLASSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long addClass(Classes classes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_NAME, classes.getClassName());
        values.put(KEY_STUDENTS, classes.getStudents());
        long id = db.insert(TABLE_CLASSES, null, values);
        db.close();
        return id;
    }

    public Classes getClass(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLASSES, new String[]{KEY_CLASS_ID, KEY_CLASS_NAME, KEY_STUDENTS}, KEY_CLASS_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Classes classes = new Classes("");
        assert cursor != null;
        classes.setClassName(cursor.getString(cursor.getColumnIndex(KEY_CLASS_NAME)));
        classes.setStudents(cursor.getString(cursor.getColumnIndex(KEY_STUDENTS)));
        classes.setId(cursor.getInt(0));
        cursor.close();
        return classes;
    }


    public void deleteClass(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASSES, KEY_CLASS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Classes> getAllClasses() {
        List<Classes> classesList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CLASSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Classes classes = new Classes("");
                classes.setClassName(cursor.getString(cursor.getColumnIndex(KEY_CLASS_NAME)));
                classes.setStudents(cursor.getString(cursor.getColumnIndex(KEY_STUDENTS)));
                classes.setId(cursor.getInt(0));
                classesList.add(classes);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return classesList;
    }

    public void addStudentToClass(int classId, Student student) {
        Classes classes = getClass(classId);
        List<Student> students = getStudentsListFromJson(classes.getStudents());
        students.add(student);
        updateClassContent(classId, students);
    }

    public List<Student> getStudentsListFromJson(String json) {
       if (json.isEmpty()){
           return new ArrayList<>();
       }else {
           Gson gson = new Gson();
           Type studentListType = new TypeToken<List<Student>>(){}.getType();
           return gson.fromJson(json, studentListType);
       }
    }


    public void updateClassContent(int id, List<Student> students) {
        Gson gson = new Gson();
        Type studentListType = new TypeToken<List<Student>>() {
        }.getType();
        String studentsJson = gson.toJson(students, studentListType);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENTS, studentsJson);
        db.update(TABLE_CLASSES, values, KEY_CLASS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
