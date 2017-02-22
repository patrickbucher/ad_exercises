package ch.hslu.ad.sw01.ex02;

public class Ex02 {

	private static int calls = 0;

	public static void main(String[] args) {
		task(4);
		System.out.println(calls);
	}

	private static void task(int n) {
		task1();
		task1();
		task1();
		task1();
		for (int i = 0; i < n; i++) {
			task2();
			task2();
			task2();
			for (int j = 0; j < n; j++) {
				task3();
				task3();
			}
		}
	}

	private static void task1() {
		calls++;
	}

	private static void task2() {
		calls++;
	}

	private static void task3() {
		calls++;
	}

}
