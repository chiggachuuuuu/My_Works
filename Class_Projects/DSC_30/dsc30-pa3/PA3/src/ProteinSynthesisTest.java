import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProteinSynthesisTest {
    @Test
    public void test1() {
        String dna1 = "ATG";
        CharQueue rna1 = new ProteinSynthesis().transcribeDNA(dna1);
        CharQueue protein1 = new ProteinSynthesis().translateRNA(rna1);
        assertEquals('M', protein1.peek());
        String dna2 = "AUGATGATG";
        CharQueue rna2 = new ProteinSynthesis().transcribeDNA(dna2);
        assertEquals(9, rna2.size());
        CharQueue protein2 = new ProteinSynthesis().translateRNA(rna2);
        assertEquals('M', protein2.dequeue());
        assertEquals('M', protein2.dequeue());
        assertEquals('M', protein2.dequeue());
        String dna3 = "GTCATGGAGCTA";
        CharQueue rna3 = new ProteinSynthesis().transcribeDNA(dna3);
        assertEquals(12, rna3.size());
        CharQueue protein3 = new ProteinSynthesis().translateRNA(rna3);
        assertEquals('M', protein3.dequeue());
        assertEquals('E', protein3.dequeue());
        assertEquals('L', protein3.dequeue());
        String dna4 = "ATGGTCCGCAGCGCATAGTAA";
        CharQueue rna4 = new ProteinSynthesis().transcribeDNA(dna4);
        CharQueue protein4 = new ProteinSynthesis().translateRNA(rna4);
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
            CharQueue rna = new ProteinSynthesis().transcribeDNA(dna);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String dna = "ACGD";
            CharQueue rna = new ProteinSynthesis().transcribeDNA(dna);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String dna = "ACUGCBFT";
            CharQueue rna = new ProteinSynthesis().transcribeDNA(dna);
        });
    }
    @Test
    public void Test2() {
        String dna5 = "";
        CharQueue rna5 = new ProteinSynthesis().transcribeDNA(dna5);
        CharQueue protein5 = new ProteinSynthesis().translateRNA(rna5);
        assertEquals(0, rna5.size());
        assertTrue(rna5.isEmpty());
        assertEquals(0, protein5.size());
        assertTrue(protein5.isEmpty());
    }
    @Test
    public void Test3() {
        String dna6 = "CGGGGGATCTCA";
        CharQueue rna6 = new ProteinSynthesis().transcribeDNA(dna6);
        assertEquals(12, rna6.size());
        CharQueue protein6 = new ProteinSynthesis().translateRNA(rna6);
        assertEquals(0, protein6.size());
        assertTrue(protein6.isEmpty());
    }
}