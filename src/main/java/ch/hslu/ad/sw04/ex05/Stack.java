package ch.hslu.ad.sw04.ex05;

public final class Stack {

	private final Integer stack[];

	private int top = 0; // points to the top free(!) element

	public Stack(int size) {
		stack = new Integer[size];
	}

	public void push(Integer element) {
		if (top < stack.length) {
			stack[top] = element;
			top++;
		} else {
			throw new IllegalStateException("Sorry, the stack is full.");
		}
	}

	public Integer pop() {
		if (top > 0) {
			Integer element = stack[top - 1];
			stack[top - 1] = null;
			top--;
			return element;
		} else {
			throw new IllegalStateException("Sorry, the stack is empty.");
		}
	}

	public int getSize() {
		return top;
	}
}
