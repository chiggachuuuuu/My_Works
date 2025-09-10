/*
 * Name: Lin Tian
 * PID: A16844916
 */

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Implementation of SpellChecker with MyHashTable and MyBloomFilter
 *
 * @author Lin Tian
 * @since 12/08/2023
 */

public class SpellChecker {

    public KeyedSet dictWords;
    private static final char[] ALPHABETS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'};   // the alphabets table

    /**
     * Read the dictionary and store it into HashTable or BloomFilter.
     *
     * @param reader the Reader object that contains the dictionary
     * @param useHashTable the boolean value that indicates using HashTable or not
     */

    public void readDictionary(Reader reader, boolean useHashTable) {
        Scanner scanner = new Scanner(reader);  // store dictionary in scanner object
        if (useHashTable) {
            this.dictWords = new MyHashTable();
        } else {
            this.dictWords = new MyBloomFilter();
        }
        while (scanner.hasNextLine()) {
            // store dictionary in dictWords interface
            this.dictWords.insert(scanner.nextLine().toLowerCase());
        }
    }

    /**
     * Check the wrong letter possibilities of the given word by changing one letter
     * in the given string that find matches in the dictionary
     *
     * @param word the given word
     * @return A list of Strings that contains possible corrections
     */
    private LinkedList<String> checkWrongLetter(String word) {
        LinkedList<String> corrections = new LinkedList<>();
        char[] wordChar= word.toCharArray();  // Split the word string
        for (int i = 0; i < wordChar.length; i++) {
            char start = wordChar[i];
            for (int j = 0; j < ALPHABETS.length; j++) {
                // Change to every alphabet letter
                wordChar[i] = ALPHABETS[j];
                String possibleCorrection = String.valueOf(wordChar);
                if (this.dictWords.lookup(possibleCorrection)) {
                    // Find possible corrections and add
                    corrections.add(possibleCorrection);
                }
            }
            // Change back
            wordChar[i] = start;
        }
        return corrections;
    }

    /**
     * Check the inserted letter possibilities of the given word by adding one letter
     * in the given string that find matches in the dictionary
     *
     * @param word the given word
     * @return A list of Strings that contains possible corrections
     */
    private LinkedList<String> checkInsertedLetter(String word) {
        LinkedList<String> corrections = new LinkedList<>();
        for (int i = 0; i < word.length() + 1; i++) {
            String possibleCorrection;
            for (int j = 0; j < ALPHABETS.length; j++) {
                // Insert all possible alphabet letters
                possibleCorrection = word.substring(0, i) + ALPHABETS[j] + word.substring(i);
                if (this.dictWords.lookup(possibleCorrection)) {
                    // Find the possible corrections and add
                    corrections.add(possibleCorrection);
                }
            }
        }
        return corrections;
    }

    /**
     * Check the inserted letter possibilities of the given word by deleting one letter
     * in the given string that find matches in the dictionary
     *
     * @param word the given word
     * @return A list of Strings that contains possible corrections
     */
    private LinkedList<String> checkDeleted(String word) {
        LinkedList<String> corrections = new LinkedList<>();
        String possibleCorrection;
        for (int i = 0; i < word.length(); i++) {
            // Delete on letter at each position
            possibleCorrection = word.substring(0,i) + word.substring(i + 1);
            if (this.dictWords.lookup(possibleCorrection)) {
                // Find possible corrections and add
                corrections.add(possibleCorrection);
            }
        }
        return corrections;
    }

    /**
     * Check the inserted letter possibilities of the given word by swapping two adjacent letters
     * in the given string that find matches in the dictionary
     *
     * @param word the given word
     * @return A list of Strings that contains possible corrections
     */
    private LinkedList<String> checkTransposedLetter(String word) {
        LinkedList<String> corrections = new LinkedList<>();
        // Split te given word string
        char[] wordChar= word.toCharArray();
        String possibleCorrection;
        for (int i = 0; i < wordChar.length - 1; i++) {
            // Swap two adjacent letters
            char temp = wordChar[i];
            wordChar[i] = wordChar[i + 1];
            wordChar[i + 1] = temp;
            possibleCorrection = String.valueOf(wordChar);
            if (this.dictWords.lookup(possibleCorrection)) {
                // Find possible corrections and add
                corrections.add(possibleCorrection);
            }
            // Swap back to the original form
            wordChar[i + 1] = wordChar[i];
            wordChar[i] = temp;
        }
        return corrections;
    }

    /**
     * Check the inserted letter possibilities of the given word by inserting a space
     * in the given string that find matches in the dictionary
     *
     * @param word the given word
     * @return A list of Strings that contains possible corrections
     */
    private LinkedList<String> checkInsertSpace(String word) {
        LinkedList<String> corrections = new LinkedList<>();
        String possibleCorrection;
        String firstHalf;
        String secondHalf;
        for (int i = 0; i < word.length() - 1; i++) {
            // Separate the string by a space
            firstHalf = word.substring(0, i + 1);
            secondHalf = word.substring(i + 1);
            if (this.dictWords.lookup(firstHalf) && this.dictWords.lookup(secondHalf)) {
                // Only when both halves found in dictionary
                possibleCorrection = firstHalf + " " + secondHalf;
                // Find possible corrections and add
                corrections.add(possibleCorrection);
            }
        }
        return corrections;
    }

    /**
     * Check the given word to find matches in the dictionary
     *
     * @param word the given word string
     * @return an array of possible correct words
     */
    private String[] checkWord(String word) {
        LinkedList<String> corrections;
        LinkedList<String> insertedLetter;
        LinkedList<String> deleted;
        LinkedList<String> transposedLetter;
        LinkedList<String> insertSpace;
        String[] output;
        if (this.dictWords.lookup(word)) {
            // When the existence of the given word found in the dictionary
            output = new String[]{"ok"};
        } else {
            // Add all wrong letter corrections
            corrections = this.checkWrongLetter(word);
            insertedLetter = this.checkInsertedLetter(word);
            deleted = this.checkDeleted(word);
            transposedLetter = this.checkTransposedLetter(word);
            insertSpace = this.checkInsertSpace(word);
            // Find all inserted letter corrections
            for (String s : insertedLetter) {
                if (!corrections.contains(s)) {
                    // Add only when it is not a duplicate
                    corrections.add(s);
                }
            }
            // Find all deleted letter corrections
            for (String i : deleted) {
                if (!corrections.contains(i)) {
                    // Add only when it is not a duplicate
                    corrections.add(i);
                }
            }
            // Find all transposed letter corrections
            for (String j : transposedLetter) {
                if (!corrections.contains(j)) {
                    // Add only when it is not a duplicate
                    corrections.add(j);
                }
            }
            // FInd all insert space corrections
            for (String k : insertSpace) {
                if (!corrections.contains(k)) {
                    // Add only when it is not a duplicate
                    corrections.add(k);
                }
            }
            if (corrections.isEmpty()) {
                // return not found if there is no possible corrections
                output = new String[]{"not found"};
            } else {
                // return all possible corrections
                output = new String[corrections.size()];
                for (int i = 0; i < corrections.size(); i++) {
                    output[i] = corrections.get(i);
                }
            }
        }
        return output;
    }

    /**
     * Main method that processes and performs spellcheck on the given file.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // args[0]: 0 if we should use a MyHashTable and 1 for a MyBloomFilter
        // args[1]: path to dict file
        // args[2]: path to input file

        SpellChecker checker = new SpellChecker();

        File dictionary = new File(args[1]);
        try {
            Reader reader = new FileReader(dictionary);
            Boolean useHashTable = null;
            if (Integer.valueOf(args[0]) == 0) {
                useHashTable = true;
            }
            if (Integer.valueOf(args[0]) == 1) {
                useHashTable = false;
            }
            checker.readDictionary(reader, useHashTable);


        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + dictionary);
            System.exit(1);
        }

        File inputFile = new File(args[2]);
        try {
            Scanner input = new Scanner(inputFile); // Reads list of words
            while (input.hasNext()) {
                String word = input.nextLine();
                String[] output = checker.checkWord(word.toLowerCase());
                System.out.print(word.toLowerCase() + ": ");
                for (int i = 0; i < output.length; i++) {
                    if (i == output.length - 1) {
                        System.out.print(output[i]);
                    } else {
                        System.out.print(output[i] + ", ");
                    }
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + inputFile);
            System.exit(1);
        }
    }
}
