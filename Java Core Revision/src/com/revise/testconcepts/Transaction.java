package com.revise.testconcepts;

import java.util.Objects;

public class Transaction {
    private String name;
    private String department;


    @Override
    public String toString() {
        return "Transaction{" +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public Transaction(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    /*@Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(department, that.department);
    }*/

   /* @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }*/

    @Override
    public boolean equals(Object o) {
        Transaction transaction = (Transaction) o;
        if (transaction.getName().equals(this.name) && transaction.getDepartment().equals(this.department)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department);
    }



}
