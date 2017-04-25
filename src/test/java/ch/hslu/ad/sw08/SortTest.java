package ch.hslu.ad.sw08;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    private Integer numbers[];
    private String names[];

    @Before
    public void initNumbers() {
        numbers = generateRandomIntegerArray(30_000, 1, 100);
        names = new String[] { "Hans", "Sepp", "Schorsch", "Theo", "Ruedi", "Josy", "Heini", "Miigu", "Therese",
                "Bertha", "Uschi", "Trudy", "Meinrad", "Miigi", "Chluuri", "Brige" };
    }

    @Test
    public void testBubbleSort() {
        SimpleSorting.bubbleSort(numbers);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.bubbleSort(names);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(names), true));
    }

    @Test
    public void testInsertionSort() {
        SimpleSorting.insertionSort(numbers);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.insertionSort(names);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(names), true));
    }

    @Test
    public void testSelectionSort() {
        SimpleSorting.selectionSort(numbers);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(numbers), true));
        SimpleSorting.selectionSort(names);
        Assert.assertTrue(SimpleSorting.isSorted(Arrays.asList(names), true));
    }

    public Integer[] generateRandomIntegerArray(int size, int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        Integer array[] = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
