package ch.hslu.ad.sw02.ex02;

import java.util.Iterator;

public final class DoubleLinkedList<T> implements Iterable<T> {

    private Element<T> start = null;

    private int size = 0;

    /**
     * adds element at the beginning of the linked list
     * 
     * @param element
     */
    public synchronized void add(Element<T> element) {
        if (start == null) {
            start = element;
        } else {
            Element<T> lastInserted = start;
            lastInserted.setPrevious(element);
            element.setNext(lastInserted);
            start = element;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public Element<T> getFirstElement() {
        return start;
    }

    public boolean contains(Element<T> element) {
        if (start == null) {
            return false;
        }
        Element<T> tmp = start;
        do {
            if (tmp.equals(element)) {
                return true;
            }
            tmp = tmp.getNext();
        } while (tmp != null);
        return false;
    }

    public Element<T> pop() {
        if (start == null) {
            return null;
        }
        Element<T> first = start;
        if (first.getNext() != null) {
            first.getNext().setPrevious(null);
        }
        start = start.getNext();
        first.setNext(null);
        size--;
        return first;
    }

    public void removeElement(Element<T> element) {
        if (start == null) {
            return;
        }
        Element<T> tmp = start;
        do {
            if (tmp.equals(element)) {
                if (tmp == start) {
                    start = tmp.getNext();
                }
                if (tmp.getNext() != null) {
                    tmp.getNext().setPrevious(tmp.getPrevious());
                }
                if (tmp.getPrevious() != null) {
                    tmp.getPrevious().setNext(tmp.getNext());
                }
                size--;
                return;
            } else {
                tmp = tmp.getNext();
            }
        } while (tmp != null);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

}
