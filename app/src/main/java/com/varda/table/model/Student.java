package com.varda.table.model;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private List<Assessment> assessment;
    private String averageGrade;
    private String marks;
    private String parentsEmail;

    public Student(String name, List<Assessment> assessment, String marks, String parentsEmail) {
        this.name = name;
        this.assessment = assessment;
        this.marks = marks;
        this.parentsEmail = parentsEmail;

        float count = 0;
        int validAssessmentCount = 0;
        for (Assessment item : assessment) {
            if (item.getScore().matches("\\d+")) {
                count += Integer.parseInt(item.getScore());
                validAssessmentCount++;
            }
        }

        if (validAssessmentCount > 0) {
            this.averageGrade = String.valueOf(count / validAssessmentCount);
        } else {
            this.averageGrade = "0";
        }
    }

    public Student(String name) {
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

    public List<Assessment> getAssessment() {
        return assessment;
    }


    public int calculateMidAssessment() {
        double total = 0;
        if (assessment != null && !assessment.isEmpty()) {
            for (Assessment str : assessment) {
                if (!str.getScore().isEmpty() && !str.getScore().contains("բ") && !str.getScore().contains("ու")) {
                    total += Double.parseDouble(str.getScore());
                }
            }
            double average = total / assessment.size();

            // Format the average to have only one digit after the decimal point
            DecimalFormat df = new DecimalFormat("#.#");
            this.averageGrade = df.format(average);

            // Return the integer part of the formatted average
            return (int) average;
        } else {
            return 0;
        }
    }


    public int getMissedCount() {
        int total = 0;
        if (assessment != null && !assessment.isEmpty()) {
            for (Assessment str : assessment) {
                if (str.getScore().contains("բ")) total++;
            }
            return total;
        } else {
            return 0;
        }
    }

    public void setAssessment(List<Assessment> assessment) {
        this.assessment = assessment;
    }

    public Assessment getLastAssessment() {
        if (assessment != null && !assessment.isEmpty()) {
            return assessment.get(assessment.size() - 1);
        } else {
            return null;
        }
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

