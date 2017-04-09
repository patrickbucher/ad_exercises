package ch.hslu.ad.sw07.ex03;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

    private AtomicLong counter;

    public AtomicCounter(long init) {
        counter = new AtomicLong(init);
    }

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public void decrement() {
        counter.decrementAndGet();
    }

    @Override
    public long get() {
        return counter.get();
    }

}
