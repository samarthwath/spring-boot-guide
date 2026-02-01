package com.revise.core.java.mycollections;

public class Student implements Comparable<Student> {
    private int id;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    private String name;
    private int marks;

    @Override
    public int compareTo(Student student) {
        if (this.marks < student.getMarks()) {
            return -1;
        } else if (this.marks > student.getMarks()) {
            return 1;
        }
        return 0;
    }
}
