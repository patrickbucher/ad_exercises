package ch.hslu.ad.sw06.ex04;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class JailSemaphoreTest {

    private final int nCells = 12;
    private Semaphore jailSemaphore = new Semaphore(nCells, nCells);
    private Random judge = new Random(System.currentTimeMillis());

    // in days... or milliseconds
    private final int minSentence = 100;
    private final int maxSentence = 3000;

    @Test
    public void jailSemaphore() {
        Map<String, Integer> honorableFamilies = new HashMap<>();
        honorableFamilies.put("Corleone", 7);
        honorableFamilies.put("Barzini", 6);
        honorableFamilies.put("Tattaglia", 5);
        honorableFamilies.put("Stracci", 4);
        honorableFamilies.put("Cuneo", 3);

        Map<String, Thread> honorableFamilyThreads = new HashMap<>();
        for (String family : honorableFamilies.keySet()) {
            honorableFamilyThreads.put(family, createHonorableFamily(family, honorableFamilies.get(family)));
        }

        for (String name : honorableFamilies.keySet()) {
            honorableFamilyThreads.get(name).start();
        }
        for (String name : honorableFamilyThreads.keySet()) {
            Thread t = honorableFamilyThreads.get(name);
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println(t.getName() + ": " + e.getMessage());
            }
        }
        Assert.assertEquals(nCells, jailSemaphore.getFree());
    }

    public Thread createHonorableFamily(String name, int members) {
        Thread honorableFamily = new Thread(() -> {
            String log;
            int sentence = minSentence + judge.nextInt(maxSentence - minSentence + 1);
            log = "The " + members + " members of the " + name + " family are sentenced to " + sentence + " days";
            System.out.println(log);
            try {
                while (jailSemaphore.getFree() < members) {
                    log = "The jail is full, the members of the " + name + " family have to wait a while.";
                    System.out.println(log);
                    // heuristic: wait for the average sentence's duration
                    Thread.sleep((minSentence + maxSentence) / 2);
                }
                synchronized (this) {
                    log = "The " + members + " members of the " + name + " family enter the jail now";
                    System.out.println(log);
                    int free = jailSemaphore.acquire(members);
                    int occupied = nCells - free;
                    log = String.format("After entry of the %s family: %d/%d cells occupied, %d free", name, occupied,
                            nCells, free);
                    System.out.println(log);
                }
                Thread.sleep(sentence);
            } catch (InterruptedException e) {
                System.err.println(name + ": " + e.getMessage());
            }
            synchronized (this) {
                log = "The " + members + " members of the " + name + " family can leave the jail";
                System.out.println(log);
                int free = jailSemaphore.release(members);
                int occupied = nCells - free;
                log = String.format("After discharge of the %s family: %d/%d cells occupied, %d free", name, occupied,
                        nCells, free);
                System.out.println(log);
            }
        });
        return honorableFamily;
    }
}
