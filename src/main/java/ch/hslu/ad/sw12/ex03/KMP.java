package ch.hslu.ad.sw12.ex03;

public class KMP {

    public static int kmpSearch(final String a, final String p) {
        final int n = a.length();
        final int m = p.length();
        int i = 0;
        int j = 0;

        int[] next = initNext(p);
        do {
            if (j == -1 || a.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        } while (j < m && i < n);

        if (j == m) {
            return i - m;
        } else {
            return -1;
        }
    }

    private static int[] initNext(final String p) {
        final int m = p.length();
        final int[] next = new int[m];
        int i = 0;
        int j = -1;
        next[0] = -1;
        do {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        } while (i < (m - 1));
        return next;
    }
}
