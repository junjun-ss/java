package exam._09_thread_async;

import java.util.concurrent.CompletableFuture;

public class MyAsync {
    public static void main(String[] args) {
        CompletableFuture<String> future = runAsyncJob("exam");

        future.thenAccept(result -> System.out.println("비동기 결과: " + result)).join();
    }

    public static CompletableFuture<String> runAsyncJob(String input) {
        return CompletableFuture.supplyAsync(() -> input.toUpperCase() + "-DONE");
    }
}
