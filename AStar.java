package com.ai.astar;

import java.util.*;

/**
 * A Star Algorithm
 *
 * @author Marcelo Surriabre
 * @version 2.1, 2017-02-23
 */
public class AStar {
    private static int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static int DEFAULT_DIAGONAL_COST = 14;  
    private int hvCost;
    private int diagonalCost;
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node SetOfGoals[];
    private Node NearestGoal;
    private List<Node> goals;


    public AStar(int rows, int cols, Node initialNode, Node SetOfGoals[], int hvCost, int diagonalCost ) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        setInitialNode(initialNode);
        this.SetOfGoals= new Node[SetOfGoals.length];
        setGoals(SetOfGoals);
        this.searchArea = new Node[rows][cols];
    

        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Double.compare(node0.getF(), node1.getF());
            }
        });
        goals=new ArrayList(Arrays.asList(SetOfGoals));
        setNodes();
        this.closedSet = new HashSet<>();
        nearest_goal();                 //After all, choose the nearest and decide the path
        
    }

    public AStar(int rows, int cols, Node initialNode, Node SetOfGoals[]) {
        this(rows, cols, initialNode, SetOfGoals, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST);
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculate_Euclidean_Heuristic(getInitialNode());
                this.searchArea[i][j] = node;
               
            }
        }
    }

    public void setBlocks(int[][] blocksArray) {
        for (int i = 0; i < blocksArray.length; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBlock(row, col);
        }
    }
    private void nearest_goal()
    {
        List<Node> path=new ArrayList<>();
        while(!goals.isEmpty())
        {
             NearestGoal= goals.get(0);                    //initally, first goal is the closest
             NearestGoal.calculate_Euclidean_Heuristic(getInitialNode());
            for(int i=1 ; i<= goals.size()-1 ; i++)
            {   goals.get(i).calculate_Euclidean_Heuristic(getInitialNode());
                if(goals.get(i).getH() < NearestGoal.getH())            //is the seconed goal nearest from the 1st one?
                {
                    NearestGoal= goals.get(i);
                }
            }
            path.addAll(findPath());
            setInitialNode(NearestGoal);        //The goal of the last delivary, the initial of the seconed deliver
            goals.remove(NearestGoal);
        }
        //Since all homes have been deleiverd, back to the depot
        printpath(path);
    }
//////////////////////////////////////////////////////////////////////UNTILL HERE IT' JUST INITIALZATION/////////////////////////////////
    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);        //Since it's the final node, give me it's parent
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {        //Walking in reverse 
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {        //Not in the corner 
            if (col - 1 >= 0) {     //As above
                checkNode(currentNode, col - 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {  //Current node with upper row and col and DFDignoal=14
        Node adjacentNode = getSearchArea()[row][col];      //the cell above me 
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {    //Not block and not previously added
            if (!getOpenList().contains(adjacentNode)) 
                 {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
                }
             else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(NearestGoal);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }
    //////////////////////////////////////////////////////////SETERS AND GETERS//////////////////////////////////////
    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return NearestGoal;
    }
    public void setFinalNode(Node FinalGoal)
    {
        this.NearestGoal=FinalGoal;
    }
  
    public void setGoals(Node SetOfGoals[]) {
        for(int i=0 ; i< SetOfGoals.length ; i++)
        {
            this.SetOfGoals[i]=SetOfGoals[i];
        }
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }
    public void printpath(List<Node> path)
    {
        for (Node node : path) 
              System.out.println(node);
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }

    private int getDiagonalCost() {
        return diagonalCost;
    }

    private void setDiagonalCost(int diagonalCost) {
        this.diagonalCost = diagonalCost;
    }
}

