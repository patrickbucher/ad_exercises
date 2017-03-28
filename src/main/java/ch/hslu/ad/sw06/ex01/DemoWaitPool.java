package ch.hslu.ad.sw06.ex01;

public final class DemoWaitPool {

    private static final Object lock = new Object();

    public static void main(final String args[]) throws InterruptedException {
        synchronized (lock) {
            MyTask waiter = new MyTask(lock);
            new Thread(waiter).start();
            Thread.sleep(1000);
            lock.notify();
        }
    }
}