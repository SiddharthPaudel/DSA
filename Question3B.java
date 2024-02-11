import java.util.*;

public class Question3B{
    
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static List<Edge> minimumSpanningTree(int V, List<Edge> edges) {
        List<Edge> MST = new ArrayList<>();
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Collections.sort(edges);

        for (Edge edge : edges) {
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            if (srcParent != destParent) {
                MST.add(edge);
                union(parent, srcParent, destParent);
            }
        }

        return MST;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == -1)
            return x;
        return parent[x] = find(parent, parent[x]);
    }

    static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        parent[rootX] = rootY;
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> MST = minimumSpanningTree(V, edges);
        for (Edge edge : MST) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }
    }
}
