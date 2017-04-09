package ch.hslu.ad.sw07.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import ch.hslu.ad.sw07.ex02.parkingStrategy.ClosestParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.ParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SearchLeastOccupiedParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SequentialParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SequentialWaitingParkingStrategy;

public class ParkingSimulation {

    private final int CAR_PARKS = 3;
    private final int CAR_PARK_CAPACITY = 100;
    private final int SIMULATION_DURATION = 12 * 60; // 12 hours (millis)
    private final int CARS_VISITING = 10_000;

    private final static int MIN_WAIT_TIME = 5;
    private final static int MAX_WAIT_TIME = 30;

    private List<CarPark> carParks = new ArrayList<>(CAR_PARKS);
    private List<Car> cars = new ArrayList<>(CARS_VISITING);

    private int succeeded = 0;
    private int failed = 0;

    public static void main(String args[]) {
        new ParkingSimulation().perform();
    }

    //
    // FIXME fix the simulation as soon as you have the nerves for it
    //
    public void perform() {
        for (int i = 0; i < CAR_PARKS; i++) {
            carParks.add(new CarPark(CAR_PARK_CAPACITY, i + 1));
        }
        for (int i = 0; i < CARS_VISITING; i++) {
            int waitTolerance = ThreadLocalRandom.current().nextInt(MIN_WAIT_TIME, MAX_WAIT_TIME + 1);
            cars.add(new Car(getParkingStrategy(i, waitTolerance), waitTolerance));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        String simulationDetails = String.format("%d car parks, %d spots per car park, %d duration, %d cars visiting",
                CAR_PARKS, CAR_PARK_CAPACITY, SIMULATION_DURATION, CARS_VISITING);
        System.out.println("starting simulation with " + simulationDetails);

        List<Future<Boolean>> attempts = new ArrayList<>();
        for (Car car : cars) {
            Future<Boolean> atempt = executorService.submit(car);
            attempts.add(atempt);
        }

        try {
            executorService.awaitTermination(SIMULATION_DURATION, TimeUnit.MILLISECONDS);
            for (Future<Boolean> attempt : attempts) {
                Boolean success;
                try {
                    success = attempt.get();
                    if (success != null) {
                        if (success) {
                            succeeded++;
                        } else {
                            failed++;
                        }
                    }
                } catch (ExecutionException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        int quota = (int) (((float) succeeded / CARS_VISITING) * 100);
        String result = String.format(
                "Simulation done, %d cars were able to park, %d cars were not able to park (%d%%)", succeeded, failed,
                quota);
        System.out.println(result);
        executorService.shutdown();
    }

    private ParkingStrategy getParkingStrategy(int i, int waitTolerance) {
        int s = i % 3;
        switch (s) {
        case 0:
            return new ClosestParkingStrategy(carParks);
        case 1:
            return new SequentialParkingStrategy(carParks);
        case 2:
            return new SequentialWaitingParkingStrategy(carParks, waitTolerance);
        case 3:
            return new SearchLeastOccupiedParkingStrategy(carParks);
        default:
            return null;
        }
    }

    public int getVisitingCars() {
        return 0;
    }

    public int getParkedCars() {
        return 0;
    }

    public int getUnparkedCars() {
        return 0;
    }
}
