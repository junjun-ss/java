package exam._08_message_queue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<String> queue;

    public MessageQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(String message) {
        queue.add(message);
        notifyAll(); // 대기중인 스레드들에게 알림
    }

    public synchronized String dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // 큐가 비어있으면 대기
        }
        return queue.poll();
    }
}
