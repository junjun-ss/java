package exam._10_process_builder;

public class AProcessClass {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new AThreadClass());
        Thread thread2 = new Thread(new AThreadClass());
        
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("A process interrupted", e);
        }
    }
}
