package ch.hslu.ad.sw06.ex05;

public final class PartialSumWorker implements Runnable {

    private final int numbers[];

    private long partialResult = 0;
    private boolean resultReady = false;

    public PartialSumWorker(int numbers[]) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < numbers.length; i++) {
            partialResult += numbers[i];
        }
        resultReady = true;
    }

    public synchronized long getPartialResult() {
        if (!resultReady) {
            throw new IllegalStateException("Can't get result unless it is ready.");
        }
        return partialResult;
    }

    public synchronized boolean isResultReady() {
        return resultReady;
    }
}
