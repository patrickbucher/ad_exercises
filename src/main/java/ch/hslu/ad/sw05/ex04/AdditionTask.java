package ch.hslu.ad.sw05.ex04;

public class AdditionTask implements Runnable {

    private static final int FORCE_FINISH_THRESHOLD_PERCENTAGE = 60;

    private static final boolean ALLOW_VIOLENT_STOP = false;

    private Thread thread;
    private boolean stopped = false;
    private boolean forcedFinish = false;

    private int from;
    private int to;

    private int current;

    public AdditionTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @SuppressWarnings("deprecation")
    public void requestStop() {
        if (ALLOW_VIOLENT_STOP) {
            // The professor told me to do so!
            thread.stop();
            System.out.println("violent stop");
        }
        String msg = "";
        int progress = estimateProgressPercentage(current);
        if (progress > FORCE_FINISH_THRESHOLD_PERCENTAGE && progress < 100) {
            forcedFinish = true;
            msg = String.format("%s was stopped; forced to finish at progress = %d%%", thread.getName(), progress);
        } else {
            msg = String.format("%s was stopped at progress = %d%%", thread.getName(), progress);
        }
        stopped = true;
        System.out.println(msg);
    }

    public boolean isStopped() {
        return stopped;
    }

    public int estimateProgressPercentage(int currentNumber) {
        int done = currentNumber - from;
        int total = to - from + 1;
        float percentage = (float) done / total;
        return (int) (percentage * 100);
    }

    public void run() {
        long sum = 0;
        for (current = from; (!isStopped() || forcedFinish) && current <= to; current++) {
            sum += current;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!isStopped() || forcedFinish) {
            System.out.println(String.format("%s: sum from %d to %d = %d", thread.getName(), from, to, sum));
        } else {
            System.out.println(String.format("%s: interrupted", thread.getName()));
        }
    }
}
