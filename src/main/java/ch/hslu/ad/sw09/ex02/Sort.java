package ch.hslu.ad.sw09.ex02;

import ch.hslu.ad.sw08.SimpleSorting;

public class Sort {

    public static void quickSort(Character[] data) {
        quickSort(data, 0, data.length - 1);
    }

    public static void quickSort(Character[] data, int left, int right) {
        int up = left;
        int down = right - 1;
        char t = data[right];
        do {
            while (data[up] < t) {
                up++;
            }
            while (data[down] >= t && down > up) {
                down--;
            }
            if (up < down) {
                swap(data, up, down);
            }
        } while (up < down);
        swap(data, up, right);
        if (left < up - 1) {
            quickSort(data, left, up - 1);
        }
        if (right > up + 1) {
            quickSort(data, up + 1, right);
        }
    }

    public static void quickInsertionSort(Character[] data, int m) {
        quickInsertionSort(data, 0, data.length - 1, m);
    }

    public static void quickInsertionSort(Character[] data, int left, int right, int m) {
        int up = left;
        int down = right - 1;
        char t = data[right];
        do {
            while (data[up] < t) {
                up++;
            }
            while (data[down] >= t && down > up) {
                down--;
            }
            if (up >= down) {
                break;
            }
            swap(data, up, down);
        } while (true);
        swap(data, up, right);
        if (left < up - 1) {
            int from = left;
            int to = up - 1;
            if (to - from  + 1> m) {
                quickInsertionSort(data, from, to, m);
            } else {
                SimpleSorting.insertionSort(data, from, to);
            }
        }
        if (right > up + 1) {
            int from = up + 1;
            int to = right;
            if (to - from + 1 > m) {
                quickInsertionSort(data, from, to, m);
            } else {
                SimpleSorting.insertionSort(data, from, to);
            }
        }
    }

    private static void swap(Character[] data, int a, int b) {
        char tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
