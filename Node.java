package Folder;

import java.util.*;

public class Node {
    
    private int row;
    private int col;
    public List<Edge> neighbors;

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        neighbors= new ArrayList<>();
    }
////////////////////////////////////////////////////////////////////////////
        public class Edge{
            public int g;
            public double f;
            public double h;
            public Node node;

        Edge(Node node , int g){
            this.node = node;
            this.g=g;
            this.h=calculate_Manhattan_Heuristic();
            setF();
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

        public double getF() {
        return f;
        }

        public void setF() {
        this.f = getG()+getH();
        }
         public double calculate_Manhattan_Heuristic() {
            this.h = Math.abs(node.getRow() - getRow()) + Math.abs(node.getCol() - getCol());
            return this.h;
        }
        //TODO Add 2 other heu

        @Override
        public String toString() {
            return "Edge{" + "F=" + getF() + ", G=" + getG() + " H= "+getH();
        }
    }
////////////////////////////////////////////////////////////////////////////

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(node , weight);
        neighbors.add(newEdge);
  }
    

    @Override
    public String toString() {
        return "Node{" + "row=" + row + ", col=" + col + " neighbors: "+neighbors;
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
    public List<Edge> getNeighbor()
    {
        return neighbors;
    }
}
