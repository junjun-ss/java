package exam._10_process_builder;

public class ChildProcess {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Child Process: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
