package exam._09_thread_async;

public class MyThread extends Thread {
    private final String taskName;

    public MyThread() {
        this("default-task");
    }

    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " 작업 시작");
        System.out.println(taskName + " 작업 완료");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("sample");
        thread.start(); // 스레드 실행
        thread.join();
        System.out.println("Main 스레드 실행");
    }
}
