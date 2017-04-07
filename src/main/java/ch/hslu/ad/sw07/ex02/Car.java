package ch.hslu.ad.sw07.ex02;

import java.util.concurrent.ThreadLocalRandom;

import ch.hslu.ad.sw07.ex02.parkingStrategy.ParkingStrategy;

public class Car implements Runnable {

    private final static int MIN_PARKING_DURATION = 1 * 60; // one hour
    private final static int MAX_PARKING_DURATION = 8 * 60; // eight hours

    private final static int MIN_WAIT_TIME = 5;
    private final static int MAX_WAIT_TIME = 30;

    private final int parkingDuration;
    private final int waitTolerance;

    private final String licensePlate;

    private final ParkingStrategy parkingStrategy;

    public Car(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
        parkingDuration = ThreadLocalRandom.current().nextInt(MIN_PARKING_DURATION, MAX_PARKING_DURATION + 1);
        waitTolerance = ThreadLocalRandom.current().nextInt(MIN_WAIT_TIME, MAX_WAIT_TIME + 1);
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

    @Override
    public void run() {
        // TODO
    }

    private static String buildLicensePlate() {
        StringBuilder licensePlateStr = new StringBuilder();
        licensePlateStr.append((char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1));
        licensePlateStr.append((char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1));
        licensePlateStr.append((char) ThreadLocalRandom.current().nextInt(1, 1_000_000));
        return licensePlateStr.toString();
    }
}
