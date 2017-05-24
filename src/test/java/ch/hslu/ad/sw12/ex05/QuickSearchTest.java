package ch.hslu.ad.sw12.ex05;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.sw12.SearchTestUtils;

public class QuickSearchTest {
    private Map<Search, Integer> searches;

    @Before
    public void initSearches() {
        searches = new HashMap<>();

        // small tests
        searches.put(new Search("ananas", "ananas"), 0);
        searches.put(new Search("anananas", "ananas"), 2);
        searches.put(new Search("ananAs", "ananas"), -1);
        searches.put(new Search("dasisteinTest", "Test"), 9);

        // bigger tests
        String haystack = SearchTestUtils.createRandomString(100000, 'a', 'z');
        String pattern = "xyz";
        Integer position = haystack.indexOf(pattern);
        searches.put(new Search(haystack, pattern), position);
        
        haystack = SearchTestUtils.createRandomString(100, 'a', 'b');
        pattern = "abba";
        position = haystack.indexOf(pattern);
        searches.put(new Search(haystack, pattern), position);
    }

    @Test
    public void testQuickSearch() {
        for (Search search : searches.keySet()) {
            int expected = searches.get(search);
            int actual = QuickSearch.quickSearch(search.haystack, search.pattern);
            Assert.assertEquals(expected, actual);
            System.out.printf("expected: %d, actual: %d\n", expected, actual);
        }
    }
}

class Search {
    Search(String haystack, String pattern) {
        this.haystack = haystack;
        this.pattern = pattern;
    }

    String haystack;
    String pattern;

    public int hashCode() {
        return Objects.hash(haystack, pattern);
    }
}
