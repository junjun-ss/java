package exam._09_thread_async;

public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Thread 작업 시작");
            Thread.sleep(2000); // 2초 동안 스레드 일시 정지
            System.out.println("Thread 작업 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); // 스레드 실행
        System.out.println("Main 스레드 실행");
    }
}
