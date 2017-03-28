package ch.hslu.ad.sw06.ex02;

public interface Synch {

    public void acquire() throws InterruptedException;

    public void release();
}
