package exam._23_backtracking;

import java.util.Arrays;

public class BacktrackingExample {
    private static int n = 3;
    private static int r = 2;
    private static int[] selected = new int[r];
    private static boolean[] visited = new boolean[n + 1];

    public static void main(String[] args) {
        System.out.println("permutation");
        permutation(0);

        System.out.println("combination");
        combination(1, 0);

        System.out.println("duplicated permutation");
        duplicatedPermutation(0);
    }

    private static void permutation(int depth) {
        if (depth == r) {
            System.out.println(Arrays.toString(selected));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[depth] = i;
            permutation(depth + 1);
            visited[i] = false;
        }
    }

    private static void combination(int start, int depth) {
        if (depth == r) {
            System.out.println(Arrays.toString(selected));
            return;
        }

        for (int i = start; i <= n; i++) {
            selected[depth] = i;
            combination(i + 1, depth + 1);
        }
    }

    private static void duplicatedPermutation(int depth) {
        if (depth == r) {
            System.out.println(Arrays.toString(selected));
            return;
        }

        for (int i = 1; i <= n; i++) {
            selected[depth] = i;
            duplicatedPermutation(depth + 1);
        }
    }
}
