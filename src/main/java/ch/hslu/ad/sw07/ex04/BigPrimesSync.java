package ch.hslu.ad.sw07.ex04;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigPrimesSync {

    private static final int NUM_BITS = 1024;
    private static final int N_PRIMES = 100;
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<BigInteger> primes = findBigPrimes(N_PRIMES);
        long end = System.currentTimeMillis();
        for (BigInteger prime : primes) {
            LOG.debug(prime.toString().substring(0, 20) + "...");
        }
        LOG.debug(String.format("found %d big prime numbers in %.2f seconds", N_PRIMES, (float) (end - start) / 1000));
    }

    public static List<BigInteger> findBigPrimes(final int nPrimes) {
        List<BigInteger> bigPrimes = new ArrayList<>(nPrimes);
        int n = 0;
        while (n < nPrimes) {
            BigInteger prime = findBigPrime();
            n++;
            bigPrimes.add(prime);
        }
        return bigPrimes;
    }

    public static BigInteger findBigPrime() {
        boolean foundPrime = false;
        BigInteger number;
        do {
            number = new BigInteger(NUM_BITS, new Random());
            foundPrime = number.isProbablePrime(Integer.MAX_VALUE);
        } while (!foundPrime);
        return number;
    }
}
