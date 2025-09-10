import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableTester {
    @Test
    public void test1() {
        MyHashTable table1 = new MyHashTable();
        MyHashTable table2 = new MyHashTable(10);
        MyHashTable table3 = new MyHashTable(5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyHashTable table4 = new MyHashTable(4);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyHashTable table5 = new MyHashTable(1);
        });
        //table 1
        table1.insert("Marina");
        table1.insert("DSC 30");
        table1.insert("is");
        table1.insert("hard");
        table1.insert("hard");
        assertFalse(table1.insert("hard"));
        assertTrue(table1.insert("Lin"));
        assertEquals(5, table1.size());
        assertEquals(20, table1.capacity());
        Assertions.assertThrows(NullPointerException.class, () -> {
            table1.insert(null);
        });
        table1.insert("haha");
        table1.insert("exams");
        table1.insert("are");
        table1.insert("painful");
        table1.insert("I don't want to do finals");
        table1.insert("Sadly");
        table1.insert("need a degree");
        table1.insert("so");
        table1.insert("gotta work");
        table1.insert("and cope with");
        table1.insert("finals");
        table1.insert("ahahahah");
        table1.insert("I");
        table1.insert("failed");
        table1.insert("Midterms");
        assertEquals(20, table1.capacity());
        assertEquals(20, table1.size());
        table1.insert("ziyao");
        table1.printTable();
        table1.insert("zhou");
        assertEquals(40, table1.capacity());
        assertEquals(22, table1.size());
        assertTrue(table1.delete("hard"));
        assertFalse(table1.delete("not there"));
        assertEquals(21, table1.size());
        assertEquals(40, table1.capacity());
        assertFalse(table1.lookup("hard"));
        assertTrue(table1.lookup("ziyao"));
        table1.printTable();
        assertEquals(Arrays.toString(table1.returnAll()), "[exams, I don't want to do finals, " +
                "ahahahah, Sadly, gotta work, is, finals, Marina, painful, haha, are, and cope " +
                "with, need a degree, Lin, Midterms, DSC 30, I, failed, zhou, so, ziyao]");
        System.out.println(table1.getStatsLog());
        //table 2
        table2.insert("J Cole");
        table2.insert("Chance the Rapper");
        table2.insert("Kendrick Lamar");
        table2.insert("Drake");
        table2.insert("Lil Tjay");
        table2.insert("Polo G");
        assertTrue(table2.insert("Pop Smoke"));
        table2.insert("Travis Scott");
        table2.insert("Kodak Black");
        table2.insert("Mac Miller");
        assertFalse(table2.insert("Lil Tjay"));
        assertTrue(table2.insert("lil Tjay"));
        assertEquals(11, table2.size());
        assertEquals(10, table2.capacity());
        table2.printTable();
        System.out.println(table2.getStatsLog());
        Assertions.assertThrows(NullPointerException.class, () -> {
            table2.delete(null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            table2.lookup(null);
        });
        assertTrue(table2.lookup("lil Tjay"));
        assertFalse(table2.delete("50 cent"));
        assertTrue(table2.insert("50 Cent"));
        assertEquals(20, table2.capacity());
        assertEquals(12, table2.size());
        assertTrue(table2.insert("Eminem"));
        assertTrue(table2.insert("Central Cee"));
        assertTrue(table2.insert("Lil baby"));
        assertTrue(table2.delete("lil Tjay"));
        assertTrue(table2.insert("Future"));
        assertTrue(table2.insert("Gunna"));
        assertTrue(table2.insert("Young Thug"));
        assertTrue(table2.insert("Offset"));
        assertTrue(table2.insert("Cardi B"));
        assertTrue(table2.insert("Logic"));
        assertEquals(table2.capacity(), table2.size());
        table2.printTable();
        table2.insert("Fivio Foreign");
        assertEquals(21, table2.size());
        assertEquals(20, table2.capacity());
        table2.insert("Gang Starr");
        assertEquals(22, table2.size());
        assertEquals(40, table2.capacity());
        System.out.println(table2.getStatsLog());
        assertEquals(Arrays.toString(table2.returnAll()), "[Polo G, Pop Smoke, 50 Cent, " +
                "Mac Miller, Travis Scott, Central Cee, J Cole, Lil Tjay, Logic, Fivio Foreign, " +
                "Young Thug, Drake, Gang Starr, Kendrick Lamar, Cardi B, Offset, Lil baby, " +
                "Chance the Rapper, Gunna, Eminem, Future, Kodak Black]");
        //table 3
        table3.insert("Rainbow Trout");
        table3.insert("Spottie");
        table3.insert("Lightening Trout");
        assertTrue(table3.insert("Largemouth Bass"));
        table3.insert("Smallmouth Bass");
        assertFalse(table3.insert("Spottie"));
        assertEquals(5, table3.size());
        assertEquals(5, table3.capacity());
        table3.printTable();
        assertTrue(table3.insert("Spotted Bay Bass"));
        table3.printTable();
        Assertions.assertThrows(NullPointerException.class, () -> {
            table3.insert(null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            table3.delete(null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            table3.lookup(null);
        });
        assertTrue(table3.delete("Spottie"));
        assertFalse(table3.lookup("Spottie"));
        assertTrue(table3.insert("Ling Cod"));
        assertEquals(5, table3.capacity());
        assertEquals(6, table3.size());
        assertTrue(table3.insert("Sculpin"));
        assertEquals(10, table3.capacity());
        assertEquals(7, table3.size());
        assertTrue(table3.insert("Rock Fish"));
        assertTrue(table3.insert("Peacock Bass"));
        assertTrue(table3.insert("Jack Smelt"));
        assertTrue(table3.insert("Rock Rass"));
        assertEquals(10, table3.capacity());
        assertEquals(11, table3.size());
        assertTrue(table3.insert("Steelhead Trout"));
        assertEquals(12, table3.size());
        assertEquals(20, table3.capacity());
        assertEquals(Arrays.toString(table3.returnAll()), "[Rock Fish, Lightening Trout, " +
                "Smallmouth Bass, Spotted Bay Bass, Peacock Bass, Rainbow Trout, " +
                "Largemouth Bass, Sculpin, Jack Smelt, Ling Cod, Rock Rass, Steelhead Trout]");
        table3.insert("Sea Trout");
        table3.insert("Lake Trout");
        table3.insert("Chinook Salmon");
        table3.insert("King Salmon");
        table3.insert("Stingray");
        table3.insert("Calico Bass");
        table3.insert("Sand Bass");
        table3.insert("Sheaphead");
        table3.insert("Black Croacker");
        assertTrue(table3.insert("Goby"));
        assertEquals(22, table3.size());
        assertEquals(40, table3.capacity());
        System.out.println(table3.getStatsLog());
    }
}