package ch.hslu.ad.div.sorting;

import java.util.List;
import java.util.Random;

public class SortingUtils {
    @SuppressWarnings("unchecked")
    public static <T> boolean isSorted(List<Comparable<T>> items, boolean asc) {
        for (int n = 0; n < items.size() - 1; n++) {
            Comparable<T> current = items.get(n);
            Comparable<T> next = items.get(n + 1);
            int cmp = current.compareTo((T) next);
            if (asc && cmp > 0) {
                return false;
            } else if (!asc && cmp < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static Integer[] generateRandomIntegerArray(int size, int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        Integer array[] = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
    
    public static Character[] generateRandomCharArray(int size, int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        Character array[] = new Character[size];
        for (int i = 0; i < size; i++) {
            array[i] = (char) (random.nextInt(max - min + 1) + min);
        }
        return array;
    }
}
