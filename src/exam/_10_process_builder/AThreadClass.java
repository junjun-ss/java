package exam._10_process_builder;

public class AThreadClass implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("A Thread " + Thread.currentThread().getId() + ": " + i);
        }
    }
}
