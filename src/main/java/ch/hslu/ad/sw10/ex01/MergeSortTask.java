package ch.hslu.ad.sw10.ex01;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class MergeSortTask extends RecursiveAction {

    private final int array[];
    private final int min;
    private final int length;
    private final int threshold;

    public MergeSortTask(int array[], int threshold) {
        this.array = array;
        this.min = 0;
        this.length = array.length;
        this.threshold = threshold;
    }

    private MergeSortTask(int array[], int min, int length, int threshold) {
        this.array = array;
        this.min = min;
        this.length = length;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (length - min + 1 <= threshold) {
            PartialInsertionSort.sortPartially(array, min, length);
        } else {
            int mid = min + (length - min) / 2;
            invokeAll(new MergeSortTask(array, min, mid, threshold), new MergeSortTask(array, mid, length, threshold));
            merge(min, mid, length);
        }
    }

    private void merge(int min, int mid, int max) {
        int buf[] = Arrays.copyOfRange(array, min, mid);
        int readBuf = 0;
        int readArray = mid;
        int write = min;
        while (readBuf < buf.length) {
            if (readArray == max || buf[readBuf] < array[readArray]) {
                array[write++] = buf[readBuf++];
            } else {
                array[write++] = array[readArray++];
            }
        }
    }

}
