package ch.hslu.ad.sw10.ex01;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class SortTask extends RecursiveAction {

    private static final int THRESHOLD = 25;

    private final int array[];
    private final int min;
    private final int length;

    public SortTask(int array[]) {
        this.array = array;
        this.min = 0;
        this.length = array.length;
    }

    private SortTask(int array[], int min, int length) {
        this.array = array;
        this.min = min;
        this.length = length;
    }

    @Override
    protected void compute() {
        if (length - min + 1 <= THRESHOLD) {
            Arrays.sort(array, min, length);
        } else {
            int mid = min + (length - min) / 2;
            invokeAll(new SortTask(array, min, mid), new SortTask(array, mid, length));
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
