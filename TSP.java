package Folder;

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

	public static void main(String[] args) {
        Node w= new Node(0, 0);
        Node n1= new Node(1 , 1);
        Node n2= new Node(1 , 2);
        Node n3= new Node(2 , 3);
        Node D= new Node(3 , 3);
       
        List<Node> sn= new ArrayList<>();
        List<Node> n1n= new ArrayList<>();
        List<Node> n2n= new ArrayList<>();
        List<Node> n3n= new ArrayList<>();

		
/* 				INITIALIZING SET OF NEIGHBORS 					*/
		sn.add(n1);
		sn.add(n2);
		sn.add(n3);
		
		n1n.add(w);
		n1n.add(n2);
		n1n.add(n3);
		
		n2n.add(w);
		n2n.add(n1);
		n2n.add(n3);

		n3n.add(w);
		n3n.add(n1);
		n3n.add(n2);
/* 				SETTING UP THESE NEIGHBORS e.g., DISTANCE AND HEURISICS				*/
		w.setNeighbors(sn);
		w.neighbors.get(0).setG(15);
		w.neighbors.get(0).setH(w.calculate_Manhattan_Heuristic(n1));
		w.neighbors.get(1).setG(10);
		w.neighbors.get(1).setH(w.calculate_Manhattan_Heuristic(n2));
		w.neighbors.get(2).setG(20);
		w.neighbors.get(2).setH(w.calculate_Manhattan_Heuristic(n3));

		n1.setNeighbors(n1n);
		n1.neighbors.get(0).setG(15);
		n1.neighbors.get(0).setH(n1.calculate_Manhattan_Heuristic(w));
		n1.neighbors.get(1).setG(10);
		n1.neighbors.get(1).setH(n1.calculate_Manhattan_Heuristic(n2));
		n1.neighbors.get(2).setG(20);
		n1.neighbors.get(2).setH(n1.calculate_Manhattan_Heuristic(n3));

		n2.setNeighbors(n2n);
		n2.neighbors.get(0).setG(20);
		n2.neighbors.get(0).setH(n2.calculate_Manhattan_Heuristic(w));
		n2.neighbors.get(1).setG(10);
		n2.neighbors.get(1).setH(n2.calculate_Manhattan_Heuristic(n1));
		n2.neighbors.get(2).setG(20);
		n2.neighbors.get(2).setH(n2.calculate_Manhattan_Heuristic(n3));

		n3.setNeighbors(n3n);
		n3.neighbors.get(0).setG(10);
		n3.neighbors.get(0).setH(n3.calculate_Manhattan_Heuristic(w));
		n3.neighbors.get(1).setG(20);
		n3.neighbors.get(1).setH(n3.calculate_Manhattan_Heuristic(n1));
		n3.neighbors.get(2).setG(20);
		n3.neighbors.get(2).setH(n3.calculate_Manhattan_Heuristic(n2));


		Map<String, Map <String, Double> > vtDistances = Map.of(
		"w", Map.of(
		"n1", w.getNeighbors().get(0).getF(),
		"n2", w.getNeighbors().get(1).getF(),
		"n3", n1.getNeighbors().get(2).getF()
		),
		"n1", Map.of(
        "w",  n1.getNeighbors().get(0).getF(),
		"n2", n1.getNeighbors().get(1).getF(),
        "n3", n1.getNeighbors().get(2).getF()
        ),
        "n2", Map.of(
		"w",  n2.getNeighbors().get(0).getF(),
        "n1", n2.getNeighbors().get(1).getF(),
        "n3", n2.getNeighbors().get(2).getF()
        ),
        "n3", Map.of(
		"w",  n3.getNeighbors().get(0).getF(),
        "n1", n3.getNeighbors().get(1).getF(),
        "n2", n3.getNeighbors().get(2).getF()
        )
        );
				
		TSP tsp = new TSP(vtDistances , "w");

		String[] shortestPath = tsp.findShortestPath();
		int distance = tsp.pathDistance(shortestPath);
		System.out.println("The shortest path is " + Arrays.toString(shortestPath) + " in " +
				distance + " miles.");
		
	}
}
