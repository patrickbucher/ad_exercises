package ch.hslu.ad.sw06.ex03;

import org.junit.Assert;
import org.junit.Test;

public class BoundedBufferTest {

    static final int BUFFER_CAPACITY = 10000;

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

}
