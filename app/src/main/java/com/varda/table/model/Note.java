package com.varda.table.model;

public class Note {

    private int id;
    private String content;

    public Note() {
    }

    public Note(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

