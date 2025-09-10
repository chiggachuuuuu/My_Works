/*
 * Name: Lin Tian
 * PID:  A16844916
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Lin Tian
 * @since  11/19/2023
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();
                for (int i = 0; i < cast.length; i++) {
                    populateHelper(movieTree, cast[i].toLowerCase(), movie);
                }
                for (int i = 0; i < studios.length; i++) {
                    populateHelper(studioTree, studios[i].toLowerCase(), movie);
                }
                for (int i = 0; i < cast.length; i++) {
                    populateHelper(ratingTree, cast[i].toLowerCase(), rating);
                }
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper function to populate BSTrees
     *
     * @param tree  - BST to be populated
     * @param key - String object to be populated as the key
     * @param data - String object to be populated as the value
     */
    private static void populateHelper(BSTree<String> tree, String key, String data ) {
        if (tree.findKey(key)) {
            if (! tree.findDataList(key).contains(data)) {
                tree.insertData(key, data);
            }
        } else {
            tree.insert(key);
            tree.insertData(key, data);
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> intersection = new LinkedList<>();
        LinkedList<String> individual = new LinkedList<>();
        if (searchTree.findKey(keys[0])) {
            intersection.addAll(searchTree.findDataList(keys[0]));
        }
        for (int i = 1; i < keys.length; i++) {
            if (searchTree.findKey(keys[i])) {
                intersection.retainAll(searchTree.findDataList(keys[i]));
            }
        }
        print(query, intersection);
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length > 1) {
            for (int i = 0; i < keys.length; i++) {
                if (searchTree.findKey(keys[i])) {
                    individual.addAll(searchTree.findDataList(keys[i]));
                    individual.removeAll(intersection);
                    for (int j = i - 1; j > -1; j--) {
                        if (searchTree.findKey(keys[j])) {
                            individual.removeAll(searchTree.findDataList(keys[j]));
                        }
                    }
                    if (! individual.isEmpty()) {
                        print(keys[i], individual);
                    }
                    individual.clear();
                } else {
                    print(keys[i], null);
                }
            }
        }
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments217
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += args[i];
            query += " ";
        }
        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        if (searchKind == 0) {
            searchMyQuery(movieTree, query);
        }
        if (searchKind == 1){
            searchMyQuery(studioTree, query);
        }
        if (searchKind == 2) {
            searchMyQuery(ratingTree, query);
        }
    }
}
