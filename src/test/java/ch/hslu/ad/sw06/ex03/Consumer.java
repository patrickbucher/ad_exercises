package ch.hslu.ad.sw06.ex03;

import org.junit.Assert;

public class Consumer implements Runnable, Thread.UncaughtExceptionHandler {

    private final BoundedBuffer<String> buffer;
    private int consumed = 0;

    private long timeout = 0;

    private volatile boolean done = false;

    public Consumer(BoundedBuffer<String> buffer) {
        this.buffer = buffer;
    }

    public Consumer(BoundedBuffer<String> buffer, long timeout) {
        this(buffer);
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while (!done) {
            synchronized (buffer) {
                if (!buffer.empty()) {
                    String str;
                    if (timeout == 0) {
                        str = buffer.get();
                    } else {
                        str = buffer.get(timeout);
                    }
                    Assert.assertNotNull(str);
                    consumed++;
                }
            }
        }
    }

    public int getConsumed() {
        return consumed;
    }

    public void requestStop() {
        this.done = true;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println(String.format("Thread %s has thrown an exception, cause: %s", t.getName(), e.getMessage()));
    }
}
