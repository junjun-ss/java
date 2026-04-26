package exam._10_process_builder;

public class BProcessClass {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new BThreadClass());
        Thread thread2 = new Thread(new BThreadClass());
        Thread thread3 = new Thread(new BThreadClass());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

