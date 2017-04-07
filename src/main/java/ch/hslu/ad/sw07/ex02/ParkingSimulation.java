package ch.hslu.ad.sw07.ex02;

import java.util.ArrayList;
import java.util.List;

public class ParkingSimulation {

    private final int CAR_PARKS = 3;
    private final int CAR_PARK_CAPACITY = 100;

    private List<CarPark> carParks = new ArrayList<>(CAR_PARKS);

    public static void main(String args[]) {
        new ParkingSimulation().perform();
    }

    public void perform() {
        for (int i = 0; i < CAR_PARKS; i++) {
            carParks.add(new CarPark(CAR_PARK_CAPACITY));
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
