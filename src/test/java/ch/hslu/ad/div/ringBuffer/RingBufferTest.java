package ch.hslu.ad.div.ringBuffer;

import org.junit.Assert;
import org.junit.Test;

public class RingBufferTest {

    @Test
    public void testWriteRead() {
        final int size = 1000000;
        RingBuffer<String> buf = new RingBuffer<>(size);
        Assert.assertTrue(buf.isEmpty());
        int r = 1, w = 1;

        for (; w <= size; w++) {
            buf.put("String" + w);
        }
        Assert.assertTrue(buf.isFull());

        for (; r <= w / 2; r++) {
            Assert.assertEquals("String" + r, buf.get());
        }

        for (; w <= size + size / 2; w++) {
            buf.put("String" + w);
        }
        Assert.assertTrue(buf.isFull());

        for (; r <= w - 1; r++) {
            Assert.assertEquals("String" + r, buf.get());
        }
        Assert.assertTrue(buf.isEmpty());
    }

}
