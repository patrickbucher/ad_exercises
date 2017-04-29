package ch.hslu.ad.sw09.generic;

import ch.hslu.ad.sw09.ex04.GenericHeap;
import ch.hslu.ad.sw09.ex04.Heap;

public class GenericSort {

    public static <T extends Comparable<T>> void heapSort(T items[]) {
        GenericHeap<T> heap = new Heap<>();
        for (int i = 0; i < items.length; i++) {
            heap.insert(items[i]);
        }
        for (int i = items.length - 1; i >= 0; i--) {
            items[i] = heap.getMax();
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] data, int left, int right) {
        if (right - left == 0) {
            return;
        }
        int up = left;
        int down = right - 1;
        T t = data[right];
        do {
            while (data[up].compareTo(t) < 0) {
                up++;
            }
            while (data[down].compareTo(t) >= 0 && down > up) {
                down--;
            }
            if (up >= down) {
                break;
            }
            swap(data, up, down);
        } while (true);
        swap(data, up, right);
        if (left < up - 1) {
            quickSort(data, left, up - 1);
        }
        if (right > up + 1) {
            quickSort(data, up + 1, right);
        }
    }

    public static <T extends Comparable<T>> void quickSortMedianOfThree(T[] data) {
        quickSortMedianOfThree(data, 0, data.length - 1);
    }

    public static <T extends Comparable<T>> void quickSortMedianOfThree(T[] data, int left, int right) {
        if (right - left == 0) {
            return;
        }
        int up = left;
        int down = right - 1;
        int middle = left + ((right - left) / 2);
        T l = data[left];
        T m = data[middle];
        T r = data[right];
        T t = r;
        int tIndex = right;
        if (l.compareTo(r) > 0 && l.compareTo(m) > 0) {
            t = l;
            tIndex = left;
        } else if (r.compareTo(l) > 0 && r.compareTo(m) > 0) {
            t = m;
            tIndex = middle;
        }
        swap(data, tIndex, right);
        do {
            while (data[up].compareTo(t) < 0) {
                up++;
            }
            while (data[down].compareTo(t) >= 0 && down > up) {
                down--;
            }
            if (up >= down) {
                break;
            }
            swap(data, up, down);
        } while (true);
        swap(data, up, right);
        if (left < up - 1) {
            quickSortMedianOfThree(data, left, up - 1);
        }
        if (right > up + 1) {
            quickSortMedianOfThree(data, up + 1, right);
        }
    }

    private static <T> void swap(T data[], int a, int b) {
        T tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
