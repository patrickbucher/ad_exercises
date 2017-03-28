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
        // TODO Auto-generated method stub

    }
}