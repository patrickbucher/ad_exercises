package ch.hslu.ad.sw04.ex05;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUtils {

    public static Integer[] getSortedArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        Integer array[] = new Integer[size];
        for (Integer n = 0; n < size; n++) {
            array[n] = n;
        }
        return array;
    }

    public static void main(String args[]) {
        final int size = 4000000;
        Integer array[] = StackUtils.getSortedArray(size);

        long start = System.currentTimeMillis();
        Deque<Integer> deque = new ArrayDeque<>(size);
        for (int n = 0; n < size; n++) {
            deque.push(n);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(array.length);
    }
}
