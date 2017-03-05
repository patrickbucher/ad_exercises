package ch.hslu.ad.sw01.ex02;

public class Ex02 {

    private static int calls = 0;

    public static void main(String[] args) {
        for (int n = 1; n <= 10; n++) {
            long start = System.currentTimeMillis();
            task(n);
            long end = System.currentTimeMillis();
            System.out.println(String.format("%8d %8d %8d", n, calls, end - start));
            calls = 0;
        }
    }

    private static void task(int n) {
        task1();
        task1();
        task1();
        task1();
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
            for (int j = 0; j < n; j++) {
                task3();
                task3();
            }
        }
    }

    private static void task1() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calls++;
    }

    private static void task2() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calls++;
    }

    private static void task3() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calls++;
    }

}
