package a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TSP {
    
    
    
    private final Map<String, Map<String, Double>> distances;
    private static String InitalNode;
   
	public TSP(Map<String, Map<String, Double>> distances , String InitalNode) {
	this.distances = distances;
        this.InitalNode=InitalNode;
	}

	public static <T> void swap(T[] array, int first, int second) {
		T temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}

	private static <T> void allPermutationsHelper(T[] permutation, List<T[]> permutations, int n) {
		// Base case - we found a new permutation, add it and return
		if (n <= 0) {
			if(Arrays.asList(permutation).get(0).equals(InitalNode))
			{	

				permutations.add(permutation);
				return;
			}
		}
		// Recursive case - find more permutations by doing swaps
		T[] tempPermutation = Arrays.copyOf(permutation, permutation.length);
		for (int i = 0; i < n; i++) {
			swap(tempPermutation, i, n - 1); // move element at i to the end
			// move everything else around, holding the end constant
			allPermutationsHelper(tempPermutation, permutations, n - 1);
			swap(tempPermutation, i, n - 1); // backtrack
		}
	}

	private static <T> List<T[]> permutations(T[] original) {
		List<T[]> permutations = new ArrayList<>();
		allPermutationsHelper(original, permutations, original.length);
		return permutations;
	}

	public int pathDistance(String[] path) {
		String last = path[0];
		int distance = 0;
		for (String next : Arrays.copyOfRange(path, 1, path.length)) {
			distance += distances.get(last).get(next);
			// distance to get back from last city to first city
			last = next;
		}
               
		return distance;
	}

	public String[] findShortestPath() {
		String[] cities = distances.keySet().toArray(String[]::new);
		List<String[]> paths = permutations(cities);
		String[] shortestPath = null;
		int minDistance = Integer.MAX_VALUE; // arbitrarily high
		for (String[] path : paths) {
			int distance = pathDistance(path);
			// distance from last to first must be added
			distance += distances.get(path[path.length - 1]).get(path[0]);
			if (distance < minDistance) {
				minDistance = distance;
				shortestPath = path;
			}
		}
                
              
		return shortestPath;
        }
        
        public int GoToDepote(String[] path){
            
               String a=Arrays.toString(path); // path without depot
               String LastHouse=a.substring(a.length() - 3) ;
               LastHouse= LastHouse.replace(LastHouse.substring(LastHouse.length()-1), "");//last house
             
                 // compute the distance between last house and the depot
                 if(LastHouse.compareTo("H1")==0)
                   return 1;
                
                 if(LastHouse.compareTo("H2")==0)
                   return 2;
                 
                  if(LastHouse.compareTo("H3")==0)
                   return 3;
                  
                  if(LastHouse.compareTo("H4")==0)
                   return 4;
              return 0;
           
        }
         public String NewPath(String[] path){
             
            String a=Arrays.toString((path));
            a = a.replace(a.substring(a.length()-1), ""); 
            a=a+", D]";
                
            return a;
             
         }
     
        public static void main(String[] args) {
         
          Node W  = new Node(0, 0); // start point
          Node H1 = new Node(2 , 6);
          Node H2 = new Node(1 , 3);
          Node H3 = new Node(3 , 5);
          Node H4 = new Node(4 , 2);
          Node D  = new Node(5 , 6);
                   
       /* Graph
        
   
          0   1   2   3   4   5   6
        
        0 W   -   -   -   -    -   -
        
        1 -   -   -   H2   -   -   -
        
        2 -   -   -   -    -   -   H1
        
        3 -   -   -   -    -   H3  -
        
        4 -   -   H4   -   -   -   -
        
        5 -   -   -   -    -   -   D
        
      
      */
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
  Map<String, Map <String, Double> > EuclideanDistances = 
      Map.of(
                      
             "W", 
      Map.of("H1", W.getNeighbor().get(0).Ef,
             "H2", W.getNeighbor().get(1).Ef,
             "H3", W.getNeighbor().get(2).Ef,
             "H4", W.getNeighbor().get(3).Ef),
          
             
              
             "H1", 
      Map.of("W",  H1.getNeighbor().get(0).Ef,
             "H2", H1.getNeighbor().get(1).Ef,
             "H3", H1.getNeighbor().get(2).Ef,
             "H4", H1.getNeighbor().get(3).Ef),
           
             
             "H2", 
      Map.of("W", H2.getNeighbor().get(0).Ef,
             "H1",H2.getNeighbor().get(1).Ef,
             "H3",H2.getNeighbor().get(2).Ef,
             "H4", H2.getNeighbor().get(3).Ef),
             
              "H3",          
       Map.of("W", H3.getNeighbor().get(0).Ef,
              "H1", H3.getNeighbor().get(1).Ef,
              "H2", H3.getNeighbor().get(2).Ef,
              "H4",  H3.getNeighbor().get(3).Ef),
     
       
              "H4",
       Map.of("W",  H4.getNeighbor().get(0).Ef,
              "H1", H4.getNeighbor().get(1).Ef,
              "H2", H4.getNeighbor().get(2).Ef,
              "H3" ,H4.getNeighbor().get(3).Ef)
      
      );
        Map<String, Map <String, Double> > DigonalDistances = 
      Map.of(
                      
             "W", 
      Map.of("H1", W.getNeighbor().get(0).Df,
             "H2", W.getNeighbor().get(1).Df,
             "H3", W.getNeighbor().get(2).Df,
             "H4", W.getNeighbor().get(3).Df),
          
             
              
             "H1", 
      Map.of("W",  H1.getNeighbor().get(0).Df,
             "H2", H1.getNeighbor().get(1).Df,
             "H3", H1.getNeighbor().get(2).Df,
             "H4", H1.getNeighbor().get(3).Df),
           
             
             "H2", 
      Map.of("W", H2.getNeighbor().get(0).Df,
             "H1",H2.getNeighbor().get(1).Df,
             "H3",H2.getNeighbor().get(2).Df,
             "H4", H2.getNeighbor().get(3).Df),
             
              "H3",          
       Map.of("W", H3.getNeighbor().get(0).Df,
              "H1", H3.getNeighbor().get(1).Df,
              "H2", H3.getNeighbor().get(2).Df,
              "H4",  H3.getNeighbor().get(3).Df),
     
       
              "H4",
       Map.of("W",  H4.getNeighbor().get(0).Df,
              "H1", H4.getNeighbor().get(1).Df,
              "H2", H4.getNeighbor().get(2).Df,
              "H3" ,H4.getNeighbor().get(3).Df)
      
      );
      		
		TSP tsp  = new TSP(ManhattanDistances , "W");
	 	TSP tsp2 = new TSP(EuclideanDistances , "W");
                TSP tsp3 = new TSP(DigonalDistances , "W");

             
                String[] shortestPath = tsp.findShortestPath();
                int LastHouse=tsp.GoToDepote(shortestPath);
                int distance = tsp.pathDistance(shortestPath)+(int)D.getNeighbor().get(LastHouse).g;
               
               System.out.println("The path is " + tsp.NewPath(shortestPath) + " in " +
				distance + " miles." + " With Manhattan Heuristic");
                
               String[] shortestPath2 = tsp2.findShortestPath();
                int LastHouse2=tsp2.GoToDepote(shortestPath2);
                int distance2 = tsp2.pathDistance(shortestPath2)+(int)D.getNeighbor().get(LastHouse).g;
              
                
		System.out.println("The path is " + tsp2.NewPath(shortestPath2) + " in " +
				distance2 + " miles." + " With Euclidean Heuristic (Optimal)");
                
                
                
                 String[] shortestPath3 = tsp3.findShortestPath();
		int LastHouse3=tsp3.GoToDepote(shortestPath3);
                int distance3 = tsp3.pathDistance(shortestPath)+(int)D.getNeighbor().get(LastHouse).g;
                
		System.out.println("The path is " + tsp3.NewPath(shortestPath3) + " in " +
				distance3 + " miles." + " With Digonal Heuristic");
                
               
	
        }
     
}

