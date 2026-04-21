package com.revise.javafeatures.mystreams;

public class Transaction {
    private String category;
    private int amount;
    private String user;

    public Transaction(String user, int amount, String category) {
        this.user = user;
        this.amount = amount;
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
