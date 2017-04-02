package ch.hslu.ad.div.ringBuffer;

public class RuntimeInfo implements Runnable {

    public static void main(String[] args) {
        Thread runtimeInfoThread = new Thread(new RuntimeInfo());
        runtimeInfoThread.start();
    }

    @Override
    public void run() {
        final int MEGS_IN_BYTES = 1024 * 1024;
        long freeMemory = Runtime.getRuntime().freeMemory() / MEGS_IN_BYTES;
        long totalMemory = Runtime.getRuntime().totalMemory() / MEGS_IN_BYTES;
        long maxMemory = Runtime.getRuntime().maxMemory() / MEGS_IN_BYTES;
        String memory = String.format("Memory: free %dM, total %dM, max %dM", freeMemory, totalMemory, maxMemory);
        String processors = String.format("Processors: %d", Runtime.getRuntime().availableProcessors());
        System.out.println(String.format("%s, %s", memory, processors));
    }

}
