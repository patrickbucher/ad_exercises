package ch.hslu.ad.sw07.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import ch.hslu.ad.sw07.ex02.parkingStrategy.ClosestParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.ParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SearchLeastOccupiedParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SequentialParkingStrategy;
import ch.hslu.ad.sw07.ex02.parkingStrategy.SequentialWaitingParkingStrategy;

public class ParkingSimulation {

    public static final boolean LOGGING = false;

    private static final int CAR_PARKS = 3;
    private static final int CAR_PARK_CAPACITY = 300;
    private static final int CARS_VISITING = 1000;

    private static final int MIN_WAIT_TIME = 1;
    private static final int MAX_WAIT_TIME = 5;

    private List<CarPark> carParks = new ArrayList<>(CAR_PARKS);
    private List<Car> cars = new ArrayList<>(CARS_VISITING);

    private int succeeded = 0;
    private int failed = 0;

    public static void main(String args[]) {
        new ParkingSimulation().perform();
    }

    public void perform() {
        for (int i = 0; i < CAR_PARKS; i++) {
            carParks.add(new CarPark(CAR_PARK_CAPACITY, i + 1));
        }
        for (int i = 0; i < CARS_VISITING; i++) {
            int waitTolerance = ThreadLocalRandom.current().nextInt(MIN_WAIT_TIME, MAX_WAIT_TIME + 1);
            cars.add(new Car(getParkingStrategy(i), waitTolerance));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_VISITING);
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executorService);
        String simulationDetails = String.format("%d car parks, %d spots per car park, %d cars visiting", CAR_PARKS,
                CAR_PARK_CAPACITY, CARS_VISITING);
        System.out.println("starting simulation with " + simulationDetails);

        for (Car car : cars) {
            completionService.submit(car);
        }
        try {
            for (int n = 0; n < CARS_VISITING; n++) {
                boolean success = completionService.take().get();
                if (success) {
                    succeeded++;
                } else {
                    failed++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int quota = (int) (((float) succeeded / CARS_VISITING) * 100);
        String result = String.format("done, %d cars did park, %d cars did not park (%d%%)", succeeded, failed, quota);
        System.out.println(result);
        executorService.shutdown();
    }

    private ParkingStrategy getParkingStrategy(int i) {
        int s = i % 4;
        switch (s) {
        case 0:
            return new ClosestParkingStrategy(carParks);
        case 1:
            return new SequentialParkingStrategy(carParks);
        case 2:
            return new SequentialWaitingParkingStrategy(carParks);
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
