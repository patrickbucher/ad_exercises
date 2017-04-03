package ch.hslu.ad.sw06.ex05;

import java.util.HashMap;
import java.util.Map;

public class SumOperator {

    private final SumTask sumTask;
    private final int nThreads;

    public SumOperator(SumTask sumTask, int nThreads) {
        this.sumTask = sumTask;
        this.nThreads = nThreads;
    }

    public long calculateSum() {
        Bounds bounds[] = calculateBounds();
        Map<PartialSumWorker, Thread> workerThreads = buildWorkerThreads(bounds);
        for (PartialSumWorker worker : workerThreads.keySet()) {
            workerThreads.get(worker).start();
        }
        for (PartialSumWorker worker : workerThreads.keySet()) {
            Thread thread = workerThreads.get(worker);
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(thread.getName() + ": " + e.getMessage());
            }
        }
        long sum = 0;
        for (PartialSumWorker worker : workerThreads.keySet()) {
            sum += worker.getPartialResult();
        }
        return sum;
    }

    private Bounds[] calculateBounds() {
        Bounds bounds[] = new Bounds[nThreads];
        int index = 0;
        for (int i = 0; i < nThreads; i++) {
            Bounds bound = new Bounds();
            bound.start = index;
            index += sumTask.getNumbers().length / nThreads - 1;
            bound.end = index;
            bounds[i] = bound;
            index++;
        }
        // remainder goes to the last worker
        bounds[nThreads - 1].end = sumTask.getNumbers().length - 1;
        return bounds;
    }

    private Map<PartialSumWorker, Thread> buildWorkerThreads(Bounds bounds[]) {
        Map<PartialSumWorker, Thread> workerThreads = new HashMap<>();
        for (int i = 0; i < nThreads; i++) {
            int partialNumbers[] = new int[bounds[i].end - bounds[i].start + 1];
            for (int r = bounds[i].start, w = 0; w < partialNumbers.length; r++, w++) {
                partialNumbers[w] = sumTask.getNumbers()[r];
            }
            PartialSumWorker worker = new PartialSumWorker(partialNumbers);
            Thread thread = new Thread(worker);
            workerThreads.put(worker, thread);
        }
        return workerThreads;
    }

    class Bounds {
        int start;
        int end;

        @Override
        public String toString() {
            return String.format("Bounds from %d to %d", start, end);
        }
    }
}
