package ch.hslu.ad.sw03.ex05;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {

	@Test
	public void testRootElement() {
		Tree tree = new BinaryTree();

		Assert.assertFalse(tree.contains(new Node('a')));
		tree.add(new Node('a'));
		Assert.assertTrue(tree.contains(new Node('a')));
	}

	@Test
	public void testThreeElements() {
		Tree tree = new BinaryTree();

		Assert.assertFalse(tree.contains(new Node('a')));
		tree.add(new Node('a'));
		Assert.assertTrue(tree.contains(new Node('a')));

		Assert.assertFalse(tree.contains(new Node('b')));
		tree.add(new Node('b'));
		Assert.assertTrue(tree.contains(new Node('b')));

		Assert.assertFalse(tree.contains(new Node('c')));
		tree.add(new Node('c'));
		Assert.assertTrue(tree.contains(new Node('c')));
	}

	public void testLotsOfElements() {
		Tree tree = new BinaryTree();
		for (char c = 'a'; c <= 'z'; c++) {
			tree.add(new Node(c));
		}
		for (char c = 'a'; c <= 'z'; c++) {
			Assert.assertTrue(tree.contains(new Node(c)));
		}
		Assert.assertEquals('z' - 'a' + 1, tree.getElementList().size());
	}

	@Test
	public void testElementList() {
		Tree tree = new BinaryTree();

		tree.add(new Node('a'));
		tree.add(new Node('b'));
		tree.add(new Node('c'));

		List<Character> elementList = tree.getElementList();
		Assert.assertEquals(3, elementList.size());

		Assert.assertTrue(elementList.contains('a'));
		Assert.assertTrue(elementList.contains('b'));
		Assert.assertTrue(elementList.contains('c'));
	}
}
