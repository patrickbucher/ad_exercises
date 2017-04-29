package ch.hslu.ad.sw09.generic;

public interface GenericHeap<T extends Comparable<T>> {
    public T getMax();

    public void insert(T element);

    public int getSize();
}
