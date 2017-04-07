package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.CarPark;

public class SequentialParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SequentialParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }
    
    @Override
    public CarPark findCarPark() {
        return null;
    }

}
