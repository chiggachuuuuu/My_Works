import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTestEC {
    public static void main(String[] args) {
        BSTree tree1 = new BSTree();
        BSTree tree2 = new BSTree();
        tree1.insert(5);
        tree1.insert(1);
        tree1.insert(10);
        tree1.insert(0);
        tree1.insert(4);
        tree1.insert(7);
        tree1.insert(9);
        tree2.insert(10);
        tree2.insert(7);
        tree2.insert(20);
        tree2.insert(4);
        tree2.insert(9);
        Iterator iter1 = tree1.iterator();
        Iterator iter2 = tree2.iterator();
        ArrayList<Integer> output;
        output = tree1.intersection(iter1, iter2);
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }
}