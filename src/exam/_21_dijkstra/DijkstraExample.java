package exam._21_dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraExample {
    private static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) {
        List<Edge>[] graph = new ArrayList[6];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 1, 2, 2L);
        addEdge(graph, 1, 3, 5L);
        addEdge(graph, 2, 3, 1L);
        addEdge(graph, 2, 4, 2L);
        addEdge(graph, 3, 5, 3L);
        addEdge(graph, 4, 5, 1L);

        long[] dist = dijkstra(graph, 1);
        System.out.println(Arrays.toString(dist));
    }

    public static void addEdge(List<Edge>[] graph, int from, int to, long cost) {
        graph[from].add(new Edge(to, cost));
    }

    public static long[] dijkstra(List<Edge>[] graph, int start) {
        long[] dist = new long[graph.length];
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
                long nextCost = current.cost + edge.cost;

                if (nextCost < dist[edge.to]) {
                    dist[edge.to] = nextCost;
                    pq.offer(new Node(edge.to, nextCost));
                }
            }
        }

        return dist;
    }

    public static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static class Node implements Comparable<Node> {
        int vertex;
        long cost;

        public Node(int vertex, long cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }
}
