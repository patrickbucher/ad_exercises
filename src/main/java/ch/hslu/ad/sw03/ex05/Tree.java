package ch.hslu.ad.sw03.ex05;

import java.util.List;

public interface Tree {

	void add(Node node);

	boolean contains(Node node);

	List<Character> getElementList();

	void remove(Node node);
}