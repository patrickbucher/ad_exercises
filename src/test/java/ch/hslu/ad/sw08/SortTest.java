package ch.hslu.ad.sw08;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.div.sorting.SortingUtils;

public class SortTest {

    private Integer numbers[];
    private String names[];

    @Before
    public void initNumbers() {
        numbers = SortingUtils.generateRandomIntegerArray(100_000, 1, 100);
        names = new String[] { "Hans", "Sepp", "Schorsch", "Theo", "Ruedi", "Josy", "Heini", "Miigu", "Therese",
                "Bertha", "Uschi", "Trudy", "Meinrad", "Miigi", "Chluuri", "Brige" };
    }

//    @Test
    public void testBubbleSort() {
        SimpleSorting.bubbleSort(numbers);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.bubbleSort(names);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(names), true));
    }

    @Test
    public void testInsertionSort() {
        SimpleSorting.insertionSort(numbers);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.insertionSort(names);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(names), true));
    }

    @Test
    public void testSelectionSort() {
        SimpleSorting.selectionSort(numbers);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.selectionSort(names);
        Assert.assertTrue(SortingUtils.isSorted(Arrays.asList(names), true));
    }
}
