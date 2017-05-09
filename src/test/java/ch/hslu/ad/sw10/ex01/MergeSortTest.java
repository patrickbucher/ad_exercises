package ch.hslu.ad.sw10.ex01;

import org.junit.Assert;
import org.junit.Test;

import ch.hslu.ad.sw10.SortTestUtils;

public class MergeSortTest {

    private static final int TEST_SIZE = 100_000_000;

    private static final int[] THRESHOLDS = new int[] { 10, 25, 33, 50, 66, 75, 100, 250 };

    @Test
    public void testMergeSort() {
        int[] array = SortTestUtils.randomArray(TEST_SIZE, 0, TEST_SIZE);
        MergeSort.mergeSort(array, 25);
        Assert.assertTrue(SortTestUtils.sorted(array));
    }

    @Test
    public void benchmarkThresholds() {
        System.out.println(" Threshold  Duration");
        System.out.println("---------- ---------");
        for (int threshold : THRESHOLDS) {
            int[] array = SortTestUtils.randomArray(TEST_SIZE, 0, TEST_SIZE);
            long start = System.currentTimeMillis();
            MergeSort.mergeSort(array, threshold);
            long end = System.currentTimeMillis();
            Assert.assertTrue(SortTestUtils.sorted(array));
            System.out.printf("%10d %9d\n", threshold, end - start);
        }
    }
}
