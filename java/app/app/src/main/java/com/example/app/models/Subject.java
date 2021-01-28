package com.example.app.models;

public class Subject {

    private String subjectName;
    private long id;

    public Subject(String subjectName, long id) {
        this.subjectName = subjectName;
        this.id = id;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", id=" + id +
                '}';
    }
}
