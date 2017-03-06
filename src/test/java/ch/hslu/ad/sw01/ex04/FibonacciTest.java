package ch.hslu.ad.sw01.ex04;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {
	private final static int FIB[] = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 };

	@Test
	public void testFiboRec1() {
		for (int n = 0; n < FIB.length; n++) {
			Assert.assertEquals(FIB[n], Fibonacci.fiboRec1(n));
		}
	}
	
	@Test
	public void testFiboRec2() {
		for (int n = 0; n < FIB.length; n++) {
			Assert.assertEquals(FIB[n], Fibonacci.fiboRec2(n));
		}
	}
	
	@Test
	public void testFiboIter() {
		for (int n = 0; n < FIB.length; n++) {
			Assert.assertEquals(FIB[n], Fibonacci.fiboIter(n));
		}
	}
	
	@Test(timeout = 1000)
	public void testFiboRec1Performance() {
		Assert.assertEquals(165580141, Fibonacci.fiboRec1(40));
	}
	
	@Test(timeout = 500)
	public void testFiboRec2Performance() {
		Assert.assertEquals(165580141, Fibonacci.fiboRec2(40));
	}
	
}
