package ch.hslu.ad.sw07.ex02;

public class CarPark {

    private final int capacity;
    private final int number;

    private int occupied;

    public CarPark(int capacity, int number) {
        this.capacity = capacity;
        this.number = number;
    }

    public synchronized void enter(Car car) {
        if (occupied < capacity) {
            occupied++;
            if (ParkingSimulation.LOGGING) {
                System.out.println(car + " entered " + this);
            }
        } else {
            throw new IllegalStateException("Can't enter car park, it's full.");
        }
    }

    public synchronized void leave(Car car) {
        occupied--;
        if (ParkingSimulation.LOGGING) {
            System.out.println(car + " left " + this);
        }
    }

    public synchronized int freeParkingFields() {
        return capacity - occupied;
    }

    @Override
    public String toString() {
        return String.format("Car Park #%d, %d/%d occupied", number, occupied, capacity);
    }
}
