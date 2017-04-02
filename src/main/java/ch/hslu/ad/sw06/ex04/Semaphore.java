package ch.hslu.ad.sw06.ex04;

public class Semaphore {

    private final int limit;

    private int semaphore;

    public Semaphore() {
        this.limit = Integer.MAX_VALUE;
        this.semaphore = 0;
    }

    public Semaphore(int init, int limit) throws IllegalArgumentException {
        if (init > limit) {
            throw new IllegalArgumentException("init must not be greater than limit");
        }
        this.limit = limit;
        this.semaphore = init;
    }

    public synchronized int acquire() throws InterruptedException {
        while (semaphore == 0) {
            this.wait();
        }
        semaphore--;
        return semaphore;
    }

    public synchronized int acquire(int permits) throws InterruptedException {
        while (permits > 0) {
            while (semaphore == 0) {
                this.wait();
            }
            semaphore--;
            permits--;
        }
        return semaphore;
    }

    public synchronized int release() {
        if (semaphore == limit) {
            throw new IllegalStateException("can't release more than " + limit);
        }
        this.notifyAll();
        semaphore++;
        return semaphore;
    }

    public synchronized int release(int permits) {
        if (permits > limit) {
            throw new IllegalArgumentException("permits is greater than limit");
        }
        if (semaphore + permits > limit) {
            throw new IllegalStateException("can't release more than " + limit);
        }
        while (permits > 0) {
            this.notifyAll();
            semaphore++;
            permits--;
        }
        return semaphore;
    }

    public int getFree() {
        return semaphore;
    }
}
