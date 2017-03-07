package ch.hslu.ad.sw02.ex03;

public final class Stack {
    private static final int STACK_SIZE = 10;

    private final String stack[] = new String[STACK_SIZE];

    private int top = 0; // points to the top free(!) element

    public void push(String element) {
        if (top < stack.length) {
            stack[top] = element;
            top++;
        } else {
            throw new IllegalStateException("Sorry, the stack is full.");
        }
    }

    public String pop() {
        if (top > 0) {
            String element = stack[top - 1];
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
