package ch.hslu.ad.sw10.ex03;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {

    private static final int N = 42;

    private BigInteger[] fib = new BigInteger[N];

    @Before
    public void calculateFibonacciNumbers() {
        for (int n = 0; n < N; n++) {
            if (n < 2) {
                fib[n] = BigInteger.ONE;
            } else {
                fib[n] = fib[n - 1].add(fib[n - 2]);
            }
        }
    }

    @Test
    public void testFirstTenManually() {
        Assert.assertEquals(BigInteger.valueOf(1), fib[0]);
        Assert.assertEquals(BigInteger.valueOf(1), fib[1]);
        Assert.assertEquals(BigInteger.valueOf(2), fib[2]);
        Assert.assertEquals(BigInteger.valueOf(3), fib[3]);
        Assert.assertEquals(BigInteger.valueOf(5), fib[4]);
        Assert.assertEquals(BigInteger.valueOf(8), fib[5]);
        Assert.assertEquals(BigInteger.valueOf(13), fib[6]);
        Assert.assertEquals(BigInteger.valueOf(21), fib[7]);
        Assert.assertEquals(BigInteger.valueOf(34), fib[8]);
        Assert.assertEquals(BigInteger.valueOf(55), fib[9]);
    }

    @Test
    public void testConcurrently() {
        Assert.assertEquals(fib[fib.length - 1], Fibonacci.concurrentFibonacci(N));
    }

    @Test
    public void testRecursively() {
        Assert.assertEquals(fib[fib.length - 1], Fibonacci.recursiveFibonacci(N));
    }

    @Test
    public void testIteratively() {
        Assert.assertEquals(fib[fib.length - 1], Fibonacci.iterativeFibonacci(N));
    }

    @Test
    public void benchmark() {
        System.out.println(" nth Fib  recursively  concurrently  iteratively");
        System.out.println("-------- ------------ ------------- ------------");
        for (int n = 1; n <= N; n++) {
            BigInteger result;

            long rStart = System.currentTimeMillis();
            result = Fibonacci.recursiveFibonacci(n);
            long rEnd = System.currentTimeMillis();
            Assert.assertEquals(fib[n - 1], result);

            long cStart = System.currentTimeMillis();
            result = Fibonacci.concurrentFibonacci(n);
            long cEnd = System.currentTimeMillis();
            Assert.assertEquals(fib[n - 1], result);

            long iStart = System.currentTimeMillis();
            result = Fibonacci.iterativeFibonacci(n);
            long iEnd = System.currentTimeMillis();
            Assert.assertEquals(fib[n - 1], result);

            System.out.printf("%8d %12d %13d %12d\n", n, rEnd - rStart, cEnd - cStart, iEnd - iStart);
        }
    }
}
