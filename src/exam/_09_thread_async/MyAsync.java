package exam._09_thread_async;

import java.util.concurrent.CompletableFuture;

public class MyAsync {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Async 작업 시작");
                Thread.sleep(2000); // 2초 동안 비동기 작업 수행
                System.out.println("Async 작업 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        future.thenRun(() -> {
            System.out.println("비동기 작업 완료 후 실행");
        });

        System.out.println("Main 스레드 실행");

        try {
            Thread.sleep(3000); // 3초 동안 메인 스레드 일시 정지
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
