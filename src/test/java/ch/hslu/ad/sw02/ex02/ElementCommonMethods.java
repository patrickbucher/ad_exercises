package ch.hslu.ad.sw02.ex02;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertImmutable;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ElementCommonMethods {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Element.class).verify();
    }

    @Test
    public void testHashCode() {
        assertImmutable(Element.class);
    }
}