package com.ai.astar;

import java.util.List;

public class AStarTest {

    public static void main(String[] args) {
        Node initalNode=  new Node(2, 1);      //initial node 
        Node [] goals = new Node[4];
        Node [] goals = new Node[4];
        goals[0]= new Node(1, 6);       //subgoal node 
        goals[1] = new Node(0, 6);       //subgoal node
        goals[2] = new Node(4, 5);       //subgoal node
        goals[3]= new Node(5, 6);       //Final Node(Depot)

        int rows = 6;   
        int cols = 7;
            AStar aStar = new AStar(rows, cols, initalNode, goals);
        
            
       //Search Area
        //      0   1   2   3   4   5   6
        // 0    -   -   -   -   -   -   H2
        // 1    -   -   -   -   -   -   H1
        // 2    -   -   -   -   -   -   -
        // 3    -   -   -   -   -   -   -
        // 4    W   -   -   -   -   H3  -
        // 5    -   -   -   -   -   -   D

        //Expected output with diagonals
        /*
        Node [row=4, col=0]
        Node [row=4, col=1]
        Node [row=4, col=2]
        Node [row=4, col=3]
        Node [row=4, col=4]
        Node [row=4, col=5]
        Node [row=4, col=5]
        Node [row=5, col=6]
        Node [row=4, col=5]
        Node [row=3, col=5]
        Node [row=2, col=5]
        Node [row=1, col=6]
        Node [row=1, col=6]
        Node [row=0, col=6]
        */

 

    }
}
