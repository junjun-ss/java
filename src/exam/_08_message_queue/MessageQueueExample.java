package exam._08_message_queue;

public class MessageQueueExample {
    private static final String POISON_PILL = "DONE";

    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        Thread producerThread = new Thread(() -> produce(messageQueue, 5));
        Thread consumerThread = new Thread(() -> consumeUntilPoisonPill(messageQueue));

        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

    public static void produce(MessageQueue queue, int messageCount) {
        for (int i = 1; i <= messageCount; i++) {
            String message = "Message " + i;
            queue.enqueue(message);
            System.out.println("Produced: " + message);
        }
        queue.enqueue(POISON_PILL);
    }

    public static void consumeUntilPoisonPill(MessageQueue queue) {
        try {
            while (true) {
                String message = queue.dequeue();
                if (POISON_PILL.equals(message)) {
                    System.out.println("Consumer 종료");
                    return;
                }
                System.out.println("Consumed: " + message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("consumer interrupted", e);
        }
    }
}
