package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.Car;
import ch.hslu.ad.sw07.ex02.CarPark;
import ch.hslu.ad.sw07.ex02.ParkingSimulation;

public class SearchLeastOccupiedParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SearchLeastOccupiedParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        int mostFreeSpots = 0;
        CarPark leastOccupiedCarPark = null;
        for (CarPark carPark : carParks) {
            if (carPark.freeParkingFields() > mostFreeSpots) {
                mostFreeSpots = carPark.freeParkingFields();
                leastOccupiedCarPark = carPark;
            }
        }
        if (leastOccupiedCarPark == null) {
            if (ParkingSimulation.LOGGING) {
                System.out.println(car + " was not able to find a car park");
            }
            return false;
        }
        try {
            leastOccupiedCarPark.enter(car);
        } catch (IllegalStateException e) {
            return false;
        }
        try {
            Thread.sleep(car.getParkingDuration());
        } catch (InterruptedException e) {
            return false;
        }
        leastOccupiedCarPark.leave(car);
        return true;
    }

}
