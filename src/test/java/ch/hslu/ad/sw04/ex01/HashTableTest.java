package ch.hslu.ad.sw04.ex01;

import org.junit.Assert;
import org.junit.Test;

public class HashTableTest {

	@Test
	public void testPutEntry() {
		HashTable table = new HashTable();
		Assert.assertTrue(table.put("Dog"));
		Assert.assertTrue(table.put("Cat"));
		Assert.assertFalse(table.put("Dog")); // already added
	}

	@Test
	public void testRemoveEntry() {
		HashTable table = new HashTable();
		table.put("Dog");
		table.put("Cat");
		Assert.assertTrue(table.remove("Dog"));
		Assert.assertTrue(table.remove("Cat"));
		Assert.assertFalse(table.remove("Dog")); // already removed
	}

	@Test
	public void testGetEntry() {
		HashTable table = new HashTable();
		String dog = "Dog";
		table.put(dog);
		Assert.assertEquals(dog, table.get(dog.hashCode()));
		Assert.assertNull(table.get("Cat".hashCode()));
	}
}