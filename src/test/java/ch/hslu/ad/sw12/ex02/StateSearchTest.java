package ch.hslu.ad.sw12.ex02;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateSearchTest {

    private Map<String, Integer> searches;

    @Before
    public void initSearches() {
        searches = new HashMap<>();
        searches.put("ANANAS", 0);
        searches.put("ANANANAS", 2);
        searches.put("ANANANANAS", 4);
        searches.put("I don't like ANANAS.", 13);
        searches.put("ANANAs", -1);
    }

    @Test
    public void testStateSearchANANAS() {
        for (String haystack : searches.keySet()) {
            int expexted = searches.get(haystack);
            int actual = StateSearch.stateSearchANANAS(haystack);
            Assert.assertEquals(expexted, actual);
        }
    }
}
