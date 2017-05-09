package ch.hslu.ad.sw10.ex01;

import java.util.concurrent.ForkJoinPool;

public class MergeSort {

    public static void mergeSort(int array[], int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, threshold);
        pool.invoke(task);
    }
}
