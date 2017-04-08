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
            System.out.println(car + " entered the parking lot #" + number);
        } else {
            try {
                wait(car.getWaitTolerance());
            } catch (InterruptedException iEx) {
                System.out.println(car + " waited for too long for parking lot #" + number + " and drives away");
            }
        }
    }

    public synchronized void leave(Car car) {
        occupied--;
        System.out.println(car + " left the parking lot #" + number);
    }

    public synchronized int freeParkingFields() {
        return capacity - occupied;
    }

    @Override
    public String toString() {
        return String.format("Car Park #%d, %d/%d occupied", number, occupied, capacity);
    }
}
