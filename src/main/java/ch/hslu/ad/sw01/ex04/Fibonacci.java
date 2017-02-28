package ch.hslu.ad.sw01.ex04;

import java.util.Map;
import java.util.TreeMap;

public class Fibonacci {

    private static Map<Integer, Integer> fibCache = new TreeMap<>();

    public static int fiboRec1(int n) {
        if (n < 2) {
            return 1;
        } else {
            return fiboRec1(n - 2) + fiboRec1(n - 1);
        }
    }

    public static int fiboRec2(int n) {
        if (fibCache.containsKey(n)) {
            return fibCache.get(n);
        }
        int fib = 0;
        if (n < 2) {
            fib = 1;
        } else {
            fib = fiboRec2(n - 2) + fiboRec2(n - 1);
        }
        fibCache.put(n, fib);
        return fib;
    }

    public static int fiboIter(int n) {
        if (n < 2) {
            return 1;
        }
        int twoBack = 1;
        int oneBack = 1;
        int fib = 1;
        while (n >= 2) {
            fib = twoBack + oneBack;
            twoBack = oneBack;
            oneBack = fib;
            n--;
        }
        return fib;
    }

    public static void main(String args[]) {
        long start = 0, end = 0;
        int n = 40, result = 0;

        start = System.currentTimeMillis();
        result = fiboRec1(n);
        end = System.currentTimeMillis();
        output(n, result, end - start, "fiboRec1");
        
        start = System.currentTimeMillis();
        result = fiboRec2(n);
        end = System.currentTimeMillis();
        output(n, result, end - start, "fiboRec2");
        
        start = System.currentTimeMillis();
        result = fiboIter(n);
        end = System.currentTimeMillis();
        output(n, result, end - start, "fiboIter");
    }

    private static void output(int n, int result, long millis, String method) {
        System.out.println(
                String.format("%s computed the %d. fibonacci number (%d) in %d ms.", method, n, result, millis));
    }

}
