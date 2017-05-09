package ch.hslu.ad.sw10.ex01;

public class PartialInsertionSort {

    public static void sortPartially(int[] items, int fromIn, int toEx) {
        for (int s = fromIn + 1; s < toEx; s++) {
            for (int i = s; i > fromIn && items[i] < items[i - 1]; i--) {
                swap(items, i, i - 1);
            }
        }
    }

    private static void swap(int[] items, int a, int b) {
        int tmp = items[a];
        items[a] = items[b];
        items[b] = tmp;
    }
}
