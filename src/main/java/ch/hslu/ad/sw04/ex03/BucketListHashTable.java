package ch.hslu.ad.sw04.ex03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BucketListHashTable {

	public static final int SIZE = 10;

	private SingleLinkedList entries[] = new SingleLinkedList[SIZE];

	private int size = 0;

	public boolean put(Object entry) {
		int index = calculateIndex(entry);
		if (entries[index] == null) {
			entries[index] = new SingleLinkedList();
		}
		if (entries[index].contains(entry)) {
			return false;
		}
		entries[index].add(entry);
		size++;
		return true;
	}

	public boolean remove(Object entry) {
		int index = calculateIndex(entry);
		if (entries[index] == null || !entries[index].contains(entry)) {
			size--;
			return false;
		}
		return entries[index].remove(entry);
	}

	public Object get(int hashCode) {
		int index = calculateIndex(hashCode);
		if (entries[index] == null) {
			return null;
		}
		Iterator<Object> iterator = entries[index].iterator();
		while (iterator.hasNext()) {
			Object entry = iterator.next();
			if (entry.hashCode() == hashCode) {
				return entry;
			}
		}
		return null;
	}

	public int getSize() {
		return size;
	}

	public Collection<Object> getAllElements() {
		Collection<Object> allElements = new ArrayList<>();
		for (int n = 0; n < entries.length; n++) {
			if (entries[n] == null) {
				continue;
			}
			Iterator<Object> listEntries = entries[n].iterator();
			while (listEntries.hasNext()) {
				allElements.add(listEntries.next());
			}
		}
		return allElements;
	}

	private int calculateIndex(Object entry) {
		return entry.hashCode() % SIZE;
	}

	private int calculateIndex(int hashCode) {
		return hashCode % SIZE;
	}
}
