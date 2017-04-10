package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.Car;
import ch.hslu.ad.sw07.ex02.CarPark;
import ch.hslu.ad.sw07.ex02.ParkingSimulation;

public class SequentialParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SequentialParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        for (CarPark carPark : carParks) {
            if (carPark.freeParkingFields() > 0) {
                try {
                    carPark.enter(car);
                } catch (IllegalStateException ex) {
                    return false;
                }
                Thread.sleep(car.getParkingDuration());
                carPark.leave(car);
                return true;
            }
        }
        if (ParkingSimulation.LOGGING) {
            System.out.println(car + " was not able to find a car park");
        }
        return false;
    }
}
