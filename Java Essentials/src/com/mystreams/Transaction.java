package com.mystreams;

public class Transaction {
    private int amount;
    private String category;

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }
}
