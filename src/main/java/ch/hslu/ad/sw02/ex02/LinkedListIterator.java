package ch.hslu.ad.sw02.ex02;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private final DoubleLinkedList<T> list;

    private Element<T> current;

    public LinkedListIterator(DoubleLinkedList<T> linkedList) {
        this.list = linkedList;
        this.current = list.getFirstElement();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        Element<T> tmp = current;
        current = current.getNext();
        return tmp.getData();
    }

}
