package ch.hslu.ad.sw06.ex03;

public class BoundedBuffer<T> {

    private final int capacity;

    // TODO replace with buffer
    private T value;
    
    // TODO replace with size counter
    private boolean valueSet;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized T get() {
        while (!valueSet) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        T value = this.value;
        this.valueSet = false;
        this.notifyAll();
        return value;
    }

    public synchronized void put(T value) {
        while (valueSet) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        this.valueSet = true;
        this.value = value;
        this.notifyAll();
    }

    public boolean empty() {
        return !valueSet;
    }

    public boolean full() {
        return valueSet;
    }

    public int size() {
        return 1;
    }
}
