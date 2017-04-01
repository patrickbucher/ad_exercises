package ch.hslu.ad.sw06.ex03;

public class BoundedBuffer<T> {

    private final int capacity;

    private Object[] data;

    private int size;
    private int read;
    private int write;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public synchronized T get() {
        while (empty()) {
            System.out.println("buffer is empty, get() has to wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        boolean wasFull = full();
        int index = read % capacity;
        T element = (T) data[index];
        read = index + 1;
        size--;
        if (wasFull) {
            System.out.println("buffer is not full any longer, put() can continue");
            this.notifyAll();
        }
        return element;
    }

    public synchronized void put(T value) {
        while (full()) {
            System.out.println("buffer is full, put() has to wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        boolean wasEmpty = empty();
        int index = write % capacity;
        data[index] = value;
        write = index + 1;
        size++;
        if (wasEmpty) {
            System.out.println("buffer is not empty any longer, get() can continue");
            this.notifyAll();
        }
    }

    public boolean empty() {
        return size == 0;
    }

    public boolean full() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}
