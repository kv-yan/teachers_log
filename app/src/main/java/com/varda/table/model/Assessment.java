package com.varda.table.model;

public class Assessment {

    public Assessment(String dayOf, String score) {
        this.dayOf = dayOf;
        this.score = score;
    }

    public String dayOf;
    public String score;

    public String getDayOf() {
        return dayOf;
    }

    public void setDayOf(String dayOf) {
        this.dayOf = dayOf;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
