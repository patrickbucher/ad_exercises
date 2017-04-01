package ch.hslu.ad.sw06.ex03;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BoundedBufferTest {

    static final int BUFFER_CAPACITY = 100;

    @Test
    public void testSync() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        Assert.assertTrue(buf.empty());

        // fill up buffer
        for (int n = 0; n < BUFFER_CAPACITY; n++) {
            buf.put("String" + n);
        }

        Assert.assertEquals(BUFFER_CAPACITY, buf.size());
        Assert.assertTrue(buf.full());
        Assert.assertFalse(buf.empty());

        // empty buffer
        for (int n = 0; n < BUFFER_CAPACITY; n++) {
            Assert.assertEquals("String" + n, buf.get());
        }
        Assert.assertTrue(buf.empty());
    }

    @Test
    public void testProducerConsumer() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                    String str = "String" + n;
                    buf.put(str);
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                    String str = "String" + n;
                    Assert.assertEquals(str, buf.get());
                }
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testProducerTwoConsumers() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                    String str = "String" + n;
                    buf.put(str);
                }
            }
        });
        Runnable consumerRunnable = new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY / 2; n++) {
                    Assert.assertNotNull(buf.get());
                }
            }
        };
        Thread consumer1 = new Thread(consumerRunnable);
        Thread consumer2 = new Thread(consumerRunnable);
        producer.start();
        consumer1.start();
        consumer2.start();
        try {
            producer.join();
            consumer1.join();
            consumer2.join();
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testMProducersNConsumers() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        final int nProducers = 10;
        final int nConsumers = 10;
        List<Thread> producers = new ArrayList<>(nProducers);
        List<Thread> consumers = new ArrayList<>(nConsumers);
        Runnable producerRunnable = new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BUFFER_CAPACITY / nProducers; n++) {
                    buf.put("String" + n);
                }
            }
        };
        Runnable consumerRunnable = new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < BUFFER_CAPACITY / nConsumers; n++) {
                    Assert.assertNotEquals(0, buf.get().length());
                }
            }
        };
        for (int n = 0; n < nProducers; n++) {
            producers.add(new Thread(producerRunnable));
        }
        for (int n = 0; n < nConsumers; n++) {
            consumers.add(new Thread(consumerRunnable));
        }
        for (Thread c : consumers) {
            c.start();
        }
        for (Thread p : producers) {
            p.start();
        }
        try {
            for (Thread p : producers) {
                p.join();
            }
            for (Thread c : consumers) {
                c.join();
            }
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException iEx) {
            System.err.println(iEx.getMessage());
        }
    }

}
