package ch.hslu.ad.sw03.ex05;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree implements Tree {

	private Node root = null;

	@Override
	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			attach(node, root);
		}
	}

	@Override
	public boolean contains(Node node) {
		return contains(node, root);
	}

	@Override
	public List<Character> getElementList() {
		List<Character> elementList = new ArrayList<>();
		if (root != null) {
			fill(elementList, root);
		}
		return elementList;
	}

	public Node getRoot() {
		return root;
	}

	@Override
	public void remove(Node node) {
		// TODO implement
		throw new UnsupportedOperationException("sorry, removing hasn't been implemented yet");
	}

	private void attach(Node newNode, Node toNode) {
		if (newNode == null || toNode == null) {
			return;
		}
		if (newNode.compareTo(toNode) < 0) {
			if (toNode.getLeft() == null) {
				toNode.setLeft(newNode);
			} else {
				attach(newNode, toNode.getLeft());
			}
		} else {
			if (toNode.getRight() == null) {
				toNode.setRight(newNode);
			} else {
				attach(newNode, toNode.getRight());
			}
		}
	}

	private boolean contains(Node node, Node candidate) {
		if (node == null || candidate == null) {
			return false;
		}
		if (candidate.getValue() == node.getValue()) {
			return true;
		} else {
			if (node.compareTo(candidate) < 0) {
				if (candidate.getLeft() == null) {
					return false;
				} else {
					return contains(node, candidate.getLeft());
				}
			} else {
				if (candidate.getRight() == null) {
					return false;
				} else {
					return contains(node, candidate.getRight());
				}
			}
		}

	}

	private void fill(List<Character> elementList, Node node) {
		if (node == null) {
			return;
		}
		elementList.add(node.getValue());
		if (node.getLeft() != null) {
			fill(elementList, node.getLeft());
		} else if (node.getRight() != null) {
			fill(elementList, node.getRight());
		}
	}
}
