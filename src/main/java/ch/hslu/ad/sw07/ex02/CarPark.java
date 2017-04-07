package ch.hslu.ad.sw07.ex02;

public class CarPark {

    private final int capacity;

    private int occupied;

    public CarPark(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enter(Car car) {
        if (occupied < capacity) {
            occupied++;
        } else {
            try {
                wait(car.getWaitTolerance());
            } catch (InterruptedException iEx) {
                // TODO car waited too long, goes away
            }
        }
    }

    public synchronized void leave() {
        occupied--;
    }

    public synchronized int freeParkingFields() {
        return capacity - occupied;
    }
}
