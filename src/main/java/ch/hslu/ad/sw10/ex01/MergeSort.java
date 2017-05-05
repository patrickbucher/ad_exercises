package ch.hslu.ad.sw10.ex01;

import java.util.concurrent.ForkJoinPool;

public class MergeSort {

    public static void mergeSort(int array[]) {
        ForkJoinPool pool = new ForkJoinPool();
        SortTask task = new SortTask(array);
        pool.invoke(task);
    }
}
