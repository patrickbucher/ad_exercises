package ch.hslu.ad.sw05.ex04;

public class AdditionApplication {

	public static void main(String[] args) {
		AdditionTask addition = new AdditionTask(1, 50);
		addition.start();
		try {
			Thread.sleep(500);
			addition.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
