package ch.hslu.ad.sw01.memory;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertImmutable;

public class MemoryCommonMethods {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Allocation.class).verify();
    }

    @Test
    public void testHashCode() {
        assertImmutable(Allocation.class);
    }
}
