package ch.hslu.ad.sw10.ex01;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {

    private static final int TEST_SIZE = 100_000_000;

    @Test
    public void testMergeSort() {
        int array[] = randomArray(TEST_SIZE, 0, TEST_SIZE);
        MergeSort.mergeSort(array);
        Assert.assertTrue(sorted(array));
    }

    private boolean sorted(int[] array) {
        int last = array[0], current;
        for (int i = 0; i < array.length; i++) {
            current = array[i];
            if (last > current) {
                return false;
            }
            last = current;
        }
        return true;
    }

    private int[] randomArray(int testSize, int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        int array[] = new int[testSize];
        for (int i = 0; i < testSize; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
