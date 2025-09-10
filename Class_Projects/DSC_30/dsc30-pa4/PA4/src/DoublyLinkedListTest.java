import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
        DoublyLinkedList<Integer> list1;
        DoublyLinkedList<Character> list2;
        DoublyLinkedList<String> list3;
        @BeforeEach
        public void setup() {
            list1 = new DoublyLinkedList<>();
            list2 = new DoublyLinkedList<>();
            list3 = new DoublyLinkedList<>();
        }
        @Test
        public void test1() {
            assertTrue(list2.add('T'));
            list2.add('A');
            list2.add('N');
            list2.add(0, 'I');
            assertFalse(list2.contains('L'));
            assertEquals('N', list2.get(3));
            assertEquals("[(head) -> T -> I -> A -> N -> (tail)]", list2.toString());
            assertEquals('A', list2.remove(2));
            assertEquals(3, list2.size());
            assertEquals('T', list2.set(0, 'L'));
            assertFalse(list2.isEmpty());
            assertEquals("[(head) -> L -> I -> N -> (tail)]", list2.toString());
            list2.clear();
            assertTrue(list2.isEmpty());
        }

        @Test
        public void test2() {
            assertTrue(list1.add(1));
            assertTrue(list1.add(3));
            list1.add(4);
            list1.add(5);
            list1.add(4,6);
            list1.add(0, 2);
            assertTrue(list1.contains(5));
            assertEquals(6, list1.get(5));
            assertEquals(2, list1.get(1));
            assertEquals(1, list1.remove(0));
            assertEquals(6, list1.set(4, 1));
            assertFalse(list1.isEmpty());
            assertEquals("[(head) -> 2 -> 3 -> 4 -> 5 -> 1 -> (tail)]", list1.toString());
            list1.clear();
            assertEquals(0, list1.size());
        }

        @Test
        public void test3() {
            assertTrue(list3.add("DSC"));
            assertTrue(list3.add("30"));
            list3.add("Sucks");
            list3.add(3,"Great!");
            list3.add(2, "Marina");
            assertFalse(list3.contains("20"));
            assertEquals("Sucks", list3.get(2));
            assertEquals("Marina", list3.get(4));
            assertEquals("Sucks", list3.remove(2));
            assertEquals("30", list3.set(1, "20"));
            assertEquals(4, list3.size());
            assertEquals("[(head) -> DSC -> 20 -> Marina -> Great! -> (tail)]", list3.toString());
            list3.clear();
            assertTrue(list3.isEmpty());
        }
        @Test
        public void assertionTest() {
            Assertions.assertThrows(NullPointerException.class, () -> {
                list1.add(null);
            });
            list1.add(2);
            list1.add(4);
            Assertions.assertThrows(NullPointerException.class, () -> {
                list1.add(2,null);
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list1.add(3,6);
            });
            list2.add('L');
            list2.add('N');
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list2.add(-1,'I');
            });
            list2.add(0, 'I');
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list2.get(-3);
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list2.remove(3);
            });
            list3.add("DSC30");
            list3.add("Sucks");
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list3.add(3, "haha");
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list3.get(2);
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list3.remove(-2);
            });
            Assertions.assertThrows(NullPointerException.class, () -> {
                list3.set(1, null);
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list3.set(2, "Great");
            });
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                list3.set(-1, "Great");
            });
            list3.set(1, "Great");
        }
        @Test
        public void Test4() {
            list1.add(1);//
            list1.add(2);
            list1.add(3);//
            list1.add(4);
            list1.add(5);//
            list1.add(6);
            list1.add(7);//
            list1.add(8);
            list1.add(9);//
            list1.removeMultipleOf(2);
            assertEquals(2,list1.get(0));
            assertEquals(4,list1.get(1));
            assertEquals(6,list1.get(2));
            assertEquals(8,list1.get(3));
        }
        @Test
        public void Test5() {
            DoublyLinkedList<Integer> list4 = new DoublyLinkedList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            list1.add(4);//
            list1.add(5);
            list1.add(6);
            list1.add(7);
            list1.add(8);
            list1.add(9);
            list4.add(9);
            list4.add(8);
            list4.add(7);
            list4.add(6);//
            list4.add(5);
            list4.add(4);
            list4.add(3);
            list4.add(2);
            list4.add(1);
            list1.swapSegment(list4, 3);
            assertEquals(9,list1.get(0));
            assertEquals(8,list1.get(1));
            assertEquals(7,list1.get(2));
            assertEquals(6,list1.get(3));
            assertEquals(5,list1.get(4));
            assertEquals(6,list1.get(5));
            assertEquals(7,list1.get(6));
            assertEquals(8,list1.get(7));
            assertEquals(9,list1.get(8));
            assertEquals(1,list4.get(0));
            assertEquals(2,list4.get(1));
            assertEquals(3,list4.get(2));
            assertEquals(4,list4.get(3));
            assertEquals(5,list4.get(4));
            assertEquals(4,list4.get(5));
            assertEquals(3,list4.get(6));
            assertEquals(2,list4.get(7));
            assertEquals(1,list4.get(8));
        }
}