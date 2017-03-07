package ch.hslu.ad.sw02.ex02;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedListTest {

    private DoubleLinkedList<String> list = null;

    private Element<String> java = null;
    private Element<String> rust = null;
    private Element<String> python = null;
    private Element<String> go = null;

    @Test
    public void testForOneElement() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        Element<String> java = new Element<String>("Java");
        list.add(java);
        Assert.assertEquals(1, list.getSize());
        Assert.assertEquals(java, list.getFirstElement());
    }

    @Test
    public void testForMultipleElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        Element<String> java = new Element<String>("Java");
        list.add(java);
        Assert.assertEquals(1, list.getSize());
        Assert.assertEquals(java, list.getFirstElement());

        Element<String> python = new Element<String>("Python");
        list.add(python);
        Assert.assertEquals(2, list.getSize());
        Assert.assertEquals(python, list.getFirstElement());
        Assert.assertEquals(java, list.getFirstElement().getNext());
        Assert.assertEquals(python, list.getFirstElement().getNext().getPrevious());
    }

    @Test
    public void testContains() {
        Element<String> vb = new Element<>("Visual Basic");

        Assert.assertTrue(list.contains(java));
        Assert.assertTrue(list.contains(rust));
        Assert.assertTrue(list.contains(python));
        Assert.assertTrue(list.contains(go));

        Assert.assertFalse(list.contains(vb));
    }

    /**
     * sets up a list as follows:
     * 
     * start -> ["Go"] -> ["Python"] -> ["Rust"] -> ["Java"]
     */
    @Before
    public void setupFourElementList() {
        list = new DoubleLinkedList<>();

        java = new Element<>("Java");
        rust = new Element<>("Rust");
        python = new Element<>("Python");
        go = new Element<>("Go");

        list.add(java);
        list.add(rust);
        list.add(python);
        list.add(go);
    }

    @Test
    public void testPop() {
        Element<String> e = list.pop();
        Assert.assertEquals(3, list.getSize());
        Assert.assertEquals(e, go);
        Assert.assertFalse(list.contains(go));

        e = list.pop();
        Assert.assertEquals(2, list.getSize());
        Assert.assertEquals(e, python);
        Assert.assertFalse(list.contains(python));

        Assert.assertTrue(list.contains(java));
        Assert.assertTrue(list.contains(rust));

        e = list.pop();
        Assert.assertEquals(1, list.getSize());
        Assert.assertEquals(e, rust);

        e = list.pop();
        Assert.assertEquals(0, list.getSize());
        Assert.assertEquals(e, java);

        e = list.pop();
        Assert.assertNull(e);
    }

    @Test
    public void testRemoveFirstElement() {
        list.removeElement(go);
        Assert.assertEquals(3, list.getSize());
        Assert.assertEquals(python, list.getFirstElement());

        list.removeElement(python);
        Assert.assertEquals(2, list.getSize());
        Assert.assertEquals(rust, list.getFirstElement());
    }

    @Test
    public void testRemoveLastElement() {
        list.removeElement(java);
        Assert.assertEquals(3, list.getSize());
        Assert.assertEquals(go, list.getFirstElement());

        list.removeElement(rust);
        Assert.assertEquals(2, list.getSize());
        Assert.assertEquals(go, list.getFirstElement());
    }

    @Test
    public void testRemoveMiddleElement() {
        list.removeElement(rust);
        Assert.assertEquals(3, list.getSize());
        Assert.assertEquals(go, list.getFirstElement());

        list.removeElement(python);
        Assert.assertEquals(2, list.getSize());
        Assert.assertEquals(go, list.getFirstElement());
    }
    
    @Test
    public void testLinkedListIterator() {
        Iterator<String> iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(go.getData(), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(python.getData(), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(rust.getData(), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(java.getData(), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
