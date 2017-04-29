package ch.hslu.ad.sw09;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.sw09.ex02.Sort;

public class SortBenchmarkTest {

    private int array[];

    @Before
    public void createRandomArray() {
        final int capacity = 2_000_000;
        array = new int[capacity];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < capacity; i++) {
            array[i] = random.nextInt(capacity);
        }
    }

    @Test
    public void benchmarkHeapSort() {
        // System.out.println(Arrays.toString(array));
        long start = System.currentTimeMillis();
        Sort.heapSort(array);
        long end = System.currentTimeMillis();
        // System.out.println(Arrays.toString(array));
        System.out.printf("Sorted %d elements in %d ms.\n", array.length, end - start);
    }
}
