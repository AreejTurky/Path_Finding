package a;

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
         
            public double Mf;
            public double Ef;
            public double Df;
            
            public double Mh;
            public double Eh;
            public double Dh;
            
            int DigonalCost=5;
            int EculedianCost=1;
            public Node node;

        Edge(Node node , int g){
            this.node = node;
            this.g=g;
            this.setDf(this.Dh=calculate_Digonal_Heuristic());
            this.setEf(this.Eh=calculate_Euclidean_Heuristic());
            this.setMf(this.Mh=calculate_Manhattan_Heuristic());
   
       
        }

        public void setMf(double Mh) {
            this.Mf = Mh+this.g;
        }

        public void setEf(double Eh) {
            this.Ef = this.Eh+this.g;
        }

        public void setDf(double Dh) {
            this.Df = this.Dh+this.g;
        }

      
       public double calculate_Manhattan_Heuristic() {
            this.Mh = Math.abs(node.getRow() - getRow()) + Math.abs(node.getCol() - getCol());
            
            return this.Mh;
        }
        public double calculate_Euclidean_Heuristic() {        
        double x = Math.pow(Math.abs(getRow()- node.getRow()) , 2);  
        double y=Math.pow(Math.abs(getCol()- node.getCol()) , 2);
         this.Eh=EculedianCost* Math.sqrt(x+y);
       
        return Eh;
        
    }
        
          public double calculate_Digonal_Heuristic() {
         
    double dx = Math.abs(getRow() - node.getRow());
    double dy = Math.abs(getCol() - node.getCol());
    
    this.Eh=DigonalCost*Math.max(dx, dy);
    
    return this.Eh;
  
            }
          
          
        //TODO Add 2 other heu

      
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