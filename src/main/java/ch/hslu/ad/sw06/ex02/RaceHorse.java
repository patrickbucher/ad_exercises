package ch.hslu.ad.sw06.ex02;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RaceHorse implements Runnable {
    private static final Logger LOG = LogManager.getLogger();
    private final Synch startSignal;
    private volatile Thread runThread;
    private final Random random;

    public RaceHorse(Synch startSignal) {
        this.startSignal = startSignal;
        this.random = new Random();
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        LOG.info("Rennpferd " + runThread.getName() + " geht in die Box.");
        try {
            startSignal.acquire();
            LOG.info("Rennpferd " + runThread.getName() + " laeuft los...");
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException ex) {
            LOG.info("Rennpferd " + runThread.getName() + " gibt auf.");
            return;
        }
        LOG.info("Rennpferd " + runThread.getName() + " ist im Ziel.");
    }
}
