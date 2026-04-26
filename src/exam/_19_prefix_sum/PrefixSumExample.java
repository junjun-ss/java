package exam._19_prefix_sum;

public class PrefixSumExample {
    public static void main(String[] args) {
        oneDimensionalPrefixSum();
        twoDimensionalPrefixSum();
    }

    private static void oneDimensionalPrefixSum() {
        int[] arr = {0, 1, 2, 3, 4, 5};
        int[] prefix = new int[arr.length];

        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        System.out.println(rangeSum(prefix, 2, 4));
    }

    private static int rangeSum(int[] prefix, int left, int right) {
        return prefix[right] - prefix[left - 1];
    }

    private static void twoDimensionalPrefixSum() {
        int[][] map = {
            {0, 0, 0, 0},
            {0, 1, 2, 3},
            {0, 4, 5, 6},
            {0, 7, 8, 9}
        };

        int n = 3;
        int[][] prefix = new int[n + 1][n + 1];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                prefix[r][c] = map[r][c]
                    + prefix[r - 1][c]
                    + prefix[r][c - 1]
                    - prefix[r - 1][c - 1];
            }
        }

        System.out.println(rectSum(prefix, 2, 2, 3, 3));
    }

    private static int rectSum(int[][] prefix, int r1, int c1, int r2, int c2) {
        return prefix[r2][c2]
            - prefix[r1 - 1][c2]
            - prefix[r2][c1 - 1]
            + prefix[r1 - 1][c1 - 1];
    }
}
