package ch.hslu.ad.sw06.ex02;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Turf {

    private static final Logger LOG = LogManager.getLogger("HorseRace");

    private static final int START_BOXES = 5;

    private static final int CANCELATION_AFTER_MILLIS = 2000;

    public static void main(final String[] args) {
        Synch starterBox = new StartBoxLatch(START_BOXES);
        List<Thread> horseThreads = new ArrayList<>();
        for (int i = 1; i <= START_BOXES; i++) {
            Thread horseThread = new Thread(new RaceHorse(starterBox), "Horse " + i);
            horseThreads.add(horseThread);
            horseThread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        starterBox.release();
        try {
            Thread.sleep(CANCELATION_AFTER_MILLIS);
            LOG.info("Die Rennleitung bricht das Rennen ab.");
            for (Thread thread : horseThreads) {
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
