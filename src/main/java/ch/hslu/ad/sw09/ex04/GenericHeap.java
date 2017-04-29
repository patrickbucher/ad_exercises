package ch.hslu.ad.sw09.ex04;

public interface GenericHeap<T extends Comparable<T>> {
    public T getMax();

    public void insert(T element);

    public int getSize();
}
