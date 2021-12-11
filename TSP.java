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
       

		w.addBranch(15, n1);
		w.addBranch(10, n2);
		w.addBranch(20, n3);
		System.out.println(w.getNeighbor().get(0).f);		//map parameter #2 
		n1.addBranch(15, w);
		n1.addBranch(45, n2);
		n1.addBranch(50, n3);
	}
}
