import java.util.*;

public class Question5B {

    public static void dfs(Map<Integer, List<Integer>> graph, int device, Set<Integer> visited, Set<Integer> impactedDevices) {
        visited.add(device);
        impactedDevices.add(device);
        for (int neighbor : graph.getOrDefault(device, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, impactedDevices);
            }
        }
    }

    public static Set<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> impactedDevices = new HashSet<>();
        dfs(graph, targetDevice, visited, impactedDevices);
        impactedDevices.remove(targetDevice); // Remove the target device itself

        return impactedDevices;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}};
        int targetDevice = 4;

        Set<Integer> result = findImpactedDevices(edges, targetDevice);
        System.out.println("Impacted Device List: " + result);
    }
}
