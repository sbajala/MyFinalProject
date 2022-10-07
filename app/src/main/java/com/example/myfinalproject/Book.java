package com.example.myfinalproject;

import com.example.myfinalproject.enums.ReadingStatus;

public class Book {

    private int id;
    private String title;
    private String author;
    private String note;
    private ReadingStatus readingStatus;


    public Book(int id, String title, String author, String note, ReadingStatus readingStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.note = note;
        this.readingStatus = readingStatus;
    }

    public Book(String title, String author, String note, ReadingStatus readingStatus) {
        this.title = title;
        this.author = author;
        this.note = note;
        this.readingStatus = readingStatus;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }
}
