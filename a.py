import sys
import math
class Node:
    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.neighbors = []

class Edge:
    DigonalCost = 5
    EculedianCost = 1
    
def __init__(self, node, g): 
    self.node = node
    self.g = g
    self.setDf(self.calculate_Digonal_Heuristic())
    self.setEf(self.calculate_Euclidean_Heuristic())
    self.setMf( self.calculate_Manhattan_Heuristic())
    
def setMf(self, Mh):
    self.Mf = Mh + self.g
    
def setEf(self, Eh):
    self.Ef = self.Eh + self.g
    
def setDf(self, Dh): 
    self.Df = self.Dh + self.g
    
def calculate_Manhattan_Heuristic(self):
    self.Mh = abs(self.node.getRow() - self.getRow()) + abs(self.node.getCol() - self.getCol())
return self.Mh

def calculate_Euclidean_Heuristic(self): 
    x = math.pow(abs(self.getRow() - self.node.getRow()), 2)
    y = math.pow(abs(self.getCol() - self.node.getCol()), 2)
    self.Eh = self.EculedianCost * mathsqrt(x + y)
return self.Eh

def calculate_Digonal_Heuristic(self):
    dx = abs(self.getRow() - self.node.getRow())
    dy = abs(self.getCol() - self.node.getCol())
    self.Eh = self.DigonalCost * max(dx, dy)
return self.Eh


def addBranch(self, weight, node):
    newEdge = Edge(node, weight)
    self.neighbors.add(newEdge)
    
def toString(self):
    return "Node{" + "row=" + str(self.row) + ", col=" + str(self.col) + " neighbors: " + str(self.neighbors)
def getRow(self): 
    return self.row
def setRow(self, row): 
    self.row = row
def getCol(self):
    return self.col
def setCol(self, col): 
    self.col = col
def getNeighbor(self):
    return self.neighbors

class TSP:
   
    def __init__(self,distances,InitalNode): # maybe these varible should be global?
        self.distances=distances 
        self.InitalNode=InitalNode
        
    @staticmethod
    def swap(array,first,second):
        temp=array[first]
        array[first]=array[second]
        array[second]=temp
        
    @staticmethod
    def allPermutationsHelper(permutation,permutations,n):
        if(n<=0):
            if(list(permutation).get(0) is InitalNode): # ==
                permutations.add(permutation)
                return
        tempPermutation=permutation.copy()
        for i in n :
            swap(tempPermutation,i,n-1)
            allPermutationsHelper(tempPermutation, permutations, n - 1)
            swap(tempPermutation, i, n - 1)
            
    @staticmethod 
    def permutations(original):
        permutations=[]
        allPermutationsHelper(original, permutations, len(original))
        return permutations
    
    def pathDistance(path):
        last=path[0]
        distance=0
        copy=slice(1,len(path))
        for i in path[copy]:
            distance+=distances.get(last).get(i)
            last=i
        return distance
    
    def findShortestPath():
        cities=distances.key.Set()
        paths=permutations(cities)
        shortestPath = none
        minDistance = sys.maxint
        for path in paths:
            distance=pathDistance(path)
            distance+=distances.get(path[len(path) - 1]).get(path[0])
            if(distance < minDistance):
                minDistance = distance
                shortestPath = path
        return shortestPath
    
    def GoToDepote(path):
        LastHouse=path[:len(path)+1] #path[len(path)-1:len(path)]
        if(LastHouse is "H1"):
        return 1
        if(LastHouse is "H2"):
        return 2
        if(LastHouse is "H3"):
        return 3
        if(LastHouse is "H4"):
        return 4
    
    def NewPath(path):
        path.append("D")
        return path
    
@staticmethod
def main():
    W  = Node(0, 0)
    H1 = Node(2, 6)
    H2 = Node(1, 3)
    H3 = Node(3, 5)
    H4 = Node(4, 2)
    D  = Node(5, 6)
    
    W.addBranch(110, H1);
    W.addBranch(80, H2);
    W.addBranch(130, H3);
    W.addBranch(160, H4);
          
    H1.addBranch(110, W);
    H1.addBranch(40, H2);
    H1.addBranch(30, H3);
    H1.addBranch(80, H4);
                
    H2.addBranch(80, W);
    H2.addBranch(40, H1);
    H2.addBranch(60, H3);
    H2.addBranch(90, H4);
                
    H3.addBranch(130, W);
    H3.addBranch(30, H1);
    H3.addBranch(60, H2);
    H3.addBranch(60, H4);
                
    H4.addBranch(160, W);
    H4.addBranch(80, H1);
    H4.addBranch(90, H2);
    H4.addBranch(60, H3);
           
    D.addBranch(170, W);
    D.addBranch(100, H1);
    D.addBranch(110, H2);
    D.addBranch(80, H3);
    D.addBranch(20, H4);
    
    ManhattanDistances = 
      map.of(
                      
             "W", 
      map.of("H1", W.getNeighbor().get(0).Mf,
             "H2", W.getNeighbor().get(1).Mf,
             "H3", W.getNeighbor().get(2).Mf,
             "H4", W.getNeighbor().get(3).Mf),
              
             "H1", 
      map.of("W",  H1.getNeighbor().get(0).Mf,
             "H2", H1.getNeighbor().get(1).Mf,
             "H3", H1.getNeighbor().get(2).Mf,
             "H4", H1.getNeighbor().get(3).Mf),
           
             
             "H2", 
      map.of("W", H2.getNeighbor().get(0).Mf,
             "H1",H2.getNeighbor().get(1).Mf,
             "H3",H2.getNeighbor().get(2).Mf,
             "H4", H2.getNeighbor().get(3).Mf),
             
              "H3",          
       map.of("W", H3.getNeighbor().get(0).Mf,
              "H1", H3.getNeighbor().get(1).Mf,
              "H2", H3.getNeighbor().get(2).Mf,
              "H4",  H3.getNeighbor().get(3).Mf),
     
       
              "H4",
       map.of("W",  H4.getNeighbor().get(0).Mf,
              "H1", H4.getNeighbor().get(1).Mf,
              "H2", H4.getNeighbor().get(2).Mf,
              "H3" ,H4.getNeighbor().get(3).Mf)
      
      )
 
          tsp= new TSP(ManhattanDistances , "W")
             
            shortestPath = tsp.findShortestPath()
            LastHouse=tsp.GoToDepote(shortestPath)
            distance = tsp.pathDistance(shortestPath)+(int)D.getNeighbor().get(LastHouse).g
               
            print("The path is " + tsp.NewPath(shortestPath) + " in " +
                  distance + " miles." + " With Manhattan Heuristic")
                
if __name__ == "__main__":
    TSP.main()
