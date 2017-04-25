package ch.hslu.ad.sw08;

import java.util.List;

public class SimpleSorting {

    @SuppressWarnings("unchecked")
    public static <T> void bubbleSort(Comparable<T> data[]) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i].compareTo((T) data[j]) > 0) {
                    swap(data, i, j);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void insertionSort(Comparable<T> data[]) {
        for (int s = 1; s < data.length; s++) {
            for (int i = s; i > 0 && data[i].compareTo((T) data[i - 1]) < 0; i--) {
                swap(data, i, i - 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void selectionSort(Comparable<T> data[]) {
        for (int s = 0; s < data.length; s++) {
            Comparable<T> smallest = data[s];
            int smallestIndex = s;
            for (int i = s; i < data.length; i++) {
                if (data[i].compareTo((T) smallest) < 0) {
                    smallest = data[i];
                    smallestIndex = i;
                }
            }
            swap(data, s, smallestIndex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean isSorted(List<Comparable<T>> items, boolean asc) {
        for (int n = 0; n < items.size() - 1; n++) {
            Comparable<T> current = items.get(n);
            Comparable<T> next = items.get(n + 1);
            int cmp = current.compareTo((T) next);
            if (asc && cmp > 0) {
                return false;
            } else if (!asc && cmp < 0) {
                return false;
            }
        }
        return true;
    }

    private static <T> void swap(T items[], int i, int j) {
        T tmp = items[j];
        items[j] = items[i];
        items[i] = tmp;
    }

    @SuppressWarnings("unused")
    private static void output(Object items[]) {
        StringBuilder str = new StringBuilder();
        for (Object item : items) {
            str.append(item.toString());
            str.append(' ');
        }
        System.out.println(str.toString().trim());
    }
}
