package ch.hslu.ad.sw02.ex04;

public class Queue {
    public static final int QUEUE_SIZE = 8;

    private String queue[] = new String[QUEUE_SIZE];

    private int head = 0;
    private int tail = 0;

    public void enqueue(String element) {
        if (tail - head < QUEUE_SIZE) {
            correctIndices();
            queue[tail % (queue.length - 1)] = element;
            tail++;
        } else {
            throw new IllegalStateException("the queue is full");
        }
    }

    public String dequeue() {
        if (tail - head > 0) {
            correctIndices();
            int index = head % (queue.length - 1);
            String element = queue[index];
            queue[index] = null;
            head++;
            return element;
        } else {
            throw new IllegalStateException("the queue is empty");
        }
    }

    private void correctIndices() {
        if (head >= QUEUE_SIZE && tail >= QUEUE_SIZE) {
            head -= (QUEUE_SIZE - 1);
            tail -= (QUEUE_SIZE - 1);
        }
    }
}
