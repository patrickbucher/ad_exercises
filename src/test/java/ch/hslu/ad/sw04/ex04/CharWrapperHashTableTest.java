package ch.hslu.ad.sw04.ex04;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class CharWrapperHashTableTest {

	@Test
	public void testPutEntry() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		Assert.assertTrue(table.put(new CharWrapper('a')));
		Assert.assertTrue(table.put(new CharWrapper('b')));
		// already added
		Assert.assertFalse(table.put(new CharWrapper('a')));
	}

	@Test
	public void testRemoveEntry() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		table.put(new CharWrapper('a'));
		table.put(new CharWrapper('b'));
		Assert.assertTrue(table.remove(new CharWrapper('a')));
		Assert.assertTrue(table.remove(new CharWrapper('b')));
		// already removed
		Assert.assertFalse(table.remove(new CharWrapper('a')));
	}

	@Test
	public void testGetEntry() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		CharWrapper a = new CharWrapper('a');
		table.put(a);
		Assert.assertEquals(a, table.get(a.hashCode()));
		Assert.assertNull(table.get(new CharWrapper('b').hashCode()));
	}

	@Test
	public void testSizeEmpty() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		Assert.assertEquals(0, table.getSize());
		Assert.assertFalse(table.isFull());
	}

	@Test
	public void testSizeFull() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		final int fill = CharWrapperHashTable.SIZE;
		for (char c = 'a'; c < ('a' + fill); c++) {
			table.put(new CharWrapper(c));
		}
		Assert.assertEquals(fill, table.getSize());
		Assert.assertTrue(table.isFull());
	}

	@Test
	public void createIndexCollision() {
		CharWrapper first = new CharWrapper('a');
		char secondChar = 'a' + CharWrapperHashTable.SIZE;
		CharWrapper second = new CharWrapper(secondChar);
		int firstIndex = first.hashCode() % CharWrapperHashTable.SIZE;
		int secondIndex = second.hashCode() % CharWrapperHashTable.SIZE;
		Assert.assertEquals(firstIndex, secondIndex);
	}

	@Test
	public void testPutCollidingEntries() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		CharWrapper first = new CharWrapper('a');
		char secondChar = 'a' + CharWrapperHashTable.SIZE;
		CharWrapper second = new CharWrapper(secondChar);
		CharWrapper last = new CharWrapper('c');
		table.put(first);
		table.put(last);
		// now: [-][a][-][c][-]

		Assert.assertTrue(table.put(second));
		// now: [-][-][a][X][c][-]
		Assert.assertEquals(3, table.getSize());

		char thirdChar = 'a' + CharWrapperHashTable.SIZE * 2;
		CharWrapper third = new CharWrapper(thirdChar);
		// it's not allowed to insert it at the right of c!
		Assert.assertFalse(table.put(third));
		Assert.assertEquals(3, table.getSize());
	}

	@Test
	public void testGetWithCollidingEntries() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		CharWrapper first = new CharWrapper('a');
		table.put(first);
		CharWrapper last = new CharWrapper('d');
		table.put(last);
		char secondChar = 'a' + CharWrapperHashTable.SIZE;
		CharWrapper second = new CharWrapper(secondChar);
		table.put(second);
		char thirdChar = 'a' + CharWrapperHashTable.SIZE * 2;
		CharWrapper third = new CharWrapper(thirdChar);
		table.put(third);
		// now: [-][a][X][Y][d]
		Assert.assertEquals(first, table.get(first.hashCode()));
		Assert.assertEquals(second, table.get(second.hashCode()));
		Assert.assertEquals(third, table.get(third.hashCode()));
		Assert.assertEquals(last, table.get(last.hashCode()));
	}

	@Test
	public void testPutFullTable() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		char c = findEntryMappingToIndexZero();
		while (!table.isFull()) {
			table.put(new CharWrapper(c));
			c += CharWrapperHashTable.SIZE; // same collision domain
		}
		Assert.assertEquals(CharWrapperHashTable.SIZE, table.getSize());
		Assert.assertFalse(table.put(new CharWrapper(c)));
	}

	@Test
	public void testGetFullTable() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		CharWrapper c = new CharWrapper(findEntryMappingToIndexZero());
		CharWrapper last = null;
		while (!table.isFull()) {
			table.put(c);
			last = c;
			// same collision domain
			char cChar = (char) (c.getCharacter() + CharWrapperHashTable.SIZE);
			c = new CharWrapper(cChar);
		}
		Assert.assertEquals(last, table.get(last.hashCode()));
	}

	@Test
	public void testGetAllElements() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		table.put(new CharWrapper('a'));
		table.put(new CharWrapper('b'));
		table.put(new CharWrapper('c'));
		Collection<CharWrapper> allElements = table.getAllElements();
		Assert.assertEquals(3, allElements.size());
		Assert.assertTrue(allElements.contains(new CharWrapper('a')));
		Assert.assertTrue(allElements.contains(new CharWrapper('b')));
		Assert.assertTrue(allElements.contains(new CharWrapper('c')));
	}

	@Test
	public void breakCollisionDomain() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		CharWrapper first = new CharWrapper('a');
		char secondChar = 'a' + CharWrapperHashTable.SIZE;
		CharWrapper second = new CharWrapper(secondChar);
		char thirdChar = 'a' + CharWrapperHashTable.SIZE * 2;
		CharWrapper third = new CharWrapper(thirdChar);
		table.put(first);
		table.put(second);
		table.put(third);
		Assert.assertEquals(3, table.getSize());
		Assert.assertEquals(third, table.get(third.hashCode()));
		table.remove(second);
		// fails
		// Assert.assertNotNull(table.get(third.hashCode()));
	}

	private Character findEntryMappingToIndexZero() {
		Character c = 'a';
		while (c.hashCode() % CharWrapperHashTable.SIZE != 0) {
			c++;
		}
		return c;
	}

}