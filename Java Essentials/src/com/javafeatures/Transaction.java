package com.javafeatures;

public class Transaction {
    private String category;
    private int amount;


    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
