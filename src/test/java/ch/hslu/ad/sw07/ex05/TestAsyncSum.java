package ch.hslu.ad.sw07.ex05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * FIXME this test, or the NumberRangeConsumer, respectively, is broken.
 */
public class TestAsyncSum {

    private static final int N_PRODUCERS = 2;
    private static final int MAX_RANGE = 1000;

    private List<NumberRangeProducer> producers;
    private NumberRangeConsumer consumer;

    private List<Future<Long>> producerFutures;
    private Future<Long> consumerFuture;

    @Before
    public void initProducerConsumer() {
        Random random = new Random(System.currentTimeMillis());
        BlockingQueue<Integer> numbers = new ArrayBlockingQueue<>(MAX_RANGE * N_PRODUCERS);
        producers = new ArrayList<>(N_PRODUCERS);
        for (int i = 0; i < N_PRODUCERS; i++) {
            producers.add(new NumberRangeProducer(random.nextInt(MAX_RANGE), numbers));
        }
        consumer = new NumberRangeConsumer(numbers);
        producerFutures = new ArrayList<>(N_PRODUCERS);
    }

    @Test
    public void testSameEndResult() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        consumerFuture = executor.submit(consumer);
        for (NumberRangeProducer producer : producers) {
            producerFutures.add(executor.submit(producer));
        }
        long producerSum = 0;
        for (Future<Long> future : producerFutures) {
            try {
                producerSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
        }
        long consumerSum = 0;
        try {
            consumerSum = consumerFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(producerSum, consumerSum);
    }
}
