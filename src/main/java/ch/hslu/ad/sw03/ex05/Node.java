package ch.hslu.ad.sw03.ex05;

public class Node implements Comparable<Node> {

	private final char value;

	private Node left;
	private Node right;

	public Node(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Node other) {
		return value - other.value;
	}

}
