package ch.hslu.ad.sw07.ex02.parkingStrategy;

import java.util.List;

import ch.hslu.ad.sw07.ex02.CarPark;

public class SearchLeastOccupiedParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SearchLeastOccupiedParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public CarPark findCarPark() {
        int mostFreeSpots = 0;
        CarPark leastOccupiedCarPark = null;
        for (CarPark carPark : carParks) {
            if (carPark.freeParkingFields() > mostFreeSpots) {
                mostFreeSpots = carPark.freeParkingFields();
                leastOccupiedCarPark = carPark;
            }
        }
        return leastOccupiedCarPark;
    }

}
