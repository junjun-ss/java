package exam._08_message_queue;

public class MessageQueueExample {
    private static final String POISON_PILL = "DONE";

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        // Producer 스레드
        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                String message = "Message " + i;
                messageQueue.enqueue(message);
                System.out.println("Produced: " + message);
                try {
                    Thread.sleep(1000); // 일부러 속도를 늦춤
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageQueue.enqueue(POISON_PILL);
        });

        // Consumer 스레드
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    String message = messageQueue.dequeue();
                    if (POISON_PILL.equals(message)) {
                        System.out.println("Consumer 종료");
                        break;
                    }

                    System.out.println("Consumed: " + message);
                    Thread.sleep(2000); // 일부러 속도를 늦춤
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
