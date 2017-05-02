package ch.hslu.ad.sw09.generic;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericSortBenchmark {

    private static final int TEST_SIZES[] = new int[] { 100_000, 200_000, 500_000, 1_000_000, 2_000_000, 5_000_000, };

    private Random random;

    @Before
    public void initialize() {
        random = new Random(System.currentTimeMillis());
    }

    @Test
    public void benchmarkHeapVsQuickSort() {
        System.out.println("   Items    HS    QS  QS Mo3");
        System.out.println("-------- ----- ----- -------");
        for (int testSize : TEST_SIZES) {
            Integer hItems[] = randomIntegerArray(testSize);
            Integer qItems[] = Arrays.copyOf(hItems, hItems.length);
            Integer qItemsMo3[] = Arrays.copyOf(hItems, hItems.length);

            long hStart = System.currentTimeMillis();
            GenericSort.heapSort(hItems);
            long hEnd = System.currentTimeMillis();
            Assert.assertTrue(sorted(hItems));

            long qStart = System.currentTimeMillis();
            GenericSort.quickSort(qItems);
            long qEnd = System.currentTimeMillis();
            Assert.assertTrue(sorted(qItems));

            long qStartMo3 = System.currentTimeMillis();
            GenericSort.quickSortMedianOfThree(qItemsMo3);
            long qEndMo3 = System.currentTimeMillis();
            Assert.assertTrue(sorted(qItemsMo3));

            System.out.printf("%8d %5d %5d %7d\n", testSize, hEnd - hStart, qEnd - qStart, qEndMo3 - qStartMo3);
        }
    }

    private <T extends Comparable<T>> boolean sorted(T items[]) {
        T last = items[0], current;
        for (int i = 1; i < items.length; i++) {
            current = items[i];
            if (current.compareTo(last) < 0) {
                return false;
            }
            last = current;
        }
        return true;
    }

    private Integer[] randomIntegerArray(int size) {
        Integer integers[] = new Integer[size];
        for (int i = 0; i < size; i++) {
            integers[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return integers;
    }
}
