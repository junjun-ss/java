package exam._20_union_find;

public class UnionFindExample {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(1, 2);
        uf.union(3, 4);
        System.out.println(uf.isConnected(1, 2));
        System.out.println(uf.isConnected(1, 3));
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }

        boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }
}
