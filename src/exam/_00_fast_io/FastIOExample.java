package exam._00_fast_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastIOExample {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int n = fs.nextInt();
        for (int i = 0; i < n; i++) {
            int value = fs.nextInt();
            out.append(value * 2).append('\n');
        }

        System.out.print(out.toString());
    }

    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) {
                    return null;
                }
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(requireNext());
        }

        long nextLong() throws IOException {
            return Long.parseLong(requireNext());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(requireNext());
        }

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        private String requireNext() throws IOException {
            String token = next();
            if (token == null) {
                throw new IOException("더 이상 읽을 입력이 없습니다.");
            }
            return token;
        }
    }
}
