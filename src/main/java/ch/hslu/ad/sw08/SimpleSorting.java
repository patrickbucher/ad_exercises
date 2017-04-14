package ch.hslu.ad.sw08;

import java.util.List;

public class SimpleSorting {

    @SuppressWarnings("unchecked")
    public static <T> void bubbleSort(Comparable<T> items[]) {
        for (int i = 0; i < items.length; i++) {
            for (int j = i + 1; j < items.length; j++) {
                if (items[i].compareTo((T) items[j]) > 0) {
                    swap(items, i, j);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void insertionSort(Comparable<T> items[]) {
        for (int sortedTo = 1; sortedTo < items.length; sortedTo++) {
            for (int i = sortedTo; i > 0 && items[i].compareTo((T) items[i - 1]) < 0; i--) {
                swap(items, i, i - 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void selectionSort(Comparable<T> items[]) {
        for (int sortedTo = 0; sortedTo < items.length; sortedTo++) {
            Comparable<T> smallest = items[sortedTo];
            int smallestIndex = sortedTo;
            for (int i = sortedTo; i < items.length; i++) {
                if (items[i].compareTo((T) smallest) < 0) {
                    smallest = items[i];
                    smallestIndex = i;
                }
            }
            swap(items, sortedTo, smallestIndex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean isSorted(List<Comparable<T>> items, boolean ascending) {
        for (int n = 0; n < items.size() - 1; n++) {
            Comparable<T> current = items.get(n);
            Comparable<T> next = items.get(n + 1);
            int comparison = current.compareTo((T) next);
            if (ascending && comparison > 0) {
                return false;
            } else if (!ascending && comparison < 0) {
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
