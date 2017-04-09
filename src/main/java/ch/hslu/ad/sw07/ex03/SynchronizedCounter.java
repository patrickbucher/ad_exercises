package ch.hslu.ad.sw07.ex03;

public class SynchronizedCounter implements Counter {

    private long counter;

    public SynchronizedCounter(long init) {
        this.counter = init;
    }

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public synchronized void decrement() {
        counter--;
    }

    @Override
    public synchronized long get() {
        return counter;
    }

}
