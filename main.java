        public static void main(String[] args) {
         
          Node W  = new Node(0, 0); // start point
          Node H1 = new Node(2 , 6);
          Node H2 = new Node(1 , 3);
          Node H3 = new Node(3 , 5);
          Node H4 = new Node(4 , 2);
          Node D  = new Node(5 , 6);
              
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
                
                  
      Map<String, Map <String, Double> > ManhattanDistances = 
      Map.of(
                      
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
      		
		TSP tsp  = new TSP(ManhattanDistances , "W");
             
                String[] shortestPath = tsp.findShortestPath();
                int LastHouse=tsp.GoToDepote(shortestPath);
                int distance = tsp.pathDistance(shortestPath)+(int)D.getNeighbor().get(LastHouse).g;
               
               System.out.println("The path is " + tsp.NewPath(shortestPath) + " in " +
				distance + " miles." + " With Manhattan Heuristic");
                
              
               
	
        }
     
