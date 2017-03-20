package ch.hslu.ad.sw04.ex03;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class BucketListHashTableTest {

	@Test
	public void testPutEntry() {
		BucketListHashTable table = new BucketListHashTable();
		Assert.assertTrue(table.put("Dog"));
		Assert.assertTrue(table.put("Cat"));
		Assert.assertFalse(table.put("Dog")); // already added
	}

	@Test
	public void testRemoveEntry() {
		BucketListHashTable table = new BucketListHashTable();
		table.put("Dog");
		table.put("Cat");
		Assert.assertTrue(table.remove("Dog"));
		Assert.assertTrue(table.remove("Cat"));
		Assert.assertFalse(table.remove("Dog")); // already removed
	}

	@Test
	public void testGetEntry() {
		BucketListHashTable table = new BucketListHashTable();
		String dog = "Dog";
		table.put(dog);
		Assert.assertEquals(dog, table.get(dog.hashCode()));
		Assert.assertNull(table.get("Cat".hashCode()));
	}

	@Test
	public void testSizeEmpty() {
		BucketListHashTable table = new BucketListHashTable();
		Assert.assertEquals(0, table.getSize());
	}

	@Test
	public void testSizeFull() {
		BucketListHashTable table = new BucketListHashTable();
		final int fill = BucketListHashTable.SIZE;
		for (char c = 'a'; c < ('a' + fill); c++) {
			table.put(String.valueOf(c));
		}
		Assert.assertEquals(fill, table.getSize());
	}

	@Test
	public void createIndexCollision() {
		Character first = 'a';
		Character second = 'a' + BucketListHashTable.SIZE;
		int firstIndex = first.hashCode() % BucketListHashTable.SIZE;
		int secondIndex = second.hashCode() % BucketListHashTable.SIZE;
		Assert.assertEquals(firstIndex, secondIndex);
	}

	@Test
	public void testPutCollidingEntries() {
		BucketListHashTable table = new BucketListHashTable();
		Character first = 'a';
		Character second = 'a' + BucketListHashTable.SIZE;
		Character last = 'c';
		table.put(first);
		table.put(last);
		Assert.assertTrue(table.put(second));
		Assert.assertEquals(3, table.getSize());
		Character third = 'a' + BucketListHashTable.SIZE * 2;
		Assert.assertTrue(table.put(third));
		Assert.assertEquals(4, table.getSize());
	}

	@Test
	public void testGetWithCollidingEntries() {
		BucketListHashTable table = new BucketListHashTable();
		Character first = 'a';
		table.put(first);
		Character last = 'd';
		table.put(last);
		Character second = 'a' + BucketListHashTable.SIZE;
		table.put(second);
		Character third = 'a' + BucketListHashTable.SIZE * 2;
		table.put(third);
		Assert.assertEquals(first, table.get(first.hashCode()));
		Assert.assertEquals(second, table.get(second.hashCode()));
		Assert.assertEquals(third, table.get(third.hashCode()));
		Assert.assertEquals(last, table.get(last.hashCode()));
	}

	@Test
	public void testGetAllElements() {
		BucketListHashTable table = new BucketListHashTable();
		table.put('a');
		table.put('b');
		table.put('c');
		Collection<Object> allElements = table.getAllElements();
		Assert.assertEquals(3, allElements.size());
		Assert.assertTrue(allElements.contains('a'));
		Assert.assertTrue(allElements.contains('b'));
		Assert.assertTrue(allElements.contains('c'));
	}

	@Test
	public void breakCollisionDomain() {
		BucketListHashTable table = new BucketListHashTable();
		Character first = 'a';
		Character second = 'a' + BucketListHashTable.SIZE;
		Character third = 'a' + BucketListHashTable.SIZE * 2;
		table.put(first);
		table.put(second);
		table.put(third);
		Assert.assertEquals(3, table.getSize());
		Assert.assertEquals(third, table.get(third.hashCode()));
		table.remove(second);
		Assert.assertNotNull(table.get(third.hashCode())); // now works
	}
}