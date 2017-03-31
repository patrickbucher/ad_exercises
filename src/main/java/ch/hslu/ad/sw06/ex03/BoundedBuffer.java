package ch.hslu.ad.sw06.ex03;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoundedBuffer<T> {

    private final Deque<T> buffer;

    private final int capacity;

    private final Object readLock = new Object();
    private final Object writeLock = new Object();

    public BoundedBuffer(int capacity) {
        buffer = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public T get() {
        synchronized (readLock) {
            if (empty()) {
                try {
                    readLock.wait();
                } catch (InterruptedException e) {
                    System.err.println("interrupted: " + e.getMessage());
                }
            }
            boolean wasFull = full();
            T element = buffer.pop();
            if (wasFull) {
                synchronized (writeLock) {
                    writeLock.notifyAll();
                }
            }
            return element;
        }
    }

    public void put(T element) {
        synchronized (writeLock) {
            if (full()) {
                try {
                    writeLock.wait();
                } catch (InterruptedException e) {
                    System.err.println("interrupted: " + e.getMessage());
                }
            }
            boolean wasEmpty = empty();
            buffer.addLast(element);
            if (wasEmpty) {
                synchronized (readLock) {
                    readLock.notifyAll();
                }
            }
        }
    }

    public boolean empty() {
        return buffer.size() == 0;
    }

    public boolean full() {
        return buffer.size() == capacity;
    }

    public int size() {
        return buffer.size();
    }
}
