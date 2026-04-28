package exam._19_prefix_sum;

public class PrefixSumExample {
    public static void main(String[] args) {
        int[] prefix = buildPrefixSum(new int[] {1, 2, 3, 4, 5});
        System.out.println(rangeSum(prefix, 2, 4));

        int[][] prefix2d = buildPrefixSum2D(new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        System.out.println(rectSum(prefix2d, 2, 2, 3, 3));
    }

    public static int[] buildPrefixSum(int[] arr) {
        int[] prefix = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        return prefix;
    }

    public static int rangeSum(int[] prefix, int left, int right) {
        return prefix[right] - prefix[left - 1];
    }

    public static int[][] buildPrefixSum2D(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        int[][] prefix = new int[rows + 1][cols + 1];

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                prefix[r][c] = map[r - 1][c - 1]
                    + prefix[r - 1][c]
                    + prefix[r][c - 1]
                    - prefix[r - 1][c - 1];
            }
        }
        return prefix;
    }

    public static int rectSum(int[][] prefix, int r1, int c1, int r2, int c2) {
        return prefix[r2][c2]
            - prefix[r1 - 1][c2]
            - prefix[r2][c1 - 1]
            + prefix[r1 - 1][c1 - 1];
    }
}
