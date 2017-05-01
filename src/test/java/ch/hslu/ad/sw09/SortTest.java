package ch.hslu.ad.sw09;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ch.hslu.ad.div.sorting.SortingUtils;
import ch.hslu.ad.sw09.ex02.Sort;

public class SortTest {

    @Test
    public void testQuickSort() {
        final int n = 1_000_000;
        Character data[] = SortingUtils.generateRandomCharArray(n, 'A', 'Z');
        Sort.quickSort(data);
        boolean sorted = SortingUtils.isSorted(Arrays.asList(data), true);
        Assert.assertTrue(sorted);
    }

    @Test
    public void benchmarkQuickSort() {
        int elements[] = { 1000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000 };
        for (int n : elements) {
            Character data[] = SortingUtils.generateRandomCharArray(n, 'A', 'Z');
            long start = System.currentTimeMillis();
            Sort.quickSort(data);
            long end = System.currentTimeMillis();
            Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(data), true));
            System.out.println(String.format("%8d %10d", n, end - start));
        }
    }

    @Test
    public void benchmarkQuickInsertionSort() {
        int ms[] = { 5, 10, 15, 20, 25, 30, 40, 50, 75, 100, 125, 150, 200, 250, 500, 1000 };
        for (int m : ms) {
            Character data[] = SortingUtils.generateRandomCharArray(1_000_000, 'A', 'Z');
            long start = System.currentTimeMillis();
            Sort.quickInsertionSort(data, m);
            long end = System.currentTimeMillis();
            SortingUtils.isSorted(Arrays.asList(data), true);
            Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(data), true));
            System.out.println(String.format("%4d %10d", m, end - start));
        }
    }

    @Test
    public void benchmarkQuickSortVsQuickInsertionSort() {
        int elements[] = { 1000, 5_000, 10_000, 50_000, 100_000, 500_000, 1_000_000, 2_000_000 };
        for (int n : elements) {

            Character data[] = SortingUtils.generateRandomCharArray(n, 'A', 'Z');
            long qStart = System.currentTimeMillis();
            Sort.quickSort(data);
            long qEnd = System.currentTimeMillis();

            data = SortingUtils.generateRandomCharArray(n, 'A', 'Z');
            long qiStart = System.currentTimeMillis();
            Sort.quickInsertionSort(data, 25);
            long qiEnd = System.currentTimeMillis();

            System.out.println(String.format("%8d %10d %10d", n, qEnd - qStart, qiEnd - qiStart));
        }
    }

}
