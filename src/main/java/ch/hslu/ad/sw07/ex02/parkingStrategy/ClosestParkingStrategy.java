package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.CarPark;

public class ClosestParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public ClosestParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public CarPark findCarPark() {
        return null;
    }

}
