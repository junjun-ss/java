package exam._23_backtracking;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingExample {
    public static void main(String[] args) {
        System.out.println(permutations(3, 2));
        System.out.println(combinations(3, 2));
        System.out.println(repeatedPermutations(3, 2));
    }

    public static List<List<Integer>> permutations(int n, int r) {
        List<List<Integer>> result = new ArrayList<>();
        permutation(n, r, 0, new int[r], new boolean[n + 1], result);
        return result;
    }

    private static void permutation(int n, int r, int depth, int[] selected, boolean[] visited, List<List<Integer>> result) {
        if (depth == r) {
            result.add(toList(selected));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[depth] = i;
            permutation(n, r, depth + 1, selected, visited, result);
            visited[i] = false;
        }
    }

    public static List<List<Integer>> combinations(int n, int r) {
        List<List<Integer>> result = new ArrayList<>();
        combination(n, r, 1, 0, new int[r], result);
        return result;
    }

    private static void combination(int n, int r, int start, int depth, int[] selected, List<List<Integer>> result) {
        if (depth == r) {
            result.add(toList(selected));
            return;
        }

        for (int i = start; i <= n; i++) {
            selected[depth] = i;
            combination(n, r, i + 1, depth + 1, selected, result);
        }
    }

    public static List<List<Integer>> repeatedPermutations(int n, int r) {
        List<List<Integer>> result = new ArrayList<>();
        repeatedPermutation(n, r, 0, new int[r], result);
        return result;
    }

    private static void repeatedPermutation(int n, int r, int depth, int[] selected, List<List<Integer>> result) {
        if (depth == r) {
            result.add(toList(selected));
            return;
        }

        for (int i = 1; i <= n; i++) {
            selected[depth] = i;
            repeatedPermutation(n, r, depth + 1, selected, result);
        }
    }

    private static List<Integer> toList(int[] selected) {
        List<Integer> values = new ArrayList<>();
        for (int value : selected) {
            values.add(value);
        }
        return values;
    }
}
