package ch.hslu.ad.sw09.ex04;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class FixedSizeHeapTest {

    @Test
    public void testInitialization() {
        FixedSizeHeap heap = new FixedSizeHeap(10);
        Assert.assertFalse(heap.isFull());
        Assert.assertEquals(0, heap.getSize());
    }

    @Test
    public void testHeapValidation() {
        int validHeap[] = new int[] { 100, 99, 98, 97, 96, 95, 94 };
        Assert.assertTrue(isValidHeap(validHeap, validHeap.length - 1));
        int invalidHeap[] = new int[] { 100, 97, 98, 99, 96, 95, 94 };
        Assert.assertFalse(isValidHeap(invalidHeap, invalidHeap.length - 1));
    }

    @Test
    public void testInsertThreeElements() {
        FixedSizeHeap heap = new FixedSizeHeap(3);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        Assert.assertTrue(heap.isFull());
        Assert.assertTrue(isValidHeap(heap.getRawHeap(), heap.getRawHeap().length - 1));
    }

    @Test
    public void testInsertThreeElementsReverse() {
        FixedSizeHeap heap = new FixedSizeHeap(3);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        Assert.assertTrue(heap.isFull());
        Assert.assertTrue(isValidHeap(heap.getRawHeap(), heap.getRawHeap().length - 1));
    }

    @Test
    public void testGetMaxValueOrderedNumbers() {
        final int capacity = 1_000_000;
        IntegerHeap heap = new FixedSizeHeap(capacity);
        for (int i = 0; i < capacity; i++) {
            heap.insert(i + 1);
        }
        for (int expected = capacity; heap.getSize() > 0; expected--) {
            Assert.assertEquals(expected, heap.getMax());
        }
    }

    @Test
    public void testGetMaxValueRandomNumbers() {
        final int capacity = 1_000_000;
        Random random = new Random(System.currentTimeMillis());
        IntegerHeap heap = new FixedSizeHeap(capacity);
        for (int i = 0; i < capacity; i++) {
            heap.insert(random.nextInt(Integer.MAX_VALUE));
        }
        int current = Integer.MAX_VALUE, last;
        while (heap.getSize() > 0) {
            last = current;
            current = heap.getMax();
            Assert.assertTrue(last >= current);
        }
    }

    // valid heap: fathers are bigger than their children
    private boolean isValidHeap(int heap[], int lastIndex) {
        for (int i = 0; i <= lastIndex; i++) {
            int leftChild = (2 * i) + 1;
            int rightChild = 2 * (i + 1);
            if (leftChild > lastIndex) {
                break;
            }
            if (heap[leftChild] > heap[i]) {
                return false;
            }
            if (rightChild > lastIndex) {
                break;
            }
            if (heap[rightChild] > heap[i]) {
                return false;
            }
        }
        return true;
    }
}
