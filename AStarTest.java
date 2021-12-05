package Folder;

import java.util.*;

import java.util.List;

public class AStarTest {

    public static void main(String[] args) {
        Node initalNode=  new Node(2, 1);      //initial node 

        List<Node> goals= new ArrayList<>();
        goals.add(new Node(1, 6));
        goals.add(new Node(1, 3));
        goals.add(new Node(4, 5));

        int rows = 6;   
        int cols = 7;
       AStarr aStar = new AStarr(rows, cols, initalNode, goals);
        
            
       //  }
        //Search Area
        //      0   1   2   3   4   5   6
        // 0    -   -   -   -   -   -   -
        // 1    -   -   -   H2  -   -   H1
        // 2    -   W   -   -   -   -   -
        // 3    -   -   -   -   -   -   -
        // 4    -   -   -   -   -   H3  -
        // 5    -   -   -   -   -   -   D


    }
}
