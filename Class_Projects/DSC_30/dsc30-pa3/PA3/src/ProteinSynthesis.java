/*
    Name: Lin Tian
    PID:  A16844916
 */

/**
 * ProteinSynthesis Implementation
 *
 * @author Lin Tian
 * @since 10/22/2023
 */
class ProteinSynthesis {
    private static final int CODON_SIZE = 3;

    /*
    * This method transcribe DNA into RNA
    * by replacing each occurrence of ‘T’ with ‘U’
    * and saves the result in a queue.
    * @param dna A string that represent the DNA needed to be transcribed.
    * @return a CharQueue object that represents RNA.
    * @throws IllegalArgumentException when the length of input string is not divisible by 3.
    **/
    public CharQueue transcribeDNA(String dna) throws IllegalArgumentException {
        if (dna.length() % CODON_SIZE != 0) {             // DNA sequence can't be translated later
            throw new IllegalArgumentException();
        }
        if (dna.length() == 0) {                 // Return empty CharQueue when dna is empty
            CharQueue emptyRna = new CharQueue();
            return emptyRna;
        }
        CharQueue rna = new CharQueue(dna.length()); // Create new CharQueue to store RNA
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'T') {
                rna.enqueue('U');             // Replace 'T' with 'U'
            } else {
                rna.enqueue(dna.charAt(i));
            }
        }
        return rna;
    }

    /*
    * This method translates a given RNA to a protein:
    * translation begin with “AUG”,
    * ends with “UAA”, “UAG”, “UGA”, or the end of the sequence.
    * @param rna A CharQueue object that represents RNA.
    * @return a CharQueue object that represents the name of the protein.
    **/
    public CharQueue translateRNA(CharQueue rna) {
        int count = rna.size() / CODON_SIZE; // Divide sequence number by codon size:3
        CharQueue rnaTranslation = new CharQueue();//Create a CharQueue object to store translation
        if (count == 0) {
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
            if (tempArray[b].equals("UAA")) {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b])); // Add Translation
                return rnaTranslation;               // End sequence keyword found
            } else if (tempArray[b].equals("UAG")) {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b])); // Add Translation
                return rnaTranslation;               // End sequence keyword found
            } else if (tempArray[b].equals("UGA")) {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b])); // Add Translation
                return rnaTranslation;               // End sequence keyword found
            } else {
                rnaTranslation.enqueue(CodonMap.getAminoAcid(tempArray[b]));  // Add Translation
            }
        }
        return rnaTranslation;         // return translation as a CharQueue object
    }
}