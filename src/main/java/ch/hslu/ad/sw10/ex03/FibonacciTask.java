package ch.hslu.ad.sw10.ex03;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class FibonacciTask extends RecursiveTask<BigInteger> {

    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n <= 2) {
            return BigInteger.ONE;
        }
        FibonacciTask ft1 = new FibonacciTask(n - 1);
        FibonacciTask ft2 = new FibonacciTask(n - 2);
        ForkJoinTask<BigInteger> t1 = ft1.fork();
        ForkJoinTask<BigInteger> t2 = ft2.fork();
        return t1.join().add(t2.join());
    }

}
