import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProteinSynthesisTest {
    @Test
    public void test1() {
        String dna1 = "ATG";
        MyQueue<Character> rna1 = new ProteinSynthesis().transcribeDNA(dna1);
        MyQueue<Character> protein1 = new ProteinSynthesis().translateRNA(rna1);
        assertEquals('M', protein1.peek());
        String dna2 = "AUGATGATG";
        MyQueue<Character> rna2 = new ProteinSynthesis().transcribeDNA(dna2);
        assertEquals(9, rna2.size());
        MyQueue<Character> protein2 = new ProteinSynthesis().translateRNA(rna2);
        assertEquals('M', protein2.dequeue());
        assertEquals('M', protein2.dequeue());
        assertEquals('M', protein2.dequeue());
        String dna3 = "GTCATGGAGCTA";
        MyQueue<Character> rna3 = new ProteinSynthesis().transcribeDNA(dna3);
        assertEquals(12, rna3.size());
        MyQueue<Character> protein3 = new ProteinSynthesis().translateRNA(rna3);
        assertEquals('M', protein3.dequeue());
        assertEquals('E', protein3.dequeue());
        assertEquals('L', protein3.dequeue());
        String dna4 = "ATGGTCCGCAGCGCATAGTAA";
        MyQueue<Character> rna4 = new ProteinSynthesis().transcribeDNA(dna4);
        MyQueue<Character> protein4 = new ProteinSynthesis().translateRNA(rna4);
        assertEquals('M', protein4.dequeue());
        assertEquals('V', protein4.dequeue());
        assertEquals('R', protein4.dequeue());
        assertEquals('S', protein4.dequeue());
        assertEquals('A', protein4.dequeue());
        assertEquals('*', protein4.dequeue());
        assertEquals(true, protein4.isEmpty());
    }
    @Test
    public void assertionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String dna = "A";
            MyQueue<Character> rna = new ProteinSynthesis().transcribeDNA(dna);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String dna = "ACGD";
            MyQueue<Character> rna = new ProteinSynthesis().transcribeDNA(dna);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String dna = "ACUGCBFT";
            MyQueue<Character> rna = new ProteinSynthesis().transcribeDNA(dna);
        });
    }
    @Test
    public void Test2() {
        String dna5 = "";
        MyQueue<Character> rna5 = new ProteinSynthesis().transcribeDNA(dna5);
        MyQueue<Character> protein5 = new ProteinSynthesis().translateRNA(rna5);
        assertEquals(0, rna5.size());
        assertTrue(rna5.isEmpty());
        assertEquals(0, protein5.size());
        assertTrue(protein5.isEmpty());
    }
    @Test
    public void Test3() {
        String dna6 = "CGGGGGATCTCA";
        MyQueue<Character> rna6 = new ProteinSynthesis().transcribeDNA(dna6);
        assertEquals(12, rna6.size());
        MyQueue<Character> protein6 = new ProteinSynthesis().translateRNA(rna6);
        assertEquals(0, protein6.size());
        assertTrue(protein6.isEmpty());
        String dna7 = "CGGGGGATCTCAATG";
        MyQueue<Character> rna7 = new ProteinSynthesis().transcribeDNA(dna7);
        assertEquals(15, rna7.size());
        MyQueue<Character> protein7 = new ProteinSynthesis().translateRNA(rna7);
        assertEquals(1, protein7.size());
        assertEquals('M', protein7.dequeue());

    }
}