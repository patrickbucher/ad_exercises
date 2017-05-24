package ch.hslu.ad.sw12;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.sw12.ex03.KMP;
import ch.hslu.ad.sw12.ex05.QuickSearch;

public class SearchBenchmark {

    private String haystack;
    private String needle;
    private int expected;

    @Before
    public void initTest() {
        haystack = SearchTestUtils.createRandomString(100_000_000, 'a', 'z');
        needle = "abcde";
        expected = haystack.indexOf(needle);
        System.out.printf("needle at: %d\n", expected);
    }

    @Test
    public void benchmarkSearch() {
        long kmpStart = System.currentTimeMillis();
        int actual = KMP.kmpSearch(haystack, needle);
        long kmpEnd = System.currentTimeMillis();
        Assert.assertEquals(expected, actual);

        long qsStart = System.currentTimeMillis();
        actual = QuickSearch.quickSearch(haystack, needle);
        long qsEnd = System.currentTimeMillis();
        Assert.assertEquals(expected, actual);

        System.out.printf("KMP: %6d\n", kmpEnd - kmpStart);
        System.out.printf("QS:  %6d\n", qsEnd - qsStart);

    }
}
