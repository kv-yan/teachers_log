package com.varda.table.model;

public class Classes {
    private int id;
    private String className;
    private String students;

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public Classes(String className) {
        this.className = className;
        this.students = "";
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(int anInt) {
        this.id = anInt;
    }
    public void getId(int anInt) {

    }
}