package com.cashlet.techinalQuiz.pirate2D.Utils;


import com.cashlet.techinalQuiz.pirate2D.Model.*;
import com.google.gson.Gson;

import java.util.*;

public class Game2DPath {

    List<Node> currentRoute = new ArrayList<>();
    List<Node> listInput = new ArrayList<>();

    // helper variables
    int matrixColSize = 0;
    int matrixRowSize = 0;



    public void Init(Object[][] data, Coordinate startCoordinate, Coordinate endCoordinate) {
        try {
            matrixRowSize = data.length;

            for (Object[] obj : data) {
                matrixColSize = obj.length;

                for (Object o : obj) {
                    System.out.println(o);
                    Map<Map<Object, Object>, Map<Object, Object>> newArry = (Map<Map<Object, Object>, Map<Object, Object>>) o;
                    System.out.println(newArry);
                    String type = String.valueOf(newArry.get("type"));
                    System.out.println(type);
                    switch (type) {
                        case "coin":
                            Coin coin = new Gson().fromJson(newArry.toString(), Coin.class);
                            listInput.add(coin);
                            break;

                        case "bomb":
                            Bomb bomb = new Gson().fromJson(newArry.toString(), Bomb.class);
                            listInput.add(bomb);
                            break;

                        case "rock":
                            Rock rock = new Gson().fromJson(newArry.toString(), Rock.class);
                            listInput.add(rock);
                            break;
                    }
                }
            }
            // System.out.println(listInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        getStarted(startCoordinate, endCoordinate);
    }


    /**
     * Create a usable dataType, graph or Matrix
     */
    private void getStarted(Coordinate startCoordinate, Coordinate endCoordinate) {

        // Get the possible path for each node in list.
        Node[][] matrix = convertToMatrix( listInput.toArray());
        // System.out.println(Arrays.deepToString(matrix));

        // set up adjacent nodes with valid paths
        populateAdjacentNodes(matrix);
        System.out.println(Arrays.deepToString(matrix));

        // apply BFS
        int x1 = translateX(startCoordinate.getX());
        int y1 = translateY(startCoordinate.getY());
        Node startNode = matrix[x1][y1];

        int x2 = translateX(endCoordinate.getX());
        int y2 = translateY(endCoordinate.getY());
        Node endNode = matrix[x2][y2];

        System.out.println("start = " + x1 + "," + y2);
        System.out.println("end = " + x2 + "," + y2);


        boolean result = applyBFSAlgorithm(startNode, endNode);
        if (result) {
            System.out.println("Found destination");
        } else {
            System.out.println("Unable to reach destination");
        }
    }


    /**
     * Convert array list to matrix
     * @param subArray linear input list of nodes
     * @return matrix
     */
    private Node[][] convertToMatrix(Object [] subArray) {
        Node [][] result = new Node[matrixRowSize][matrixColSize];
        for (int i = 0; i < subArray.length; i++) {
            int row = (int) Math.floor(i / (double) matrixRowSize);
            int col = i % matrixColSize;
            result[row][col] = (Node) subArray[i];
        }
        return result;
    }


    /**
     * Insert valid adjacent nodes
     * valid path only Top or Right
     */
    private void populateAdjacentNodes(Node[][] matrix) {

        for (int i=0; i<matrixRowSize; i++) {
            for (int j=0; j<matrixColSize; j++) {

                Node currentNode = matrix[i][j];

                Node topNode = top(matrix, i, j);
                if (topNode != null) {
                    currentNode.adjacent.add(topNode);
                }

                Node rightNode = right(matrix, i, j);
                if (rightNode != null) {
                    currentNode.adjacent.add(rightNode);
                }

                matrix[i][j] = currentNode;
            }
        }
    }


    /**
     * Top = -[1][0]
     * Minus because row index sequence is reversed
     */
    private Node top(Node[][] matrix, int refRow, int refCol) {
        try {
            return matrix[refRow - 1][refCol];
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }


    /**
     * Right +[0][1]
     * Plus because column index moves to the right as it should
     */
    private Node right(Node[][] matrix, int refRow, int refCol) {
        try {
            return matrix[refRow][refCol + 1];
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }


    /**
     * Convert array Index on X axis to graph coordinate
     * @param i array index
     * @return return converted value
     */
    private int translateX(int i) {
        return (matrixColSize-1) - i;
    }


    /**
     * Convert array Index on Y axis to graph coordinate
     * @param i array index
     * @return return the same sekof arrayIndex on x is unchanged for graph as well
     */
    private int translateY(int i) {
        return i;
    }


    /**
     * Apply Breadth First Search algorithm
     * @param start Node start
     * @param destination Node destination
     * @return true/false for if can reach destination
     */
    private boolean applyBFSAlgorithm(Node start, Node destination) {
        System.out.println("startNode => " + start);
        System.out.println("endNode => " + destination);

        // nodes I am yet to visit
        LinkedList<Node> yetToVisit = new LinkedList<>();

        // IDs of nodes ive already visited
        HashSet<Integer> visitedNodes = new HashSet<>();

        // visit the start point first
        yetToVisit.add(start);

        // keep iterating till there are no more nodes to visit
        while (!yetToVisit.isEmpty()) {

            // get node
            Node currentNode = yetToVisit.remove();
            if (currentNode == destination) {
                // path exists
                getVisitedNodes(visitedNodes);
                return true;
            }

            // have I already visited this node
            if (visitedNodes.contains(currentNode.id)) {
                continue;
            }

            // i have not, add the id
            visitedNodes.add(currentNode.id);

            // queue up the next set of nodes to be visited
            yetToVisit.addAll(currentNode.adjacent);
        }

        return false;
    }


    /**
     * Inaccurate currently
     * @param visitedNodes visited nodes
     */
    private void getVisitedNodes(HashSet<Integer> visitedNodes) {
        for (Node inputNode : listInput) {
            for (int id : visitedNodes) {
                if (id == inputNode.id) {
                    currentRoute.add(inputNode);
                }
            }
        }
        System.out.println("current route => " + currentRoute);
    }

}
