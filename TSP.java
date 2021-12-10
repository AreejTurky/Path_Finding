
package a;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class TSP {
    
         static Node W  = new Node(0, 0); // start point
         static Node H1 = new Node(2 , 6);
         static Node H2 = new Node(1 , 3);
         static Node H3 = new Node(3 , 5);
         static Node H4 = new Node(4 , 2);
         static Node D  = new Node(0 , 6);
    
         
    
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
               double distance=0;
               
               
               String LastHouse=a.substring(a.length() - 3) ;
               LastHouse= LastHouse.replace(LastHouse.substring(LastHouse.length()-1), "");//last house
             
                 // compute the distance between last house and the depot
                 if(LastHouse.compareTo("H1")==0)
                   distance= D.calculate_Euclidean_Heuristic(H1);
                
                 if(LastHouse.compareTo("H2")==0)
                   distance= D.calculate_Euclidean_Heuristic(H2);
                 
                  if(LastHouse.compareTo("H3")==0)
                   distance= D.calculate_Euclidean_Heuristic(H3);
                  
                  if(LastHouse.compareTo("H4")==0)
                   distance= D.calculate_Euclidean_Heuristic(H4);
            
            
        return (int) distance;
            
        }
         public String NewPath(String[] path){
             
            String a=Arrays.toString((path));
            a = a.replace(a.substring(a.length()-1), ""); 
            a=a+", D]";
                
            return a;
             
         }
     

	public static void main(String[] args) {
         
        
        
         
       /* Graph
        
   
          0   1   2   3   4   5   6
        
        0 W   -   -   -   -    -   -
        
        1 -   -   -   H2   -   -   -
        
        2 -   -   -   -    -   -   H1
        
        3 -   -   -   -    -   H3  -
        
        4 -   -   H4   -   -   -   -
        
        5 -   -   -   -    -   -   D
        
    */  
      
        
      Map<String, Map <String, Double> > vtDistances = 
      Map.of(
                      
             "W", 
      Map.of("H1", W.calculate_Digonal_Heuristic(H1) + TSP.W.getG(),
             "H2", W.calculate_Digonal_Heuristic(H2) + W.getG(),
             "H3", W.calculate_Digonal_Heuristic(H3) + W.getG(),
             "H4", W.calculate_Digonal_Heuristic(H4) + W.getG()),
          
             
              
             "H1", 
      Map.of("H2", H1.calculate_Digonal_Heuristic(H2) + H1.getG(),
             "H3", H1.calculate_Digonal_Heuristic(H3) + H1.getG(),
             "H4", H1.calculate_Digonal_Heuristic(H4) + H1.getG(),
             "W",  H1.calculate_Digonal_Heuristic(W)  + H1.getG()) ,
           
             
             "H2", 
      Map.of("H1", H2.calculate_Digonal_Heuristic(H1) + H2.getG(),
             "H3", H2.calculate_Digonal_Heuristic(H3) + H2.getG(),
             "H4", H2.calculate_Digonal_Heuristic(H4) + H2.getG(),
             "W",  H2.calculate_Digonal_Heuristic(W)  + H2.getG()),
             
              "H3",          
       Map.of("H1", H3.calculate_Digonal_Heuristic(H1) + H3.getG(),
              "H2", H3.calculate_Digonal_Heuristic(H2) + H3.getG(),
              "H4", H3.calculate_Digonal_Heuristic(H4) + H3.getG(),
              "W",  H3.calculate_Digonal_Heuristic(W)  + H3.getG()),
     
       
              "H4",
       Map.of("H1", H4.calculate_Digonal_Heuristic(H1) + H4.getG(),
              "H2", H4.calculate_Digonal_Heuristic(H2) + H4.getG(),
              "H3", H4.calculate_Digonal_Heuristic(H3) + H4.getG(),
              "W" , H4.calculate_Digonal_Heuristic(W)  + H4.getG())
      
      );
      
       Map<String, Map <String, Double> > vt2Distances = 
      Map.of(
                      
             "W", 
      Map.of("H1", W.calculate_Euclidean_Heuristic(H1) + W.getG(),
             "H2", W.calculate_Euclidean_Heuristic(H2) + W.getG(),
             "H3", W.calculate_Euclidean_Heuristic(H3) + W.getG(),
             "H4", W.calculate_Euclidean_Heuristic(H4) + W.getG()),  
              
             "H1", 
      Map.of("H2", H1.calculate_Euclidean_Heuristic(H2) + H1.getG(),
             "H3", H1.calculate_Euclidean_Heuristic(H3) + H1.getG(),
             "H4", H1.calculate_Euclidean_Heuristic(H4) + H1.getG(),
             "W",  H1.calculate_Euclidean_Heuristic(W)  + H1.getG()) ,
           
             
             "H2", 
      Map.of("H1", H2.calculate_Euclidean_Heuristic(H1) + H2.getG(),
             "H3", H2.calculate_Euclidean_Heuristic(H3) + H2.getG(),
             "H4", H2.calculate_Euclidean_Heuristic(H4) + H2.getG(),
             "W",  H2.calculate_Euclidean_Heuristic(W)  + H2.getG()),
             
              "H3",          
       Map.of("H1", H3.calculate_Euclidean_Heuristic(H1) + H3.getG(),
              "H2", H3.calculate_Euclidean_Heuristic(H2) + H3.getG(),
              "H4", H3.calculate_Euclidean_Heuristic(H4) + H3.getG(),
               "W",  H3.calculate_Euclidean_Heuristic(W)  + H3.getG()),
   
       
              "H4",
       Map.of("H1", H4.calculate_Euclidean_Heuristic(H1) + H4.getG(),
              "H2", H4.calculate_Euclidean_Heuristic(H2) + H4.getG(),
              "H3", H4.calculate_Euclidean_Heuristic(H3) + H4.getG(),
               "W" , H4.calculate_Euclidean_Heuristic(W)  + H4.getG())
      
      );
       
         Map<String, Map <String, Double> > vt3Distances = 
      Map.of(
                      
             "W", 
      Map.of("H1", W.calculate_Manhattan_Heuristic(H1) + W.getG(),
             "H2", W.calculate_Manhattan_Heuristic(H2) + W.getG(),
             "H3", W.calculate_Manhattan_Heuristic(H3) + W.getG(),
             "H4", W.calculate_Manhattan_Heuristic(H4) + W.getG()),
           
             
              
             "H1", 
      Map.of("H2", H1.calculate_Manhattan_Heuristic(H2) + H1.getG(),
             "H3", H1.calculate_Manhattan_Heuristic(H3) + H1.getG(),
             "H4", H1.calculate_Manhattan_Heuristic(H4) + H1.getG(),
             "W",  H1.calculate_Manhattan_Heuristic(W)  + H1.getG()) ,
           
             
             "H2", 
      Map.of("H1", H2.calculate_Manhattan_Heuristic(H1) + H2.getG(),
             "H3", H2.calculate_Manhattan_Heuristic(H3) + H2.getG(),
             "H4", H2.calculate_Manhattan_Heuristic(H4) + H2.getG(),
             "W",  H2.calculate_Manhattan_Heuristic(W)  + H2.getG()),
             
              "H3",          
       Map.of("H1", H3.calculate_Manhattan_Heuristic(H1) + H3.getG(),
              "H2", H3.calculate_Manhattan_Heuristic(H2) + H3.getG(),
              "H4", H3.calculate_Manhattan_Heuristic(H4) + H3.getG(),
              "W",  H3.calculate_Manhattan_Heuristic(W)  + H3.getG()),
       
      
              "H4",
       Map.of("H1", H4.calculate_Manhattan_Heuristic(H1) + H4.getG(),
              "H2", H4.calculate_Manhattan_Heuristic(H2) + H4.getG(),
              "H3", H4.calculate_Manhattan_Heuristic(H3) + H4.getG(),
              "W" , H4.calculate_Manhattan_Heuristic(W)  + H4.getG())
      
      );
				
		TSP tsp  = new TSP(vtDistances , "W");
                TSP tsp2 = new TSP(vt2Distances , "W");
                TSP tsp3 = new TSP(vt3Distances , "W");

       
                String[] shortestPath = tsp.findShortestPath();
              
                int distance = tsp.pathDistance(shortestPath)+ tsp.GoToDepote(shortestPath);
               
               System.out.println("The path is " + tsp.NewPath(shortestPath) + " in " +
				distance + " miles." + " With Digonal Heuristic");
                
               
                String[] shortestPath2 = tsp2.findShortestPath();
		int distance2 = tsp2.pathDistance(shortestPath2)+tsp2.GoToDepote(shortestPath2);
              
                
		System.out.println("The path is " + tsp2.NewPath(shortestPath2) + " in " +
				distance2 + " miles." + " With Euclidean Heuristic (Optimal)");
                
                
                
                 String[] shortestPath3 = tsp3.findShortestPath();
		int distance3 = tsp3.pathDistance(shortestPath3)+tsp3.GoToDepote(shortestPath3);
              
                
		System.out.println("The path is " + tsp3.NewPath(shortestPath3) + " in " +
				distance3 + " miles." + " With Manhatten Heuristic");
		
        }
    
}
