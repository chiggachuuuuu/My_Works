import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {
    @Test
    public void test1() {
        BSTree tree1 = new BSTree();
        assertEquals(null, tree1.getRoot());
        assertEquals(0, tree1.getSize());
        assertEquals(-1, tree1.findHeight());
        tree1.insert(38);
        assertEquals(0, tree1.findHeight());
        tree1.insert(18);
        tree1.insert(8);
        assertEquals(2, tree1.findHeight());
        tree1.insert(2);
        tree1.insert(45);
        assertEquals(3, tree1.findHeight());
        assertTrue(tree1.insert(77));
        tree1.insert(98);
        tree1.insert(20);
        assertEquals(38, tree1.getRoot().getKey());
        assertEquals(8, tree1.getSize());
        assertFalse(tree1.insert(77));
        assertFalse(tree1.findKey(19));
        assertTrue(tree1.findKey(2));
        assertTrue(tree1.findKey(98));
        tree1.insertData(45, "DSC");
        tree1.insertData(45, "tiresome");
        tree1.insertData(77, 30);
        assertEquals("tiresome", tree1.findDataList(45).get(1));
        assertEquals("DSC", tree1.findDataList(45).get(0));
        assertEquals(30, tree1.findDataList(77).get(0));
        Iterator<Integer> iter1 = tree1.iterator();
        assertTrue(iter1.hasNext());
        assertEquals(2, iter1.next());
        assertEquals(8, iter1.next());
        assertEquals(18, iter1.next());
        assertEquals(20, iter1.next());
        assertEquals(38, iter1.next());
        assertEquals(45, iter1.next());
        assertEquals(77, iter1.next());
        assertEquals(98, iter1.next());
        assertFalse(iter1.hasNext());
    }
    @Test
    public void test2() {
        BSTree tree2 = new BSTree();
        assertEquals(null, tree2.getRoot());
        assertEquals(0, tree2.getSize());
        assertEquals(-1, tree2.findHeight());
        tree2.insert(11.11);
        assertEquals(0, tree2.findHeight());
        tree2.insert(10.91);
        tree2.insert(12.03);
        assertEquals(1, tree2.findHeight());
        tree2.insert(2.19);
        tree2.insert(10.97);
        assertEquals(2, tree2.findHeight());
        assertTrue(tree2.insert(12.04));
        tree2.insert(12.02);
        assertEquals(11.11, tree2.getRoot().getKey());
        assertEquals(7, tree2.getSize());
        assertFalse(tree2.insert(12.02));
        assertFalse(tree2.findKey(19.08));
        assertTrue(tree2.findKey(2.19));
        assertTrue(tree2.findKey(12.04));
        tree2.insertData(12.03, "DSC");
        tree2.insertData(10.97, "30");
        tree2.insertData(2.19, 999);
        assertEquals(999, tree2.findDataList(2.19).get(0));
        assertEquals("DSC", tree2.findDataList(12.03).get(0));
        assertEquals("30", tree2.findDataList(10.97).get(0));
        Iterator<Double> iter2 = tree2.iterator();
        assertTrue(iter2.hasNext());
        assertEquals(2.19, iter2.next());
        assertEquals(10.91, iter2.next());
        assertEquals(10.97, iter2.next());
        assertEquals(11.11, iter2.next());
        assertEquals(12.02, iter2.next());
        assertEquals(12.03, iter2.next());
        assertEquals(12.04, iter2.next());
        assertFalse(iter2.hasNext());

    }
    @Test
    public void test3() {
        BSTree tree3 = new BSTree();
        assertEquals(null, tree3.getRoot());
        assertEquals(0, tree3.getSize());
        assertEquals(-1, tree3.findHeight());
        tree3.insert("Lin");
        assertEquals(0, tree3.findHeight());
        tree3.insert("Tian");
        tree3.insert("DSC");
        assertEquals(1, tree3.findHeight());
        tree3.insert("Painful");
        tree3.insert("Marina");
        assertEquals(3, tree3.findHeight());
        assertTrue(tree3.insert("Awesome"));
        tree3.insert("30");
        assertEquals("Lin", tree3.getRoot().getKey());
        assertEquals(7, tree3.getSize());
        assertFalse(tree3.insert("Painful"));
        assertFalse(tree3.findKey("Sucks"));
        assertTrue(tree3.findKey("DSC"));
        assertTrue(tree3.findKey("Awesome"));
        assertTrue(tree3.findKey("Marina"));
        tree3.insertData("DSC", 10);
        tree3.insertData("DSC", 20);
        tree3.insertData("Marina", "all good");
        assertEquals(10, tree3.findDataList("DSC").get(0));
        assertEquals("all good", tree3.findDataList("Marina").get(0));
        assertEquals(20, tree3.findDataList("DSC").get(1));
        Iterator<String> iter3 = tree3.iterator();
        assertTrue(iter3.hasNext());
        assertEquals("30", iter3.next());
        assertEquals("Awesome", iter3.next());
        assertEquals("DSC", iter3.next());
        assertEquals("Lin", iter3.next());
        assertEquals("Marina", iter3.next());
        assertEquals("Painful", iter3.next());
        assertEquals("Tian", iter3.next());
        assertFalse(iter3.hasNext());
    }
    @Test
    public void assertionTest() {
        BSTree tree4 = new BSTree();
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree4.insert(null);
        });
        tree4.insert(3);
        tree4.insert(2);
        tree4.insert(4);
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree4.findKey(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tree4.insertData(5, "haha");
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree4.insertData(4, null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree4.insertData(null, "haha");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tree4.findDataList(1);
        });
        Iterator<Iterator> iter4 = tree4.iterator();
        iter4.next();
        iter4.next();
        iter4.next();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iter4.next();
        });
    }
}