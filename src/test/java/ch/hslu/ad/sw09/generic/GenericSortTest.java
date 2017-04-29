package ch.hslu.ad.sw09.generic;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.div.sorting.SortingUtils;

public class GenericSortTest {

    private static final int TEST_SIZE = 100;
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
        SortingUtils.isSorted(Arrays.asList(integers), true);
    }

    @Test
    public void testDoubleHeapSort() {
        GenericSort.heapSort(doubles);
        SortingUtils.isSorted(Arrays.asList(doubles), true);
    }

    @Test
    public void testStringHeapSort() {
        GenericSort.heapSort(strings);
        SortingUtils.isSorted(Arrays.asList(strings), true);
    }

    @Test
    public void testIntegerQuickSort() {
        GenericSort.quickSort(integers);
        SortingUtils.isSorted(Arrays.asList(integers), true);
    }

    @Test
    public void testDoubleQuickSort() {
        GenericSort.quickSort(doubles);
        SortingUtils.isSorted(Arrays.asList(doubles), true);
    }

    @Test
    public void testStringQuickSort() {
        GenericSort.quickSort(strings);
        SortingUtils.isSorted(Arrays.asList(strings), true);
    }

    private String randomString(Random random) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < STRING_LENGTH; i++) {
            randomString.append((char) (random.nextInt('Z' - 'A' + 1) + 'A'));
        }
        return randomString.toString();
    }
}
