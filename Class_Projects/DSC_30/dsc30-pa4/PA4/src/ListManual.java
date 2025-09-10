import java.util.*;

/**
 * Singly Linked List Practicing
 * @author Lin Tian
 * @since  A16844916
 */

public class ListManual {

// No style for this file. How poetic :D

    public static ArrayList<String>  listManipulations() {

        ArrayList<String> answers = new ArrayList<>(11);
        // Each index corresponds to the task number. Example is
        // at index 0.

        //0.
        answers.add("h = h.next;");
        //task 1.
        answers.add("h = r;");
        //task 2.
        answers.add("r = h;");
        //task 3.
        answers.add("t = h.next;");
        //task 4.
        answers.add("h.elem = t.elem;");
        //task 5.
        answers.add("h.elem = h.next.next.elem;");
        //task 6.
        answers.add("h.next.next.next = h;");
        //task 7.
        answers.add("h.next = h.next.next;");
        //task 8.
        answers.add("while (r.elem != 'M') {r = r.next;}");
        //task 9.
        answers.add("h = new Node('A', null); h.next = new Node('B', null); h.next.next = new Node('C', null); h.next.next.next = new Node('D', null);");
        //task 10.
        answers.add("h.next.next.next = new Node('D', null);");
        return answers;
    }
}
