import math
class Node: def __init__(self, row, col): self.row = row
self.col = col
self.neighbors = []
# //////////////////////////////////////////////////////////////////////////
class Edge: DigonalCost = 5
EculedianCost = 1
def __init__(self, node, g): self.node = node
self.g = g
self.setDf(self.Dh = self.calculate_Digonal_Heuristic())
self.setEf(self.Eh = self.calculate_Euclidean_Heuristic())
self.setMf(self.Mh = self.calculate_Manhattan_Heuristic())
def setMf(self, Mh): self.Mf = Mh + self.g
def setEf(self, Eh): self.Ef = self.Eh + self.g
def setDf(self, Dh): self.Df = self.Dh + self.g
def calculate_Manhattan_Heuristic(self): self.Mh = abs(self.node.getRow() - self.getRow()) + abs(self.node.getCol() - self.getCol())
return self.Mh
def calculate_Euclidean_Heuristic(self): x = math.pow(abs(self.getRow() - self.node.getRow()), 2)
y = math.pow(abs(self.getCol() - self.node.getCol()), 2)
self.Eh = self.EculedianCost * mathsqrt(x + y)
return self.Eh
def calculate_Digonal_Heuristic(self): dx = abs(self.getRow() - self.node.getRow())
dy = abs(self.getCol() - self.node.getCol())
self.Eh = self.DigonalCost * max(dx, dy)
return self.Eh
# //////////////////////////////////////////////////////////////////////////
def addBranch(self, weight, node): newEdge = Edge(node, weight)
self.neighbors.add(newEdge)
def toString(self): return "Node{" + "row=" + str(self.row) + ", col=" + str(self.col) + " neighbors: " + str(self.neighbors)
def getRow(self): return self.row
def setRow(self, row): self.row = row
def getCol(self): return self.col
def setCol(self, col): self.col = col
def getNeighbor(self): return self.neighbors