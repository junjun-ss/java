package exam._22_dynamic_programming;

public class DynamicProgrammingExample {
    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(maxNonAdjacentSum(new int[] {10, 20, 15, 25, 10, 20}));
        System.out.println(lcsLength("ABCBDAB", "BDCABA"));
    }

    public static int fibonacci(int n) {
        int[] dp = new int[n + 1];
        if (n >= 1) {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int maxNonAdjacentSum(int[] scores) {
        int n = scores.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int take = dp[Math.max(0, i - 2)] + scores[i - 1];
            dp[i] = Math.max(dp[i - 1], take);
        }
        return dp[n];
    }

    public static int lcsLength(String a, String b) {
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

        return dp[a.length()][b.length()];
    }
}
