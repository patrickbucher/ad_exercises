package ch.hslu.ad.sw07.ex02.parkingStrategy;

import ch.hslu.ad.sw07.ex02.Car;

public interface ParkingStrategy {

    public Boolean park(Car car) throws Exception;
}
