package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.Car;
import ch.hslu.ad.sw07.ex02.CarPark;
import ch.hslu.ad.sw07.ex02.ParkingSimulation;

public class SequentialWaitingParkingStrategy implements ParkingStrategy {

    private final List<CarPark> carParks;

    public SequentialWaitingParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        for (CarPark carPark : carParks) {
            if (carPark.freeParkingFields() > 0) {
                return park(car, carPark);
            } else {
                try {
                    Thread.sleep(car.getWaitTolerance());
                } catch (InterruptedException e) {
                    throw new Exception(e);
                }
                if (carPark.freeParkingFields() > 0) {
                    return park(car, carPark);
                }
            }
        }
        if (ParkingSimulation.LOGGING) {
            System.out.println(car + " was not able to find a car park");
        }
        return false;
    }

    private boolean park(Car car, CarPark carPark) throws Exception {
        try {
            carPark.enter(car);
        } catch (IllegalStateException e) {
            return false;
        }
        try {
            Thread.sleep(car.getParkingDuration());
        } catch (InterruptedException e) {
            throw new Exception(e);
        }
        carPark.leave(car);
        return true;
    }
}
