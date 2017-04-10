package ch.hslu.ad.sw07.ex02;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import ch.hslu.ad.sw07.ex02.parkingStrategy.ParkingStrategy;

public class Car implements Callable<Boolean> {

    // durations in milliseconds, one millisecond represents one minute
    private final static int MIN_PARKING_DURATION = 1 * 60;
    private final static int MAX_PARKING_DURATION = 8 * 60;

    private final int parkingDuration;
    private final int waitTolerance;

    private final String licensePlate;

    private final ParkingStrategy parkingStrategy;

    public Car(ParkingStrategy parkingStrategy, int waitTolerance) {
        this.parkingStrategy = parkingStrategy;
        this.waitTolerance = waitTolerance;
        parkingDuration = ThreadLocalRandom.current().nextInt(MIN_PARKING_DURATION, MAX_PARKING_DURATION + 1);
        licensePlate = buildLicensePlate();
    }

    public int getParkingDuration() {
        return parkingDuration;
    }

    public int getWaitTolerance() {
        return waitTolerance;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void run() {
    }

    @Override
    public Boolean call() throws Exception {
        return parkingStrategy.park(this);
    }

    @Override
    public String toString() {
        return licensePlate + " [" + parkingStrategy.getClass().getSimpleName() + "]";
    }

    private static String buildLicensePlate() {
        StringBuilder licensePlateStr = new StringBuilder();
        licensePlateStr.append((char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1));
        licensePlateStr.append((char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1));
        licensePlateStr.append('-');
        licensePlateStr.append(ThreadLocalRandom.current().nextInt(1, 1_000_000));
        return licensePlateStr.toString();
    }

}
