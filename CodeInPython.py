import math
class Node:
    def __init__(self, row, col): 
        self.row = row
        self.col = col
        self.neighbors = []
# //////////////////////////////////////////////////////////////////////////
class Edge:
    def __init__(self, node, g): 
        self.node = node
        self.g = g
self.Dh = self.calculate_Digonal_Heuristic()
self.setDf(self.Dh)
self.Eh = self.calculate_Euclidean_Heuristic()
self.setEf(self.Eh)
self.Mh = self.calculate_Manhattan_Heuristic()
self.setMf(self.Mh )

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
    self.Eh = mathsqrt(x + y)
    return self.Eh

def calculate_Digonal_Heuristic(self): 
    dx = abs(self.getRow() - self.node.getRow())
    dy = abs(self.getCol() - self.node.getCol())
    self.Eh = max(dx, dy)
    return self.Eh
# //////////////////////////////////////////////////////////////////////////
def addBranch(self, weight, node):
    newEdge = Edge(node, weight)
    self.neighbors.add(newEdge)
    
def toString(self): return "Node{" + "row=" + str(self.row) + ", col=" + str(self.col) + " neighbors: " + str(self.neighbors)

def getRow(self): return self.row
def setRow(self, row): self.row = row
def getCol(self): return self.col
def setCol(self, col): self.col = col
def getNeighbor(self): return self.neighbors

class TSP: 
    W = Node(0, 0)# start point
    H1 = Node(2, 6)
    H2 = Node(1, 3)
    H3 = Node(3, 5)
    H4 = Node(4, 2)
    D = Node(5, 6)
def __init__(self, distances, InitalNode):
    self.distances = distances
    self.InitalNode = InitalNode
@staticmethod
def swap(array, first, second):
    temp = array[first]
    array[first] = array[second]
    array[second] = temp
@staticmethod

def allPermutationsHelper(permutation, permutations, n):
  
    if (n <= 0):
      if (Arrays.asList(permutation).get(0) is self.InitalNode): permutations.add(permutation)
    return
# Recursive

tempPermutation = Arrays.copyOf(permutation, len(permutation))
i = 0
while (i < n): self.swap(tempPermutation, i, n - 1)
# move element at i to the end
# move everything

self.allPermutationsHelper(tempPermutation, permutations, n - 1)
self.swap(tempPermutation, i, n - 1)
i += 1
@staticmethod
def permutations(original):
    permutations = []
    self.allPermutationsHelper(original,                  permutations, len(original))
    
    return permutations
    
def pathDistance(self, path):
    last = path[0]
    distance = 0
for next in Arrays.copyOfRange(path, 1, len(path)): distance += self.distances.get(last).get(next)
# distance to get back from last city to first city
last = next
        return distance
    
def findShortestPath(self):
    cities = distances.keySet()
paths = self.permutations(cities)
shortestPath = None
minDistance = sys.maxsize
# arbitrarily high
for path in paths: distance = self.pathDistance(path)
# distance from last to first must be added
distance += self.distances.get(path[len(path) - 1]).get(path[0])
if (distance < minDistance): minDistance = distance
shortestPath = path
return shortestPath
def GoToDepote(self, path): a = Arrays.toString(path)
# path without depot
LastHouse = a[len(a) - 3: ]
LastHouse = LastHouse.replace(LastHouse[len(LastHouse) - 1: ], "")
# last house
# compute the distance between last house and the depot
if (LastHouse.compareTo("H1") == 0): return 1
if (LastHouse.compareTo("H2") == 0): return 2
if (LastHouse.compareTo("H3") == 0): return 3
if (LastHouse.compareTo("H4") == 0): return 4
return 0
def NewPath(self, path): a = Arrays.toString((path))
a = a.replace(a[len(a) - 1: ], "")
a = a + ", D]"
return a
@staticmethod
def main(args): W = Node(0, 0)
# start point
H1 = Node(2, 6)
H2 = Node(1, 3)
H3 = Node(3, 5)
H4 = Node(4, 2)
D = Node(5, 6)
# Graph
# 0 1 2 3 4 5 6
# 0 W - - - - - -# 1 - - -H2 - - -# 2 - - - - - -H1
# 3 - - - - -H3 - # 4 - -H4 - - - -# 5 - - - - - -D
W.addBranch(25, H1)
W.addBranch(17, H2)
W.addBranch(29, H3)
W.addBranch(21, H4)
H1.addBranch(25, W)
H1.addBranch(71, H2)
H1.addBranch(30, H3)
H1.addBranch(81, H4)
H2.addBranch(17, W)
H2.addBranch(71, H1)
H2.addBranch(33, H3)
H2.addBranch(49, H4)
H3.addBranch(29, W)
H3.addBranch(30, H1)
H3.addBranch(33, H2)
H3.addBranch(52, H4)
H4.addBranch(21, W)
H4.addBranch(81, H1)
H4.addBranch(49, H2)
H4.addBranch(52, H3)
D.addBranch(100, W)
D.addBranch(100, H1)
D.addBranch(100, H2)
D.addBranch(100, H3)
D.addBranch(100, H4)
# here from java
ManhattanDistances =   Map.of(
                      
             "W", 
      Map.of("H1", W.getNeighbor().get(0).Mf,
             "H2", W.getNeighbor().get(1).Mf,
             "H3", W.getNeighbor().get(2).Mf,
             "H4", W.getNeighbor().get(3).Mf),
          
             
              
             "H1", 
      Map.of("W",  H1.getNeighbor().get(0).Mf,
             "H2", H1.getNeighbor().get(1).Mf,
             "H3", H1.getNeighbor().get(2).Mf,
             "H4", H1.getNeighbor().get(3).Mf),
           
             
             "H2", 
      Map.of("W", H2.getNeighbor().get(0).Mf,
             "H1",H2.getNeighbor().get(1).Mf,
             "H3",H2.getNeighbor().get(2).Mf,
             "H4", H2.getNeighbor().get(3).Mf),
             
              "H3",          
       Map.of("W", H3.getNeighbor().get(0).Mf,
              "H1", H3.getNeighbor().get(1).Mf,
              "H2", H3.getNeighbor().get(2).Mf,
              "H4",  H3.getNeighbor().get(3).Mf),
     
       
              "H4",
       Map.of("W",  H4.getNeighbor().get(0).Mf,
              "H1", H4.getNeighbor().get(1).Mf,
              "H2", H4.getNeighbor().get(2).Mf,
              "H3" ,H4.getNeighbor().get(3).Mf)
      
      );
#until here
      
EuclideanDistances = None
DigonalDistances = None
tsp = TSP(ManhattanDistances, "W")
tsp2 = TSP(EuclideanDistances, "W")
tsp3 = TSP(DigonalDistances, "W")
shortestPath = tsp.findShortestPath()
LastHouse = tsp.GoToDepote(shortestPath)
distance = tsp.pathDistance(shortestPath) + int(D.getNeighbor().get(LastHouse).Mf)
print("The path is " + tsp.NewPath(shortestPath) + " in " + str(distance) + " miles." + " With Manhattan Heuristic")
shortestPath2 = tsp2.findShortestPath()
LastHouse2 = tsp2.GoToDepote(shortestPath2)
distance2 = tsp2.pathDistance(shortestPath2) + int(D.getNeighbor().get(LastHouse).Mf)
print("The path is " + tsp2.NewPath(shortestPath2) + " in " + str(distance2) + " miles." + " With Euclidean Heuristic (Optimal)")
shortestPath3 = tsp3.findShortestPath()
LastHouse3 = tsp3.GoToDepote(shortestPath3)
distance3 = tsp3.pathDistance(shortestPath) + int(D.getNeighbor().get(LastHouse).Mf)
print("The path is " + tsp3.NewPath(shortestPath3) + " in " + str(distance3) + " miles." + " With Digonal Heuristic")
if __name__ == "__main__": TSP.main([])
