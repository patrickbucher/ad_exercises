package ch.hslu.ad.div.ringBuffer;

public class RingBuffer<T> {

    private Object[] ring;

    private final int capacity;

    private int size = 0;
    private int write = 0;
    private int read = 0;

    public RingBuffer(int capacity) {
        ring = new Object[capacity];
        this.capacity = capacity;
    }

    public void put(T element) {
        if (isFull()) {
            throw new IllegalStateException("RingBuffer is empty");
        }
        int index = write % capacity;
        ring[index] = element;
        write = index + 1;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (isEmpty()) {
            throw new IllegalStateException("RingBuffer is empty");
        }
        int index = read % capacity;
        T element = (T) ring[index];
        read = index + 1;
        size--;
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
