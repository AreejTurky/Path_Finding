package Folder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TSP2 {
	private final Map<Node, Map<Node, Integer> > distances;     //map of map 

	public TSP2(Map<Node, Map<Node, Integer> > distances) {
		this.distances = distances;
	}

	public static <T> void swap(T[] array, int first, int second) {
		T temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}

	private static <T> void allPermutationsHelper(T[] permutation, List<T[]> permutations, int n) {
		// Base case - we found a new permutation, add it and return
		if (n <= 0) {
			permutations.add(permutation);
			return;
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

	public int pathDistance(Node[] path) {
		Node last = path[0];
		int distance = 0;
		for (Node next : Arrays.copyOfRange(path, 1, path.length)) {
			distance += distances.get(last).get(next);
			// distance to get back from last city to first city
			last = next;
		}
		return distance;
	}

	public Node[] findShortestPath() {
		Node[] cities = distances.keySet();
		List<Node[]> paths = permutations(cities);
		Node[] shortestPath = null;
		int minDistance = Integer.MAX_VALUE; // arbitrarily high
		for (Node[] path : paths) {
			int distance = pathDistance(path);
			// distance from last to first must be added
			distance += distances.get(path[path.length - 1]).get(path[0]);
			if (distance < minDistance) {
				minDistance = distance;
				shortestPath = path;
			}
		}
		// add first city on to end and return
		shortestPath = Arrays.copyOf(shortestPath, shortestPath.length + 1);
		shortestPath[shortestPath.length - 1] = shortestPath[0];
		return shortestPath;
	}

	public static void main(String[] args) {
        Node n1= new Node(0 , 0);
        Node n2= new Node(1 , 0);
        Node n3= new Node(0 , 1);
        Node n4= new Node(1 , 2);

        Map<Node, Map<Node, Integer>> vtDistances = Map.of(
				n1, Map.of(
				n2, n1.calculate_Manhattan_Heuristic(n2),
				n3, n1.calculate_Manhattan_Heuristic(n3),
				n4, n1.calculate_Manhattan_Heuristic(n4)
						),
                n2, Map.of(
                n1, n2.calculate_Manhattan_Heuristic(n1),
                n3, n2.calculate_Manhattan_Heuristic(n3),
                n4, n2.calculate_Manhattan_Heuristic(n4)
                        ),
                n3, Map.of(
                n1, n3.calculate_Manhattan_Heuristic(n1),
                n2, n3.calculate_Manhattan_Heuristic(n2),
                n4, n3.calculate_Manhattan_Heuristic(n4)
                        ),
                n4, Map.of(
                n1, n4.calculate_Manhattan_Heuristic(n1),
                n2, n4.calculate_Manhattan_Heuristic(n2),
                n3, n4.calculate_Manhattan_Heuristic(n3)
                        )
                            );
				// "Burlington", Map.of(
				// 		"Rutland", 67,
				// 		"White River Junction", 91,
				// 		"Bennington", 122,
				// 		"Brattleboro", 153),
				// "White River Junction", Map.of(
				// 		"Rutland", 46,
				// 		"Burlington", 91,
				// 		"Bennington", 98,
				// 		"Brattleboro", 65),
				// "Bennington", Map.of(
				// 		"Rutland", 55,
				// 		"Burlington", 122,
				// 		"White River Junction", 98,
				// 		"Brattleboro", 40),
				// "Brattleboro", Map.of(
				// 		"Rutland", 75,
				// 		"Burlington", 153,
				// 		"White River Junction", 65,
				// 		"Bennington", 40));
		TSP2 tsp = new TSP2(vtDistances);
		Node[] shortestPath = tsp.findShortestPath();
		int distance = tsp.pathDistance(shortestPath);
		System.out.println("The shortest path is " + Arrays.toString(shortestPath) + " in " +
				distance + " miles.");
	}
}