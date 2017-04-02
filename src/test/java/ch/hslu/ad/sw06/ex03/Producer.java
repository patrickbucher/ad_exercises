package ch.hslu.ad.sw06.ex03;

public class Producer implements Runnable {

    private final int itemsToProduce;
    private final BoundedBuffer<String> buffer;
    private int produced = 0;

    public Producer(int itemsToProduce, BoundedBuffer<String> buffer) {
        this.itemsToProduce = itemsToProduce;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < itemsToProduce; i++) {
            String str = "String" + i;
            buffer.put(str);
            produced++;
        }
    }

    public int getProduced() {
        return produced;
    }
}
