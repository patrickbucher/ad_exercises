package ch.hslu.ad.sw07.ex05;

import java.util.Collection;
import java.util.concurrent.Callable;

public class NumberRangeProducer implements Callable<Long> {

    private final Collection<Integer> numbers;
    private final int maxRange;

    public NumberRangeProducer(int maxRange, Collection<Integer> numbers) {
        this.numbers = numbers;
        this.maxRange = maxRange;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = 0; i < maxRange; i++) {
            sum += i;
            numbers.add(i);
        }
        return sum;
    }
}
