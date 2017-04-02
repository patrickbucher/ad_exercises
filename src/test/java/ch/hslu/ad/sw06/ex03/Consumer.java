package ch.hslu.ad.sw06.ex03;

import org.junit.Assert;

public class Consumer implements Runnable {

    private final BoundedBuffer<String> buffer;
    private int consumed = 0;

    private volatile boolean done;

    public Consumer(BoundedBuffer<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (!done) {
            if (!buffer.empty()) {
                String str = buffer.get();
                Assert.assertNotNull(str);
                consumed++;
            }
        }
    }

    public int getConsumed() {
        return consumed;
    }

    public synchronized void requestStop() {
        done = true;
    }
}
