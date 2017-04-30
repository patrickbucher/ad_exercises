package ch.hslu.ad.sw09.ex04;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.div.sorting.SortingUtils;
import ch.hslu.ad.sw09.generic.GenericSort;

public class GenericSortTest {

    private static final int TEST_SIZE = 100_000;
    private static final int STRING_LENGTH = 30;

    private Integer integers[] = new Integer[TEST_SIZE];
    private Double doubles[] = new Double[TEST_SIZE];
    private String strings[] = new String[TEST_SIZE];

    @Before
    public void initializeRandomArray() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < TEST_SIZE; i++) {
            integers[i] = random.nextInt();
            doubles[i] = random.nextDouble();
            strings[i] = randomString(random);
        }
    }

    @Test
    public void testIntegerHeapSort() {
        GenericSort.heapSort(integers);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(integers), true));
    }

    @Test
    public void testDoubleHeapSort() {
        GenericSort.heapSort(doubles);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(doubles), true));
    }

    @Test
    public void testStringHeapSort() {
        GenericSort.heapSort(strings);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(strings), true));
    }

    @Test
    public void testIntegerQuickSort() {
        GenericSort.quickSort(integers);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(integers), true));
    }

    @Test
    public void testDoubleQuickSort() {
        GenericSort.quickSort(doubles);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(doubles), true));
    }

    @Test
    public void testStringQuickSort() {
        GenericSort.quickSort(strings);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(strings), true));
    }

    @Test
    public void testMedianOfThree() {
        Assert.assertEquals(1, GenericSort.medianOfThree(new Integer[] { 10, 20, 30 }, 0, 2));
        Assert.assertEquals(2, GenericSort.medianOfThree(new Integer[] { 10, 20, 30, 40, 50 }, 0, 4));
        Assert.assertEquals(3, GenericSort.medianOfThree(new Integer[] { 10, 20, 30, 40, 50, 60, 70 }, 0, 6));
        Assert.assertEquals(3, GenericSort.medianOfThree(new Integer[] { 10, 20, 30, 40, 50, 60, 70 }, 2, 4));
    }

    private String randomString(Random random) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < STRING_LENGTH; i++) {
            randomString.append((char) (random.nextInt('Z' - 'A' + 1) + 'A'));
        }
        return randomString.toString();
    }
}
