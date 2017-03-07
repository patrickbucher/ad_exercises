package ch.hslu.ad.sw02.ex03;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void testPushPop() {
        Stack stack = new Stack();
        Assert.assertEquals(0, stack.getSize());

        stack.push("Hello");
        Assert.assertEquals(1, stack.getSize());

        stack.push("World");
        Assert.assertEquals(2, stack.getSize());

        Assert.assertEquals("World", stack.pop());
        Assert.assertEquals(1, stack.getSize());

        Assert.assertEquals("Hello", stack.pop());
        Assert.assertEquals(0, stack.getSize());
    }

    @Test(expected = IllegalStateException.class)
    public void testOverflow() {
        Stack stack = new Stack();
        for (int n = 0; n < 10; n++) {
            stack.push(String.valueOf(n));
        }
        Assert.assertEquals(10, stack.getSize());
        stack.push("overflow");
    }

    @Test(expected = IllegalStateException.class)
    public void testUnderflow() {
        Stack stack = new Stack();
        stack.pop();
    }
}
