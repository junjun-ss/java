package exam._09_thread_async;

import java.io.*;

public class ThreadPipeExample {
    public static void main(String[] args) {
        try {
            // 파이프 생성
            PipedInputStream pipeInput = new PipedInputStream();
            PipedOutputStream pipeOutput = new PipedOutputStream(pipeInput);

            // 프로세스 1 실행
            Thread thread1 = new Thread(new Process1(pipeOutput));
            thread1.start();

            // 프로세스 2 실행
            Thread thread2 = new Thread(new Process2(pipeInput));
            thread2.start();

            // 각 프로세스의 실행이 완료될 때까지 대기
            thread1.join();
            thread2.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Process1 implements Runnable {
    private final PipedOutputStream output;

    public Process1(PipedOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        try {
            // 작업 수행
            int result = 42;

            // 결과 값을 파이프로 전송
            output.write(result);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Process2 implements Runnable {
    private final PipedInputStream input;

    public Process2(PipedInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            // 파이프로부터 결과 값을 읽음
            int result = input.read();

            // 결과 값 출력
            System.out.println("Process 2에서 받은 결과: " + result);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
