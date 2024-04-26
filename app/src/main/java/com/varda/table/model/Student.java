package com.varda.table.model;

import java.util.Collections;
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

    public Student(String name /*List<String> assessment, String averageGrade, String marks, String parentsEmail*/) {
        this.name = name;
        this.assessment = Collections.emptyList();
        this.averageGrade = "";
        this.marks = "";
        this.parentsEmail = "";
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

    public int calculateMidAssessment() {
        double total = 0;
        if (assessment != null && !assessment.isEmpty()) {
            for (String str : assessment) {
                total += Double.parseDouble(str);
            }
            return (int) (total / assessment.size());
        } else {
            return 0;
        }
    }

    public int getMissedCount() {
        int total = 0;
        if (assessment != null && !assessment.isEmpty()) {
            for (String str : assessment) {
                if (str.contains("բ")) total++;
            }
            return total;
        } else {
            return 0;
        }
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

