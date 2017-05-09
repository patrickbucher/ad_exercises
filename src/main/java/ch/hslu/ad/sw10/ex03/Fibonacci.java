package ch.hslu.ad.sw10.ex03;

import java.math.BigInteger;

public class Fibonacci {

    public static BigInteger concurrentFibonacci(int n) {
        return new FibonacciTask(n).fork().join();
    }

    public static BigInteger recursiveFibonacci(int n) {
        if (n <= 2) {
            return BigInteger.ONE;
        } else {
            return recursiveFibonacci(n - 1).add(recursiveFibonacci(n - 2));
        }
    }

    public static BigInteger iterativeFibonacci(int n) {
        BigInteger[] fib = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                fib[i] = BigInteger.ONE;
            } else {
                fib[i] = fib[i - 1].add(fib[i - 2]);
            }
        }
        return fib[n - 1];
    }
}
