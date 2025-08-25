package com.mycollections;

public class Student implements Comparable {

    private String name;
    private String rollNo;
    private int marks;

    public String getName() {
        return name;
    }

   /* public void setName(String name) {
        this.name = name;
    }*/

    public String getRollNo() {
        return rollNo;
    }

   /* public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }*/

    public int getMarks() {
        return marks;
    }

   /* public void setMarks(int marks) {
        this.marks = marks;
    }*/

    public Student(String name, String rollNo, int marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", marks=" + marks +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        if (this.marks > student.marks) {
            return 1;
        } else if (this.marks < student.marks) {
            return -1;
        } else {
            return 0;
        }
    }
}
