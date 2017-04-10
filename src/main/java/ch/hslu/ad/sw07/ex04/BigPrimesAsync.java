package ch.hslu.ad.sw07.ex04;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigPrimesAsync {

    private static final int N_PRIMES = 100;
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String args[]) {
        // findPrimesAndWriteToFile();
        findPrimesAndLog();
    }

    public static void findPrimesAndWriteToFile() {
        File outputFile = new File(System.getProperty("user.home") + File.separator + "primes.txt");
        try {
            long start = System.currentTimeMillis();
            findBigPrimes(N_PRIMES, outputFile);
            long end = System.currentTimeMillis();
            LOG.debug(String.format("found %d big prime numbers in %.2f seconds and wrote them to %s", N_PRIMES,
                    (float) (end - start) / 1000, outputFile.getAbsolutePath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void findPrimesAndLog() {
        try {
            long start = System.currentTimeMillis();
            List<BigInteger> primes = findBigPrimes(N_PRIMES);
            long end = System.currentTimeMillis();
            for (BigInteger prime : primes) {
                LOG.debug(prime.toString().substring(0, 20) + "...");
            }
            LOG.debug(String.format("found %d big prime numbers in %.2f seconds", N_PRIMES,
                    (float) (end - start) / 1000));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * finds primes and outputs them to the output file given
     */
    public static void findBigPrimes(final int nPrimes, File outputTo) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        Queue<BigInteger> resultQueue = new ArrayBlockingQueue<>(nPrimes);
        for (int n = 0; n < nPrimes; n++) {
            executorService.submit(new BigPrimeFinder(resultQueue));
        }
        int found = 0;

        OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputTo)));
        try {
            while (found < nPrimes) {
                BigInteger prime = resultQueue.poll();
                if (prime != null) {
                    writer.write(prime.toString() + "\n");
                    found++;
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new Exception(e);
                    }
                }
            }
            executorService.shutdown();
        } catch (IOException ioEx) {
            throw new Exception(ioEx);
        } finally {
            writer.close();
        }
    }

    /**
     * finds primes and returns them
     */
    public static List<BigInteger> findBigPrimes(final int nPrimes) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        CompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executorService);
        List<BigInteger> primes = new ArrayList<>(nPrimes);
        for (int n = 0; n < nPrimes; n++) {
            completionService.submit(new BigPrimeFinder());
        }
        for (int n = 0; n < nPrimes; n++) {
            try {
                primes.add(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                throw new Exception(e);
            }
        }
        executorService.shutdown();
        return primes;
    }
}
