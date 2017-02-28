package ch.hslu.ad.sw01.ex05;

public class AckermannFunktion {

    private static int calls = 0;

    public static void main(String[] args) {
        System.out.println(String.format("%d %d", ack(3,1), calls));
    }

    public static int ack(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("n and m must be >= 0");
        }
        calls++;
        if (n == 0) {
            return m + 1;
        } else if (m == 0) {
            return ack(n - 1, 1);
        } else {
            return ack(n - 1, ack(n, m - 1));
        }
    }
}
