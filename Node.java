package com.ai.astar;

/**
 * Node Class
 *
 * @author Marcelo Surriabre
 * @version 2.0, 2018-02-23
 */
public class Node {

    private double g;
    private double f;
    private double h;
    private int row;
    private int col;
    private boolean isBlock;
    private Node parent;

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

   /*                      Two Different heuristics                    */
   
    //Euclidean Distance
    public void calculate_Euclidean_Heuristic(Node InitialNode) {        
        double x = Math.pow(Math.abs(getRow()- InitialNode.getRow()) , 2);  
        double y=Math.pow(Math.abs(getCol()- InitialNode.getCol()) , 2);
        this.h=Math.sqrt(x+y);
    }
    //Manhattan Distance 
    public void calculate_Manhattan_Heuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
    }

    public void setNodeData(Node currentNode, int cost) {
        double gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        double gCost = currentNode.getG() + cost;
        if (gCost < getG()) {                   //calculated cost less than my cost
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        double finalCost = getG() + getH();
        setF(finalCost);
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    public double getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getG() {
        return g;
    }

    public void setG(Double g) {
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

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
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
