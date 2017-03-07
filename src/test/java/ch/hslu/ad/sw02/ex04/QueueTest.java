package ch.hslu.ad.sw02.ex04;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {
    @Test
    public void testSingleAllInAllOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test
    public void testSingleInOutInOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test
    public void testDoubleAllInAllOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test
    public void testDoubleInOutInOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test
    public void testTripeAllInAllOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        kassenschlange.enqueue("Vreni");
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test
    public void testTripeInOutInOut() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
        kassenschlange.enqueue("Uschi");
        Assert.assertEquals("Uschi", kassenschlange.dequeue());
        kassenschlange.enqueue("Vreni");
        Assert.assertEquals("Vreni", kassenschlange.dequeue());
        kassenschlange.enqueue("Josy");
        Assert.assertEquals("Josy", kassenschlange.dequeue());
    }

    @Test(expected = IllegalStateException.class)
    public void testOverflow() {
        Queue kassenschlange = new Queue();
        for (int n = 1; n <= Queue.QUEUE_SIZE + 1; n++) {
            kassenschlange.enqueue("Therese" + n);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testUnderflow() {
        Queue kassenschlange = new Queue();
        kassenschlange.enqueue("Therese");
        kassenschlange.dequeue();
        kassenschlange.dequeue();
    }

    @Test
    public void regressionTest() {
        Queue kassenschlange = new Queue();
        for (int n = 0; n < 1024 * 1024; n++) {
            kassenschlange.enqueue("Josy" + n);
            kassenschlange.enqueue("Therese" + n);
            kassenschlange.enqueue("Vreni" + n);
            kassenschlange.enqueue("Uschi" + n);
            kassenschlange.dequeue();
            kassenschlange.dequeue();
            kassenschlange.dequeue();
            kassenschlange.dequeue();
        }
    }
}
