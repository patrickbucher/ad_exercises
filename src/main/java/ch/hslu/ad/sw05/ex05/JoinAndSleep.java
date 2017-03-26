package ch.hslu.ad.sw05.ex05;

public class JoinAndSleep extends Thread {

    public JoinAndSleep(Runnable runnable) {
        super(runnable);
    }

    public static void main(String[] args) {

        JoinAndSleep t3 = new JoinAndSleep(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 start");
                try {
                    System.out.println("t3 sleeping");
                    sleep(4000);
                    System.out.println("t3 sleeping done");
                } catch (InterruptedException e) {
                    System.out.println("t3 interrupted");
                }
            }
        });

        JoinAndSleep t2 = new JoinAndSleep(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2 start");
                    System.out.println("t2 waiting for t3");
                    t3.join();
                    System.out.println("t2 waiting for t3 done");
                    System.out.println("t2 sleeping");
                    sleep(3000);
                    System.out.println("t2 sleeping done");
                } catch (InterruptedException e) {
                    System.out.println("t2 interrupted");
                }
            }
        });

        JoinAndSleep t1 = new JoinAndSleep(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1 start");
                    System.out.println("t1 waiting for t2");
                    t2.join();
                    System.out.println("t1 waiting for t2 done");
                    System.out.println("t1 sleeping");
                    sleep(2000);
                    System.out.println("t1 sleeping done");
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupted");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t3.interrupt();
    }

}
