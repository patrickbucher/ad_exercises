package ch.hslu.ad.sw09.ex04;

public class FixedSizeHeap implements IntegerHeap {

    private final int capacity;

    private final int heap[];

    private int size = 0;

    public FixedSizeHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    @Override
    public int getMax() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        int max = heap[0];
        size--;
        heap[0] = heap[size];
        heap[size] = -1;
        sink();
        return max;
    }

    @Override
    public void insert(int number) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full.");
        }
        heap[size] = number;
        size++;
        int newElementIndex = size - 1;
        raise(newElementIndex);
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int[] getRawHeap() {
        return heap;
    }

    private void sink() {
        boolean sunk = false;
        int l = 1, f = 0, r = 2;
        while (!sunk) {
            if (heap[f] < heap[l] || heap[f] < heap[r]) {
                int biggerChildIndex = heap[l] > heap[r] ? l : r;
                swap(f, biggerChildIndex);
                f = biggerChildIndex;
                l = (2 * f) + 1;
                r = 2 * (f + 1);
                if (l >= size || r >= size) {
                    sunk = true;
                }
            } else {
                sunk = true;
            }
        }
    }

    private void raise(int i) {
        boolean risen = false;
        while (!risen) {
            int father = (i - 1) / 2;
            if (heap[i] > heap[father]) {
                swap(i, father);
                i = father;
                if (i == 0) {
                    risen = true;
                }
            } else {
                risen = true;
            }
        }
    }

    private void swap(int a, int b) {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}
