package exam._09_thread_async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    public static void main(String[] args) {
        System.out.println(sumTwoTasks());
    }

    public static int sumTwoTasks() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> sumRange(1, 50), executor);
            CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> sumRange(51, 100), executor);

            return first.thenCombine(second, Integer::sum).join();
        } finally {
            executor.shutdown();
        }
    }

    public static int sumRange(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
