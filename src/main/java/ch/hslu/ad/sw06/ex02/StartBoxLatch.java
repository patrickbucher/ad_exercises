package ch.hslu.ad.sw06.ex02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartBoxLatch implements Synch {

    private static final Logger LOG = LogManager.getLogger("HorseRace");

    private final int totalSlots;
    private int slotsFilled;
    private boolean started = false;

    private final Thread timeout;

    public StartBoxLatch(int totalSlots) {
        this.totalSlots = totalSlots;

        timeout = new Thread(new Runnable() {
            @Override
            public void run() {
                LOG.info("Startplätze belegt, automatischer Start in 1000ms...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOG.info("Der manuelle Start ist dem automatischen Start zuvorgekommen.");
                    return;
                }
                release();
            }
        });
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        if (slotsFilled < totalSlots) {
            slotsFilled++;
            if (slotsFilled == totalSlots) {
                timeout.start();
            }
            this.wait();
        }
    }

    @Override
    public synchronized void release() {
        if (started) {
            LOG.info("Das Rennen kann nicht manuell gestartet werden, es wurde bereits automatisch gestartet");
            return;
        }
        while (slotsFilled < totalSlots) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!started) {
            started = true;
            timeout.interrupt();
            this.notifyAll();
        }
        LOG.info("Start...");
    }
}
