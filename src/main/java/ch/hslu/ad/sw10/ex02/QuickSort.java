package ch.hslu.ad.sw10.ex02;

import java.util.concurrent.ForkJoinPool;

public class QuickSort  {

    public static void quickSort(int[] items, int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask task = new QuickSortTask(items, threshold);
        pool.invoke(task);
    }

}
