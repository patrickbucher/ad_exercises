package ch.hslu.ad.sw12.ex05;

public class QuickSearch {

    public static int quickSearch(String a, String p) {
        int n = a.length();
        int m = p.length();
        int range = 256; // ASCII range
        int[] shift = new int[range];

        // init shift array
        for (int i = 0; i < range; i++) {
            shift[i] = m + 1;
        }

        // overwrite fields according to pattern
        for (int i = 0; i < m; i++) {
            shift[p.charAt(i)] = m - i;
        }

        // search
        int i = 0;
        int j = 0;
        do {
            if (a.charAt(i + j) == p.charAt(j)) {
                j++;
            } else {
                if (i + m < n) {
                    i += shift[a.charAt(i + m)];
                    j = 0;
                } else {
                    break;
                }
            }
        } while (j < m && i + m <= n);

        if (j == m) {
            return i; // pattern found, starting at i
        } else {
            return -1; // pattern not found
        }
    }

}
