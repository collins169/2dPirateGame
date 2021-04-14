package com.cashlet.techinalQuiz.pirate2D.Model;

public class Coin extends Node {

    private int amount = 0;

    @Override
    String getType() {
        return "coin";
    }

    @Override
    public String toString() {
        return "Coin{" +
                "amount=" + amount + "," +
                "id=" + id + "," +
                "adjacent=" + adjacent.size() +
                '}';
    }
}
