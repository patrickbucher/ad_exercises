package ch.hslu.ad.sw04.ex01;

public class HashTable {

	public static final int SIZE = 10;

	private Object entries[] = new Object[SIZE];

	public boolean put(Object entry) {
		int index = calculateIndex(entry);
		if (entries[index] != null) {
			return false;
		}
		entries[index] = entry;
		return true;
	}

	public boolean remove(Object entry) {
		int index = calculateIndex(entry);
		if (entries[index] == null) {
			return false;
		}
		entries[index] = null;
		return true;
	}

	public Object get(int hashCode) {
		int index = calculateIndex(hashCode);
		return entries[index];
	}

	private int calculateIndex(Object entry) {
		return entry.hashCode() % SIZE;
	}

	private int calculateIndex(int hashCode) {
		return hashCode % SIZE;
	}
}
