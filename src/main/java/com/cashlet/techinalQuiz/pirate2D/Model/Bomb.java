package com.cashlet.techinalQuiz.pirate2D.Model;

public class Bomb extends Node {

    @Override
    String getType() {
        return "bomb";
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "id=" + id +
                '}';
    }
}
