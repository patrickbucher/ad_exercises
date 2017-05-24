package ch.hslu.ad.sw12;

import java.util.Random;

public class SearchTestUtils {

    public static String createRandomString(int length, char from, char to) {
        if (from >= to) {
            return "";
        }
        Random random = new Random(System.currentTimeMillis());
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (from + random.nextInt(to - from + 1));
        }
        return new String(chars);
    }
}
