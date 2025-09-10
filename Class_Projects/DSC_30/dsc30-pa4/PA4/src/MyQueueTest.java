import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    MyQueue<String> queue1;
    MyQueue<Integer> queue2;
    @BeforeEach
    public void setup() {
        queue1 = new MyQueue();
        queue2 = new MyQueue();
    }
    @Test
    public void test1() {
        assertEquals(true, queue1.isEmpty());
        assertEquals(0, queue2.size());
        queue1.enqueue("A");
        queue1.enqueue("B");
        queue1.enqueue("C");
        assertEquals(false, queue1.isEmpty());
        assertEquals(3, queue1.size());
        assertEquals("A", queue1.dequeue());
        assertEquals("B", queue1.peek());
        queue1.dequeue();
        assertEquals(1, queue1.size());
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.enqueue(4);
        queue2.enqueue(5);
        queue2.enqueue(6);
        queue2.enqueue(7);
        queue2.enqueue(8);
        assertEquals(8, queue2.size());
        assertEquals(1, queue2.peek());
        queue2.enqueue(9);
        assertEquals(1, queue2.peek());
        queue2.enqueue(10);
        assertEquals(10, queue2.size());
        queue2.enqueue(11);
        queue2.enqueue(12);
        queue2.enqueue(23);
        queue2.enqueue(24);
        queue2.enqueue(25);
        queue2.enqueue(26);
        assertEquals(16, queue2.size());
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        assertEquals(5, queue2.dequeue());
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
        MyQueue<String> queue3 = new MyQueue();
        queue1.enqueue("DSC30");
        queue1.enqueue("is");
        queue1.enqueue("worst");
        queue3.enqueue("Tian");
        queue3.enqueue("is");
        queue3.enqueue("the");
        queue3.enqueue("best");
        assertEquals(4, queue3.size());
        queue3.dequeue();
        queue1.dequeue();
        assertEquals(queue1.dequeue(), queue3.peek());
        queue3.clear();
        queue3.enqueue("DSC");
        queue3.enqueue("30");
        queue3.enqueue("is");
        queue3.enqueue("pain");
        assertEquals("DSC", queue3.dequeue());
        assertEquals("30", queue3.dequeue());
        assertEquals("is", queue3.dequeue());
        assertEquals("pain", queue3.peek());
        queue3.dequeue();
        queue1.clear();
        assertEquals(queue3.size(),queue1.size());
        assertEquals(queue3.isEmpty(), queue1.isEmpty());
    }
    @Test
    public void exceptionTest() {
        MyQueue<Character> queue5 = new MyQueue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            queue5.enqueue(null);
        });
        assertEquals(null, queue5.peek());
        assertEquals(null, queue5.dequeue());
    }
}