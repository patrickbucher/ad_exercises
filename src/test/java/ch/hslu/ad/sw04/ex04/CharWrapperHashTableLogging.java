package ch.hslu.ad.sw04.ex04;

import org.junit.Test;

public class CharWrapperHashTableLogging {

	// @Test
	public void testAddRemoveOneEntry() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		table.put(new CharWrapper('a'));
		table.remove(new CharWrapper('a'));
	}

	// @Test
	public void testAddRemoveTwoEntriesNoCollision() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		table.put(new CharWrapper('a'));
		table.put(new CharWrapper('b'));
		table.remove(new CharWrapper('a'));
		table.remove(new CharWrapper('b'));
	}

	// @Test
	public void testAddRemoveThreeEntriesNoCollision() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		table.put(new CharWrapper('a'));
		table.put(new CharWrapper('b'));
		table.put(new CharWrapper('c'));
		table.remove(new CharWrapper('a'));
		table.remove(new CharWrapper('b'));
		table.remove(new CharWrapper('c'));
	}

	// @Test
	public void testAddRemoveTwoEntriesWithCollision() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		char first = 'a';
		char second = 'a' + CharWrapperHashTable.SIZE;
		table.put(new CharWrapper(first));
		table.put(new CharWrapper(second));
		table.remove(new CharWrapper(first));
		table.remove(new CharWrapper(second));
	}

	// @Test
	public void testAddRemoveThreeEntriesWithCollision() {
		CharWrapperHashTable table = new CharWrapperHashTable();
		char first = 'a';
		char second = 'a' + CharWrapperHashTable.SIZE;
		char third = 'a' + CharWrapperHashTable.SIZE * 2;
		table.put(new CharWrapper(first));
		table.put(new CharWrapper(second));
		table.put(new CharWrapper(third));
		table.remove(new CharWrapper(first));
		table.remove(new CharWrapper(second));
		table.remove(new CharWrapper(third));
	}

	@Test
	public void testAddRemoveFourEntriesWithCollision() {
		long start = System.currentTimeMillis();
		CharWrapperHashTable table = new CharWrapperHashTable();
		char first = 'a';
		char second = 'a' + CharWrapperHashTable.SIZE;
		char third = 'a' + CharWrapperHashTable.SIZE * 2;
		char fourth = 'a' + CharWrapperHashTable.SIZE * 3;
		table.put(new CharWrapper(first));
		table.put(new CharWrapper(second));
		table.put(new CharWrapper(third));
		table.put(new CharWrapper(fourth));
		table.remove(new CharWrapper(first));
		table.remove(new CharWrapper(second));
		table.remove(new CharWrapper(third));
		table.remove(new CharWrapper(fourth));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
