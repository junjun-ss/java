package exam._09_thread_async;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadPipeExample {
    public static void main(String[] args) throws Exception {
        pipeIntValue(42);
    }

    public static void pipeIntValue(int value) throws IOException, InterruptedException {
        PipedInputStream pipeInput = new PipedInputStream();
        PipedOutputStream pipeOutput = new PipedOutputStream(pipeInput);

        Thread writer = new Thread(new PipeWriter(pipeOutput, value));
        Thread reader = new Thread(new PipeReader(pipeInput));
        writer.start();
        reader.start();
        writer.join();
        reader.join();
    }
}

class PipeWriter implements Runnable {
    private final PipedOutputStream output;
    private final int value;

    public PipeWriter(PipedOutputStream output, int value) {
        this.output = output;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            output.write(value);
            output.close();
        } catch (IOException e) {
            throw new IllegalStateException("pipe write 실패", e);
        }
    }
}

class PipeReader implements Runnable {
    private final PipedInputStream input;

    public PipeReader(PipedInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            int result = input.read();
            System.out.println("파이프로 받은 값: " + result);
            input.close();
        } catch (IOException e) {
            throw new IllegalStateException("pipe read 실패", e);
        }
    }
}
