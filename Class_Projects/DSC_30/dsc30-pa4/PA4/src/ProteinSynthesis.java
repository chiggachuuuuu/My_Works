/*
    Name: Lin Tian
    PID:  A16844916
 */

/**
 * Implementation of MyQueue in ProteinSynthesis
 *
 * @author Lin Tian
 * @since 10/31/2023
 */
class ProteinSynthesis {
    private static final int CODON_SIZE = 3;

    /**
     * Transcribe DNA into RNA (replacing each occurrence of ‘T’ with ‘U’)
     *
     * @param dna A string that represent the DNA needed to be transcribed
     * @return a MyQueue object that stores RNA
     * @throws IllegalArgumentException when the length of input string is not divisible by 3
     */
    public MyQueue<Character> transcribeDNA(String dna) throws IllegalArgumentException {
        if (dna.length() % CODON_SIZE != 0) {             // DNA sequence can't be translated later
            throw new IllegalArgumentException();
        }
        if (dna.isEmpty()) {                 // Return empty CharQueue when dna is empty
            MyQueue<Character> emptyRna = new MyQueue<Character>();
            return emptyRna;
        }
        MyQueue<Character> rna = new MyQueue<Character>(); // Create new CharQueue to store RNA
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'T') {
                rna.enqueue('U');             // Replace 'T' with 'U'
            } else {
                rna.enqueue(dna.charAt(i));
            }
        }
        return rna;
    }

    /**
     * Translates a given RNA to a protein
     *
     * @param rna A MyQueue object that stores RNA
     * @return a MyQueue object that stores the name of the protein
     */

    public MyQueue<Character> translateRNA(MyQueue<Character> rna) {

        int count = rna.size() / CODON_SIZE; // Divide sequence number by codon size:3
        MyQueue<Character> rnaTranslation = new MyQueue<Character>();
        if (rna.isEmpty()) {
            return rnaTranslation;     // Check if passed in rna is empty
        }
        String[] tempArray = new String[count]; // Make a temporary string array to store sequences
        for (int i = 0; i < count; i++) {
            String temp = "";                 // make a temporary string to store sequence of 3 RNA
            for (int j = 0; j < CODON_SIZE; j++) {
                temp = temp + rna.dequeue();    // Store each char into temporary string
            }
            tempArray[i] = temp;              // Store the RNA sequence into the sting array
        }
        int a = 0;
        while (!tempArray[a].equals("AUG")) {  // Find the start point
            a++;
            if (a == count) {                  // No start point found
                return rnaTranslation;
            }
        }
        for ( int b = a; b < count; b++) {   // Translate from the start point
            if (tempArray[b].equals("UAA") || tempArray[b].equals("UAG") ||
                    tempArray[b].equals("UGA")) {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b])); // Add Translation
                return rnaTranslation;               // End sequence keyword found
            } else {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b]));  // Add Translation
            }
        }
        return rnaTranslation;         // return translation as a CharQueue object
    }

}