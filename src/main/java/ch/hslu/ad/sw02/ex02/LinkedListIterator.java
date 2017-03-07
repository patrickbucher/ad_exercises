package ch.hslu.ad.sw02.ex02;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

	private final DoubleLinkedList<Element<T>> list;

	private Element<T> current;

	public LinkedListIterator(DoubleLinkedList<Element<T>> linkedList) {
		this.list = linkedList;
		this.current = (Element<T>) list.getFirstElement();
	}

	@Override
	public boolean hasNext() {
		return current.getNext() != null;
	}

	@Override
	public T next() {
		Element<T> tmp = current;
		current = current.getNext();
		return tmp.getData();
	}

}
