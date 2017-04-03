package ch.hslu.ad.sw06.ex05;

import java.util.Random;

public final class SumTask {

    private final int numbers[];

    public SumTask(int n) {
        Random random = new Random(System.currentTimeMillis());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(100) + 1; // 1..100
        }
    }

    public int[] getNumbers() {
        return numbers;
    }
}
