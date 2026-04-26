package exam._10_process_builder;

public class BThreadClass implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 120; i++) {
            System.out.println("B Thread " + Thread.currentThread().getId() + ": " + i);
        }
    }
}
