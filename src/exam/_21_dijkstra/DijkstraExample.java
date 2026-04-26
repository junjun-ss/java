package exam._21_dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraExample {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        List<Edge>[] graph = new ArrayList[6];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 1, 2, 2);
        addEdge(graph, 1, 3, 5);
        addEdge(graph, 2, 3, 1);
        addEdge(graph, 2, 4, 2);
        addEdge(graph, 3, 5, 3);
        addEdge(graph, 4, 5, 1);

        int[] dist = dijkstra(graph, 1);
        System.out.println(Arrays.toString(dist));
    }

    private static void addEdge(List<Edge>[] graph, int from, int to, int cost) {
        graph[from].add(new Edge(to, cost));
    }

    private static int[] dijkstra(List<Edge>[] graph, int start) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.vertex]) {
                continue;
            }

            for (Edge edge : graph[current.vertex]) {
                int nextCost = current.cost + edge.cost;

                if (nextCost < dist[edge.to]) {
                    dist[edge.to] = nextCost;
                    pq.offer(new Node(edge.to, nextCost));
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
}
