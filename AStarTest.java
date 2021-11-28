package com.ai.astar;

import java.util.List;

public class AStarTest {

    public static void main(String[] args) {
        Node initalNode=  new Node(2, 1);      //initial node 
        Node [] goals = new Node[4];
        goals[0]= new Node(1, 6);       //subgoal node 
        goals[1] = new Node(1, 3);       //Final node
        goals[2] = new Node(4, 5);       //Final node
        goals[3]= new Node(5, 6);

        int rows = 6;   
        int cols = 7;
            AStar aStar = new AStar(rows, cols, initalNode, goals);
        
            
       //  }
        //Search Area
        //      0   1   2   3   4   5   6
        // 0    -   -   -   -   -   -   -
        // 1    -   -   -   H2  -   -   H1
        // 2    -   W   -   -   -   -   -
        // 3    -   -   -   -   -   -   -
        // 4    -   -   -   -   -   H3  -
        // 5    -   -   -   -   -   -   D

        //Expected output with diagonals
        //Node [row=2, col=1]
        //Node [row=1, col=2]
        //Node [row=0, col=3]
        //Node [row=0, col=4]
        //Node [row=1, col=5]
        //Node [row=2, col=6]

        //Search Path with diagonals
        //      0   1   2   3   4   5   6
        // 0    -   -   -   *   -   -   -
        // 1    -   -   *   B   *   -   -
        // 2    -   I*  -   B   -  *F   -
        // 3    -   -   -   B   -   -   -
        // 4    -   -   -   -   -   -   -
        // 5    -   -   -   -   -   -   -

        //Expected output without diagonals
        //Node [row=2, col=1]
        //Node [row=2, col=2]
        //Node [row=1, col=2]
        //Node [row=0, col=2]
        //Node [row=0, col=3]
        //Node [row=0, col=4]
        //Node [row=1, col=4]
        //Node [row=2, col=4]
        //Node [row=2, col=5]

        //Search Path without diagonals
        //      0   1   2   3   4   5   6
        // 0    -   -   *   *   *   -   -
        // 1    -   -   *   B   *   -   -
        // 2    -   I*  *   B   *  *F   -
        // 3    -   -   -   B   -   -   -
        // 4    -   -   -   -   -   -   -
        // 5    -   -   -   -   -   -   -
    }
}
