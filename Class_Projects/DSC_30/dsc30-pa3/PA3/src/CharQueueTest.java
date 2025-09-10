import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CharQueueTest {
    CharQueue queue1;
    CharQueue queue2;
    @BeforeEach
    public void setup() {
        queue1 = new CharQueue();
        queue2 = new CharQueue(8);
    }
    @Test
    public void test1() {
        assertEquals(true, queue1.isEmpty());
        assertEquals(0, queue2.size());
        queue1.enqueue('A');
        queue1.enqueue('B');
        queue1.enqueue('C');
        assertEquals(false, queue1.isEmpty());
        assertEquals(3, queue1.size());
        assertEquals('A', queue1.dequeue());
        assertEquals('B', queue1.peek());
        queue1.dequeue();
        assertEquals(1, queue1.size());
        queue2.enqueue('D');
        queue2.enqueue('S');
        queue2.enqueue('C');
        queue2.enqueue('H');
        queue2.enqueue('A');
        queue2.enqueue('H');
        queue2.enqueue('A');
        queue2.enqueue('H');
        assertEquals(8, queue2.size());
        assertEquals('D', queue2.peek());
        queue2.enqueue('A');
        assertEquals('D', queue2.peek());
        queue2.enqueue('L');
        assertEquals(10, queue2.size());
        queue2.enqueue('O');
        queue2.enqueue('V');
        queue2.enqueue('E');
        queue2.enqueue('S');
        queue2.enqueue('M');
        queue2.enqueue('E');
        assertEquals(16, queue2.size());
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        assertEquals('A', queue2.dequeue());
        assertEquals(11, queue2.size());
        queue2.clear();
        assertEquals(0, queue2.size());
        assertEquals(true, queue2.isEmpty());
        queue1.clear();
        assertEquals(0, queue1.size());
        assertEquals(true, queue1.isEmpty());
    }
    @Test
    public void test2() {
        CharQueue queue3 = new CharQueue(2);
        queue1.enqueue('L');
        queue1.enqueue('I');
        queue1.enqueue('N');
        queue3.enqueue('T');
        queue3.enqueue('I');
        queue3.enqueue('A');
        queue3.enqueue('N');
        assertEquals(4, queue3.size());
        queue3.dequeue();
        queue1.dequeue();
        assertEquals(queue1.dequeue(), queue3.peek());
        queue3.clear();
        queue3.enqueue('S');
        queue3.enqueue('T');
        queue3.enqueue('I');
        queue3.enqueue('L');
        assertEquals('S', queue3.dequeue());
        assertEquals('T', queue3.dequeue());
        assertEquals('I', queue3.dequeue());
        assertEquals('L', queue3.peek());
        queue3.dequeue();
        queue1.clear();
        assertEquals(queue3.size(),queue1.size());
        assertEquals(queue3.isEmpty(), queue1.isEmpty());
    }
    @Test
    public void exceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue queue3 = new CharQueue(0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue queue4 = new CharQueue(-1);
        });
        CharQueue queue5 = new CharQueue(1);
        queue5.enqueue('W');
        assertEquals(1, queue5.size());
        assertEquals('W', queue5.peek());
        assertEquals('W', queue5.dequeue());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue5.dequeue();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue queue6 = new CharQueue(-88);
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue1.dequeue();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue2.peek();
        });
        CharQueue queue3 = new CharQueue(2);
        queue3.enqueue('T');
        queue3.enqueue('I');
        queue3.dequeue();
        queue3.dequeue();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue3.dequeue();
        });
        queue1.enqueue('L');
        queue1.enqueue('I');
        queue1.enqueue('N');
        queue1.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue1.peek();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue3.peek();
        });
        queue2.enqueue('O');
        queue2.enqueue('V');
        queue2.enqueue('E');
        queue2.enqueue('S');
        queue2.enqueue('M');
        queue2.enqueue('E');
        queue2.dequeue();
        queue2.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue2.peek();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue2.dequeue();
        });

    }

}