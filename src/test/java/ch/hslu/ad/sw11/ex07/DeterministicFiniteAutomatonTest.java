package ch.hslu.ad.sw11.ex07;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.sw11.ex07.DeterministicFiniteAutomaton;

public class DeterministicFiniteAutomatonTest {

    private Map<String, Boolean> testValues;

    @Before
    public void initTestValues() {
        testValues = new HashMap<>();
        testValues.put("0", true);
        testValues.put("010", true);
        testValues.put("01110", true);
        testValues.put("0111010", true);
        testValues.put("0101110", true);
        testValues.put("0101010", true);
        testValues.put("1010", false); // doesn't start with a 0
        testValues.put("0110", false); // even number of 1s
        testValues.put("010010", false); // 0 is not alone
        testValues.put("0101", false); // doesn't end with a 0
    }

    @Test
    public void test() {
        for (String testValue : testValues.keySet()) {
            Assert.assertEquals(testValues.get(testValue), DeterministicFiniteAutomaton.isWordLanguage(testValue));
        }
    }
}
