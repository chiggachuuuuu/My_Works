import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class dHeapTester {
    @Test
    public void testMax() {
        dHeap heap1 = new dHeap();
        dHeap heap2 = new dHeap(15);
        heap2.add(43);
        heap2.add(12);
        heap2.add(37);
        heap2.add(44);
        heap2.add(83);
        assertEquals(5, heap2.size());
        assertEquals(83, heap2.element());
        heap2.add(13);
        heap2.add(68);
        heap2.add(59);
        heap2.add(94);
        heap2.add(71);
        heap2.add(99);
        heap2.add(3);
        assertEquals(12, heap2.size());
        assertEquals(99, heap2.element());
        assertEquals(99, heap2.remove());
        assertEquals(94, heap2.remove());
        assertEquals(83, heap2.remove());
        assertEquals(71, heap2.remove());
        assertEquals(68, heap2.remove());
        assertEquals(59, heap2.remove());
        assertEquals(44, heap2.remove());
        assertEquals(43, heap2.remove());
        assertEquals(37, heap2.remove());
        assertEquals(13, heap2.remove());
        assertEquals(12, heap2.remove());
        assertEquals(3, heap2.element());
        assertEquals(3, heap2.remove());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap2.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap2.remove();
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            heap1.add(null);
        });
        heap1.add(30);
        heap1.add(46);
        heap1.add(31);
        heap1.add(49);
        heap1.add(73);
        heap1.add(93);
        heap1.add(94);
        heap1.add(53);
        heap1.add(87);
        heap1.add(91);
        assertEquals(94, heap1.element());
        assertEquals(10, heap1.size());
        heap1.add(95);
        heap1.add(99);
        assertEquals(99, heap1.element());
        assertEquals(12, heap1.size());
        assertEquals(99, heap1.remove());
        assertEquals(95, heap1.remove());
        assertEquals(94, heap1.remove());
        assertEquals(93, heap1.remove());
        assertEquals(91, heap1.remove());
        heap1.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap1.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap1.remove();
        });
        assertEquals(0, heap1.size());
        dHeap heap3 = new dHeap(3, 11, true);
        dHeap heap4 = new dHeap(4, 26, true);
        int[] insert = new int[]{12, 50, 34, 30, 25, 90, 53, 75, 92, 33, 2};
        for (int i = 0; i < insert.length; i++) {
            heap3.add(insert[i]);
            heap4.add(insert[i]);
        }
        assertEquals(92, heap3.element());
        assertEquals(heap3.element(), heap4.element());
        assertEquals(heap3.size(), heap4.size());
        assertEquals(11, heap4.size());
        assertEquals(92, heap4.remove());
        assertEquals(90, heap4.remove());
        assertEquals(75, heap4.remove());
        assertEquals(53, heap4.remove());
        assertEquals(50, heap4.remove());
        assertEquals(34, heap4.remove());
        heap4.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap4.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap4.remove();
        });
        assertEquals(0, heap4.size());
        Assertions.assertThrows(NullPointerException.class, () -> {
            heap3.add(null);
        });
        heap3.add(30);
        heap3.add(98);
        assertEquals(98, heap3.element());
        assertEquals(13, heap3.size());
        assertEquals(98, heap3.remove());
        assertEquals(92, heap3.remove());
        assertEquals(90, heap3.remove());
        assertEquals(75, heap3.remove());
        assertEquals(53, heap3.remove());
        assertEquals(50, heap3.remove());
        assertEquals(34, heap3.remove());
        assertEquals(33, heap3.remove());
        assertEquals(30, heap3.remove());
        assertEquals(30, heap3.remove());
        assertEquals(25, heap3.remove());
        assertEquals(12, heap3.remove());
        assertEquals(2, heap3.remove());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap3.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap3.remove();
        });
        assertEquals(0, heap3.size());
    }
    @Test
    public void testMin() {
        dHeap heap1 = new dHeap(2, 10, false);
        dHeap heap2 = new dHeap(2, 15, false);
        heap2.add(43);
        heap2.add(12);
        heap2.add(37);
        heap2.add(44);
        heap2.add(83);
        assertEquals(5, heap2.size());
        assertEquals(12, heap2.element());
        heap2.add(13);
        heap2.add(68);
        heap2.add(59);
        heap2.add(94);
        heap2.add(71);
        heap2.add(99);
        heap2.add(3);
        assertEquals(12, heap2.size());
        assertEquals(3, heap2.element());
        assertEquals(3, heap2.remove());
        assertEquals(12, heap2.remove());
        assertEquals(13, heap2.remove());
        assertEquals(37, heap2.remove());
        assertEquals(43, heap2.remove());
        assertEquals(44, heap2.remove());
        assertEquals(59, heap2.remove());
        assertEquals(68, heap2.remove());
        assertEquals(71, heap2.remove());
        assertEquals(83, heap2.remove());
        assertEquals(94, heap2.remove());
        assertEquals(99, heap2.element());
        assertEquals(99, heap2.remove());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap2.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap2.remove();
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            heap1.add(null);
        });
        heap1.add(30);
        heap1.add(46);
        heap1.add(31);
        heap1.add(49);
        heap1.add(73);
        heap1.add(93);
        heap1.add(94);
        heap1.add(53);
        heap1.add(87);
        heap1.add(91);
        assertEquals(30, heap1.element());
        assertEquals(10, heap1.size());
        heap1.add(95);
        heap1.add(99);
        assertEquals(30, heap1.element());
        assertEquals(12, heap1.size());
        assertEquals(30, heap1.remove());
        assertEquals(31, heap1.remove());
        assertEquals(46, heap1.remove());
        assertEquals(49, heap1.remove());
        assertEquals(53, heap1.remove());
        heap1.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap1.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap1.remove();
        });
        assertEquals(0, heap1.size());
        dHeap heap3 = new dHeap(3, 11, false);
        dHeap heap4 = new dHeap(4, 26, false);
        int[] insert = new int[]{12, 50, 34, 30, 25, 90, 53, 75, 92, 33, 2};
        for (int i = 0; i < insert.length; i++) {
            heap3.add(insert[i]);
            heap4.add(insert[i]);
        }
        assertEquals(2, heap3.element());
        assertEquals(heap3.element(), heap4.element());
        assertEquals(heap3.size(), heap4.size());
        assertEquals(11, heap4.size());
        assertEquals(2, heap4.remove());
        assertEquals(12, heap4.remove());
        assertEquals(25, heap4.remove());
        assertEquals(30, heap4.remove());
        assertEquals(33, heap4.remove());
        assertEquals(34, heap4.remove());
        heap4.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap4.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap4.remove();
        });
        assertEquals(0, heap4.size());
        Assertions.assertThrows(NullPointerException.class, () -> {
            heap3.add(null);
        });
        heap3.add(30);
        heap3.add(1);
        assertEquals(1, heap3.element());
        assertEquals(13, heap3.size());
        assertEquals(1, heap3.remove());
        assertEquals(2, heap3.remove());
        assertEquals(12, heap3.remove());
        assertEquals(25, heap3.remove());
        assertEquals(30, heap3.remove());
        assertEquals(30, heap3.remove());
        assertEquals(33, heap3.remove());
        assertEquals(34, heap3.remove());
        assertEquals(50, heap3.remove());
        assertEquals(53, heap3.remove());
        assertEquals(75, heap3.remove());
        assertEquals(90, heap3.remove());
        assertEquals(92, heap3.remove());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap3.element();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            heap3.remove();
        });
        assertEquals(0, heap3.size());
    }

}