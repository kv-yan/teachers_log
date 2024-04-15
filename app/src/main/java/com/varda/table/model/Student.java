package com.varda.table.model;

import java.util.List;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<String> assessment;
    private String averageGrade;
    private String marks;
    private String parentsEmail;

    public Student(String name, List<String> assessment, String averageGrade, String marks, String parentsEmail) {
        this.name = name;
        this.assessment = assessment;
        this.averageGrade = averageGrade;
        this.marks = marks;
        this.parentsEmail = parentsEmail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<String> assessment) {
        this.assessment = assessment;
    }


    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getParentsEmail() {
        return parentsEmail;
    }

    public void setParentsEmail(String parentsEmail) {
        this.parentsEmail = parentsEmail;
    }
}

