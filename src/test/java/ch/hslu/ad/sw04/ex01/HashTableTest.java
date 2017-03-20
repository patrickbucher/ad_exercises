package ch.hslu.ad.sw04.ex01;

import java.util.Collection;

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

	@Test
	public void testSizeEmpty() {
		HashTable table = new HashTable();
		Assert.assertEquals(0, table.getSize());
		Assert.assertFalse(table.isFull());
	}

	@Test
	public void testSizeFull() {
		HashTable table = new HashTable();
		final int fill = HashTable.SIZE;
		for (char c = 'a'; c < ('a' + fill); c++) {
			table.put(String.valueOf(c));
		}
		Assert.assertEquals(fill, table.getSize());
		Assert.assertTrue(table.isFull());
	}

	@Test
	public void createIndexCollision() {
		Character first = 'a';
		Character second = 'a' + HashTable.SIZE;
		int firstIndex = first.hashCode() % HashTable.SIZE;
		int secondIndex = second.hashCode() % HashTable.SIZE;
		Assert.assertEquals(firstIndex, secondIndex);
	}

	@Test
	public void testPutCollidingEntries() {
		HashTable table = new HashTable();
		Character first = 'a';
		Character second = 'a' + HashTable.SIZE;
		Character last = 'c';
		table.put(first);
		table.put(last);
		// now: [-][a][-][c][-]

		Assert.assertTrue(table.put(second));
		// now: [-][-][a][X][c][-]
		Assert.assertEquals(3, table.getSize());

		Character third = 'a' + HashTable.SIZE * 2;
		// it's not allowed to insert it at the right of c!
		Assert.assertFalse(table.put(third));
		Assert.assertEquals(3, table.getSize());
	}

	@Test
	public void testGetWithCollidingEntries() {
		HashTable table = new HashTable();
		Character first = 'a';
		table.put(first);
		Character last = 'd';
		table.put(last);
		Character second = 'a' + HashTable.SIZE;
		table.put(second);
		Character third = 'a' + HashTable.SIZE * 2;
		table.put(third);
		// now: [-][a][X][Y][d]
		Assert.assertEquals(first, table.get(first.hashCode()));
		Assert.assertEquals(second, table.get(second.hashCode()));
		Assert.assertEquals(third, table.get(third.hashCode()));
		Assert.assertEquals(last, table.get(last.hashCode()));
	}

	@Test
	public void testPutFullTable() {
		HashTable table = new HashTable();
		char c = findEntryMappingToIndexZero();
		while (!table.isFull()) {
			table.put(c);
			c += HashTable.SIZE; // same collision domain
		}
		Assert.assertEquals(HashTable.SIZE, table.getSize());
		Assert.assertFalse(table.put(c));
	}

	@Test
	public void testGetFullTable() {
		HashTable table = new HashTable();
		char c = findEntryMappingToIndexZero();
		Character last = 0;
		while (!table.isFull()) {
			table.put(c);
			last = c;
			c += HashTable.SIZE; // same collision domain
		}
		Assert.assertEquals(last, table.get(last.hashCode()));
	}

	@Test
	public void testGetAllElements() {
		HashTable table = new HashTable();
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
		HashTable table = new HashTable();
		Character first = 'a';
		Character second = 'a' + HashTable.SIZE;
		Character third = 'a' + HashTable.SIZE * 2;
		table.put(first);
		table.put(second);
		table.put(third);
		Assert.assertEquals(3, table.getSize());
		Assert.assertEquals(third, table.get(third.hashCode()));
		table.remove(second);
		Assert.assertNotNull(table.get(third.hashCode())); // fails
	}

	private Character findEntryMappingToIndexZero() {
		Character c = 'a';
		while (c.hashCode() % HashTable.SIZE != 0) {
			c++;
		}
		return c;
	}

}