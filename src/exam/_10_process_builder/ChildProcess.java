package exam._10_process_builder;

public class ChildProcess {
    public static void main(String[] args) throws InterruptedException {
        int count = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        for (int i = 1; i <= count; i++) {
            System.out.println("Child Process: " + i);
        }
    }
}
