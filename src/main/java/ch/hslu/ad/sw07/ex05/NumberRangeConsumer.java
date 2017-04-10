package ch.hslu.ad.sw07.ex05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class NumberRangeConsumer implements Callable<Long> {

    private final BlockingQueue<Integer> numbers;

    public NumberRangeConsumer(BlockingQueue<Integer> numbers) {
        this.numbers = numbers;
    }

    // FIXME: this is broken!
    @Override
    public Long call() throws Exception {
        long sum = 0;
        Integer tmp;
        while ((tmp = numbers.poll(100, TimeUnit.MICROSECONDS)) != null) {
            sum += tmp;
        }
        return sum;
    }
}
