import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.FullStackException;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    MyStack stack1;
    MyStack stack2;
    @BeforeEach
    public void setup(){
        stack1 = new MyStack();
        stack2 = new MyStack(4);
    }
    @Test
    public void isEmpty() {
        boolean expected1 = true;
        boolean expected2 = true;
        boolean actual1 = stack1.isEmpty();
        boolean actual2 = stack2.isEmpty();
        assertEquals(expected1, actual2);
        assertEquals(expected2, actual2);
    }

    @Test
    public void sizeAndCapacity() {
        assertEquals(0, stack1.size());
        assertEquals(10, stack1.capacity());
        assertEquals(0, stack2.size());
        assertEquals(4, stack2.capacity());
    }

    @Test
    public void test1() {
        /* push() * */
        stack1.push("water");
        stack2.push("ice-cream");
        stack2.push("coffee");
        assertEquals(1, stack1.size());
        assertEquals(2, stack2.size());
        assertEquals(false, stack2.isEmpty());
        /* multipush() * */
        String[] stringArray1 = {"chips", "tea"};
        stack1.multiPush(stringArray1);
        stack2.multiPush(stringArray1);
        assertEquals(4, stack2.size());
        assertEquals(3, stack1.size());
        assertEquals(10, stack1.capacity());
        /* pop() * */
        assertEquals("tea", stack1.pop());
        assertEquals(2, stack1.size());
        assertEquals("tea", stack2.pop());
        assertEquals(3, stack2.size());
        stack1.pop();
        /* peek() * */
        assertEquals("water", stack1.peek());
        assertEquals(1, stack1.size());
        stack1.clear();
        assertEquals(true, stack1.isEmpty());
        stack2.clear();
        assertEquals(0, stack2.size());
    }
    @Test
    public void test2() {
        /* setup stacks **/
        String[] stringArray2 = {"flower", "orange", "coffee", "apple", "lily", "onion"};
        String[] stringArray3 = {"bokchoy", "yogurt", "carrot"};
        stack1.multiPush(stringArray2);
        stack2.multiPush(stringArray3);
        assertEquals(6, stack1.size());
        assertEquals(3, stack2.size());
        assertEquals(false, stack1.isEmpty());
        /* multipop() **/
        stack1.multiPop(1);
        assertEquals("lily", stack1.peek());
        assertEquals(5, stack1.size());
        String[] outArray1 = stack1.multiPop(3);
        assertEquals(3, outArray1.length);
        assertEquals("lily", outArray1[0]);
        assertEquals("coffee", outArray1[2]);
        assertEquals("orange", stack1.peek());
        assertEquals(2, stack1.size());
        String[] outArray2 = stack2.multiPop(4);
        assertEquals(0, stack2.size());
        assertEquals("carrot", outArray2[0]);
        assertEquals("bokchoy", outArray2[2]);
        /* clear() **/
        stack1.clear();
        assertEquals(0, stack1.size());
        stack2.clear();
        assertEquals(true, stack2.isEmpty());
    }
    @Test
    public void exceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyStack stack3 = new MyStack(0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyStack stack3 = new MyStack(-1);
        });
        MyStack stack3 = new MyStack(2);
        Assertions.assertThrows(EmptyStackException.class, () -> {
            stack3.peek();
        });
        Assertions.assertThrows(EmptyStackException.class, () -> {
            stack3.pop();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stack3.push(null);
        });
        stack3.push("haha");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stack3.multiPush(null);
        });
        String[] inputArray = { "hehe", "hihi" };
        Assertions.assertThrows(FullStackException.class, () -> {
            stack3.multiPush(inputArray);
        });
        Assertions.assertThrows(FullStackException.class, () -> {
            stack3.push("hihi");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stack3.multiPop(0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stack3.multiPop(-1);
        });
        stack3.pop();
        assertEquals(1, stack3.size());
        assertEquals("haha", stack3.pop());
        assertEquals(0, stack3.size());
    }
}