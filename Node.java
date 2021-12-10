package Folder;

import java.util.*;

public class Node {
    
    public int row;
    public int col;
    public int g;
    public double f;
    public double h;
    public Node parent;
    public List<Node> neighbors;

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.g=10;
        neighbors= new ArrayList<>();
    }

   /*                      Two Different heuristics                    */
   
    //Euclidean Distance
    public double calculate_Euclidean_Heuristic(Node InitialNode) {        
        double x = Math.pow(Math.abs(getRow()- InitialNode.getRow()) , 2);  
        double y=Math.pow(Math.abs(getCol()- InitialNode.getCol()) , 2);
        return Math.sqrt(x+y);                  //TODO CHANGES
    }
    //Manhattan Distance 
    public double calculate_Manhattan_Heuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
        return this.h;
    }


    @Override
    public String toString() {
        return "Node{" + "row=" + row + ", col=" + col + ", f=" + f + ", g=" + g+ ", h=" + h+" neighbors: "+this.neighbors+'}';
    }
    public void setNeighbors(List<Node> n)
    {
        this.neighbors=n;
    }
    public List<Node> getNeighbors()
    {
        return this.neighbors;
    }
    public double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
