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

    public String toDotString(String graphName) {
        StringBuilder dotString = new StringBuilder();
        dotString.append("digraph ");
        dotString.append(graphName);
        dotString.append(" {\n");
        dotString.append(toDotString(root));
        dotString.append("}");
        return dotString.toString();
    }

    private String toDotString(Node node) {
        StringBuilder str = new StringBuilder();
        str.append('"');
        str.append(node.getValue());
        str.append('"');
        str.append(";\n");
        if (node.getLeft() != null) {
            str.append(toDotString(node, node.getLeft()));
            str.append(toDotString(node.getLeft()));
        }
        if (node.getRight() != null) {
            str.append(toDotString(node, node.getRight()));
            str.append(toDotString(node.getRight()));
        }
        return str.toString();
    }

    private String toDotString(Node from, Node to) {
        StringBuilder str = new StringBuilder();
        str.append('"');
        str.append(from.getValue());
        str.append('"');
        str.append(" -> ");
        str.append('"');
        str.append(to.getValue());
        str.append('"');
        str.append(";\n");
        return str.toString();
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
