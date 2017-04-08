package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.CarPark;

public class SequentialWaitingParkingStrategy implements ParkingStrategy {

    private final List<CarPark> carParks;
    private final int waitTolerance;

    public SequentialWaitingParkingStrategy(List<CarPark> carParks, int waitTolerance) {
        this.carParks = carParks;
        this.waitTolerance = waitTolerance;
    }

    @Override
    public CarPark findCarPark() {
        for (CarPark carPark : carParks) {
            if (carPark.freeParkingFields() == 0) {
                // TODO synchronize here?
                try {
                    Thread.sleep(waitTolerance);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                if (carPark.freeParkingFields() > 0) {
                    return carPark;
                }
            }
        }
        return null;
    }

}
