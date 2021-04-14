package com.cashlet.techinalQuiz.pirate2D.Model;


public class Request {
    private String type;
    private int amount;


    public Request(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
