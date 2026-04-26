package exam._22_dynamic_programming;

import java.util.Arrays;

public class DynamicProgrammingExample {
    public static void main(String[] args) {
        fibonacciExample();
        oneDimensionalDpExample();
        twoDimensionalDpExample();
    }

    private static void fibonacciExample() {
        int n = 10;
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }

    private static void oneDimensionalDpExample() {
        int[] scores = {0, 10, 20, 15, 25, 10, 20};
        int n = scores.length - 1;
        int[] dp = new int[n + 1];

        dp[1] = scores[1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + scores[i]);
        }

        System.out.println(dp[n]);
    }

    private static void twoDimensionalDpExample() {
        String a = "ABCBDAB";
        String b = "BDCABA";
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[a.length()][b.length()]);
        System.out.println(Arrays.deepToString(dp));
    }
}
