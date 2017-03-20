package ch.hslu.ad.sw04.ex04;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class HashTablePerformanceComparison {

	private final static int TEST_SIZE = 10000;

	@Test
	public void testHashTablePerformance() {
		long start = System.currentTimeMillis();
		CharWrapperHashTable hashTable = new CharWrapperHashTable();
		for (char c = 0; c < TEST_SIZE; c++) {
			hashTable.put(new CharWrapper(c));
		}
		Assert.assertEquals(TEST_SIZE, hashTable.getSize());
		for (char c = 0; c < TEST_SIZE; c++) {
			hashTable.remove(new CharWrapper(c));
		}
		Assert.assertEquals(0, hashTable.getSize());
		long end = System.currentTimeMillis();
		System.out.println("HashTable duration for " + TEST_SIZE + " items: " + (end - start) + "ms");
	}

	@Test
	public void testHashSetPerformance() {
		long start = System.currentTimeMillis();
		HashSet<CharWrapper> hashSet = new HashSet<>(TEST_SIZE);
		for (char c = 0; c < TEST_SIZE; c++) {
			hashSet.add(new CharWrapper(c));
		}
		Assert.assertEquals(TEST_SIZE, hashSet.size());
		for (char c = 0; c < TEST_SIZE; c++) {
			hashSet.remove(new CharWrapper(c));
		}
		Assert.assertEquals(0, hashSet.size());
		long end = System.currentTimeMillis();
		System.out.println("HashSet duration for " + TEST_SIZE + " items: " + (end - start) + "ms");
	}
}
