package com.revise.comparators;

public class Student implements Comparable<Student> {

    private String rollNo;
    private String name;
    private int marks;

    public Student(String rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String getRollNo() {
        return rollNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
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

    @Override
    public int compareTo(Student o) {
        if (this.marks > o.marks)
            return 1;
        else if (this.marks < o.marks)
            return -1;
        else return 0;
    }
}
