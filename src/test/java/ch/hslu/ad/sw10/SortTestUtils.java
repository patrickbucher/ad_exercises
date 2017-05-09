package ch.hslu.ad.sw10;

import java.util.Random;

public class SortTestUtils {
    
    public static boolean sorted(int[] array) {
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

    public static int[] randomArray(int testSize, int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        int array[] = new int[testSize];
        for (int i = 0; i < testSize; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
