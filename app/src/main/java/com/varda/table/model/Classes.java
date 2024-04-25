package com.varda.table.model;

public class Classes {
    private int id;
    private String className;
    private String students;

    public Classes(String className) {
        this.className = className;
        this.students = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}