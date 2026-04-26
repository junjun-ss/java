package exam._09_thread_async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> asyncTask1 = CompletableFuture.runAsync(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Async Thread 1: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, executor);

        CompletableFuture<Void> asyncTask2 = CompletableFuture.runAsync(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Async Thread 2: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, executor);

        CompletableFuture.allOf(asyncTask1, asyncTask2)
                .thenRunAsync(() -> System.out.println("All tasks completed."))
                .join();

        executor.shutdown();
    }
}
