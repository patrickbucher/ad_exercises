package ch.hslu.ad.sw10.ex02;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ch.hslu.ad.sw10.SortTestUtils;

public class QuickSortTest {

    private static final int TEST_SIZE = 100_000_000;

    private static final int[] THRESHOLDS = new int[] { 10, 25, 33, 50, 66, 75, 100, 250 };

    private static final int[] COMPARISONS = new int[] { 1_000_000, 2_000_000, 5_000_000, 10_000_000, 20_000_000,
            50_000_000, 100_000_000 };

    @Test
    public void testQuickSort() {
        int[] array = SortTestUtils.randomArray(TEST_SIZE, 0, TEST_SIZE);
        QuickSort.quickSort(array, 25);
        Assert.assertTrue(SortTestUtils.sorted(array));
    }

    @Test
    public void benchmarkThresholds() {
        System.out.println(" Threshold  Duration");
        System.out.println("---------- ---------");
        for (int threshold : THRESHOLDS) {
            int[] array = SortTestUtils.randomArray(TEST_SIZE, 0, TEST_SIZE);
            long start = System.currentTimeMillis();
            QuickSort.quickSort(array, threshold);
            long end = System.currentTimeMillis();
            Assert.assertTrue(SortTestUtils.sorted(array));
            System.out.printf("%10d %9d\n", threshold, end - start);
        }
    }

    @Test
    public void benchmarkAgainstArraysSort() {
        System.out.println(" Items  Arrays.sort  QuickSort");
        System.out.println("------ ------------ ----------");
        for (int comparison : COMPARISONS) {
            int[] arrayA = SortTestUtils.randomArray(comparison, 0, comparison);
            int[] arrayB = Arrays.copyOf(arrayA, arrayA.length);

            long aStart = System.currentTimeMillis();
            Arrays.sort(arrayA);
            long aEnd = System.currentTimeMillis();
            Assert.assertTrue(SortTestUtils.sorted(arrayA));

            long qsStart = System.currentTimeMillis();
            QuickSort.quickSort(arrayB, 50);
            long qsEnd = System.currentTimeMillis();
            Assert.assertTrue(SortTestUtils.sorted(arrayB));

            System.out.printf("%5dM %12d %10d\n", comparison / 1_000_000, aEnd - aStart, qsEnd - qsStart);
        }
    }
}
