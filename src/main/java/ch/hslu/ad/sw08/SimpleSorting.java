package ch.hslu.ad.sw08;

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
    public static <T> void insertionSort(Comparable<T> data[], int from, int to) {
        for (int s = from + 1; s <= to; s++) {
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
