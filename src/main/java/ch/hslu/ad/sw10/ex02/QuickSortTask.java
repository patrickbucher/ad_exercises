package ch.hslu.ad.sw10.ex02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class QuickSortTask extends RecursiveAction {

    private final int[] items;
    private final int left;
    private final int right;
    private final int threshold;

    public QuickSortTask(int items[], int threshold) {
        this.items = items;
        this.left = 0;
        this.right = items.length - 1;
        this.threshold = threshold;
    }

    private QuickSortTask(int items[], int left, int right, int threshold) {
        this.items = items;
        this.left = left;
        this.right = right;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        int up = left;
        int down = right - 1;
        int t = items[right];
        do {
            while (items[up] < t) {
                up++;
            }
            while (items[down] >= t && down > up) {
                down--;
            }
            if (up < down) {
                swap(items, up, down);
            }
        } while (up < down);
        swap(items, up, right);
        List<QuickSortTask> tasks = new ArrayList<>();
        if (left < up - 1) {
            int size = (up - 1) - left + 1;
            if (size < threshold) {
                Arrays.sort(items, left, up);
            } else {
                tasks.add(new QuickSortTask(items, left, up - 1, threshold));
            }
        }
        if (right > up + 1) {
            int size = right - (up + 1) + 1;
            if (size < threshold) {
                Arrays.sort(items, up + 1, right + 1);
            } else {
                tasks.add(new QuickSortTask(items, up + 1, right, threshold));
            }
        }
        if (!tasks.isEmpty()) {
            invokeAll(tasks);
        }
    }
    
    private void swap(int[] items, int a, int b) {
        int tmp = items[a];
        items[a] = items[b];
        items[b] = tmp;
    }

}
