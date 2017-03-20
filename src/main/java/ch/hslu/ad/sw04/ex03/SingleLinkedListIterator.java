package ch.hslu.ad.sw04.ex03;

import java.util.Iterator;

public class SingleLinkedListIterator implements Iterator<Object> {

	private Node current;

	public SingleLinkedListIterator(Node start) {
		current = start;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Object next() {
		Node node = current;
		current = node.getNext();
		return node.getValue();
	}
}
