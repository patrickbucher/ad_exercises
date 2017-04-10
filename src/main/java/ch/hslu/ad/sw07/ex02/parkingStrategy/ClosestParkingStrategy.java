package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.Car;
import ch.hslu.ad.sw07.ex02.CarPark;
import ch.hslu.ad.sw07.ex02.ParkingSimulation;

public class ClosestParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public ClosestParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        CarPark carPark = carParks.get(0);
        if (carPark.freeParkingFields() > 0) {
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
        } else {
            if (ParkingSimulation.LOGGING) {
                System.out.println(car + " was not able to park in " + carPark);
            }
            return false;
        }
    }

}
