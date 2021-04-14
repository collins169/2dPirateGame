package com.cashlet.techinalQuiz.pirate2D.Model;

public class Rock extends Node{

    @Override
    String getType() {
        return "rock";
    }

    @Override
    public String toString() {
        return "Rock{" +
                "id=" + id +
                '}';
    }
}
