package exam._16_bfs_dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BfsDfsExample {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) {
        graphBfsDfsExample();
        gridBfsExample();
    }

    public static void graphBfsDfsExample() {
        List<Integer>[] graph = new ArrayList[5];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);

        System.out.println(dfsOrder(1, graph));
        System.out.println(bfsOrder(1, graph));
    }

    public static void addEdge(List<Integer>[] graph, int a, int b) {
        graph[a].add(b);
        graph[b].add(a);
    }

    public static List<Integer> dfsOrder(int start, List<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        List<Integer> order = new ArrayList<>();
        dfs(start, graph, visited, order);
        return order;
    }

    private static void dfs(int node, List<Integer>[] graph, boolean[] visited, List<Integer> order) {
        visited[node] = true;
        order.add(node);

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited, order);
            }
        }
    }

    public static List<Integer> bfsOrder(int start, List<Integer>[] graph) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);

            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return order;
    }

    public static void gridBfsExample() {
        int[][] grid = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 1}
        };

        int[][] distance = bfsGrid(grid, 0, 0);
        for (int[] row : distance) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] bfsGrid(int[][] grid, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startRow, startCol});
        distance[startRow][startCol] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = row + DR[d];
                int nc = col + DC[d];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                    continue;
                }
                if (grid[nr][nc] == 0 || distance[nr][nc] != -1) {
                    continue;
                }

                distance[nr][nc] = distance[row][col] + 1;
                queue.offer(new int[] {nr, nc});
            }
        }

        return distance;
    }
}
