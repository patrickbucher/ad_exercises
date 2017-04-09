package ch.hslu.ad.sw07.ex03;

public class UnsynchronizedCounter implements Counter {

    private long counter;

    public UnsynchronizedCounter(long init) {
        this.counter = init;
    }

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public void decrement() {
        counter--;
    }

    @Override
    public long get() {
        return counter;
    }

}
