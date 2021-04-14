package com.cashlet.techinalQuiz.pirate2D.Model;


import java.util.LinkedList;
import java.util.Random;

public abstract class Node {

    // id to identify the node
    public int id = new Random().nextInt();

    // a list of the nodes that the current node can travel to
    // the node at the top
    // the node to the right
    public LinkedList<Node> adjacent = new LinkedList<>();

    public Node() {
    }

    abstract String getType();
}
