package ch.hslu.ad.sw05.ex04;

public class AdditionApplication {

    public static void main(String[] args) {
        AdditionTask addition = new AdditionTask(1, 60);
        addition.start();
        try {
            Thread.sleep(500);
            addition.requestStop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
