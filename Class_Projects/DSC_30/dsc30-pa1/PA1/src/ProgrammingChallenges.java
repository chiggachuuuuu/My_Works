/*
 * Name: Lin Tian
 * PID:  A16844916
 */

/**
 * Various Programming Challenges
 *
 * @author Lin Tian
 * @since 2023-10-04
 */

public class ProgrammingChallenges {

    /**
     * Problem 1
     * This is a method that checks if you have enough money to buy
     * certain goods from a store.
     * @param item a string expression represent commodity in the store.
     * @param inWallet a float number represents the budget in your wallet.
     * @param needed a float number represents the price of the good you
     *               want to purchase.
     * @return a boolean represent whether you can afford the good.
     */
    public static boolean store(String item, float inWallet, float needed) {
        if (inWallet >= needed) {
            if (item.equals("cake")) {
                return true;
            } else if (item.equals("sushi")) {
                return true;
            } else if (item.equals("ice-cream")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Problem 2
     * This is a method that compares two integer arrays under following criteria:
     * The first array is twice as long as the second array.
     * The first element of the first array is the opposite element of the last
     * element of the second array.
     * The last element of the first array is the opposite element of the first
     * element of the second array.
     * @param arr1 an integer array
     * @param arr2 an integer array.
     * @return a boolean as the result of the comparison (return true if all criteria met).
     */
    public static boolean compareArrays(int[] arr1, int[] arr2) {
        int l1 = arr1.length;
        int l2 = arr2.length;
        if (l1 == 2 * l2) {
            if (arr1[0] == (-1) * arr2[l2 - 1]) {
                if (arr1[l1 - 1] == (-1) * arr2[0]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Problem 3
     * This method counts the number of odd numbers not divisible by 7 in an array.
     * @param arr1 an integer array.
     * @return an integer number as the counts.
     */
    public static int countNumbers(int[] arr1) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 == 1) {
                if (arr1[i] % 7 != 0) {
                    count += 1;
                }
            }
        }
        return count;
    }


    /**
     * Problem 4
     * This method finds the number of positive integers in an array
     * and calculates the average of these positive integers.
     * @param arr1 an integer array.
     * @return an float array contains two elements: the count and the average.
     */
    public static float[] positiveAverage(int[] arr1) {
        float positiveCount = 0;
        int positiveSum = 0;
        int i = 0;
        while (i < arr1.length) {
            if (arr1[i] > 0) {
                positiveCount += 1;
                positiveSum += arr1[i];
            }
            i++;
        }
        float average = (float) Math.round((positiveSum / positiveCount) * 100) / 100;
        return new float[]{positiveCount, average};
    }

    /**
     * Problem 5
     * This method takes two numbers and returns true if the first digit of the first
     * number is the same as the last digit of the second number.
     * @param num1 a non-negative integer.
     * @param num2 a non-negative integer.
     * @return a boolean as the result.
     */
    public static boolean sameDigitFirstAndLast(int num1, int num2) {
        int firstDigit = num1;
        while (firstDigit > 10) {
            firstDigit = firstDigit / 10;
        }
        int lastDigit = num2 % 10;
        while (firstDigit == lastDigit) {
            return true;
        }
        return false;
    }


    /**
     * Problem 6
     * This tells if there are three consecutive decreasing numbers in the given array.
     * @param elems an integer array.
     * @return a boolean as the result.
     */
    public static boolean decreasingOrder(int[] elems) {
        int len = elems.length;
        int count = 1;
        for (int i = 0; i < len - 1; i++) {
            if (elems[i] < elems[i + 1]) {
                count += 1;
            } else {
                count = 1;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Problem 7
     * This method replaces all numbers on the diagonal of a given
     * square matrix with the sum of the diagonal numbers.
     * @param elems a 2D integer array that could be expressed as a square matrix.
     * @return null(use print method to see the mutated array).
     */
    public static void replaceMainDiagonal(int[][] elems) {
        int diagonalSum = 0;
        int len = elems.length;
        for (int i = 0; i < len; i++) {
            diagonalSum += elems[i][i];
        }
        for (int i = 0; i < len; i++) {
            elems[i][i] = diagonalSum;
        }
    }

    /**
     * Problem 8
     * This method calculates the average of a students grades or
     * the average of students grades on a single assignment,
     * depending on a given boolean.
     * @param grades a 2D integer array represents students' grades on each assignment.
     * @param index an integer represents the index of an assignment or student.
     * @param choice a boolean that decides which calculation to take.
     * @return a float number of the average grade.
     */
    public static float averageGrade(int[][] grades, int index, boolean choice) {
        if (grades.length == 0) {
            return 0f;
        }
        if (choice) {
            float assignmentSum = 0f;
            for (int i = 0; i < grades.length; i++) {
                assignmentSum += grades[i][index];
            }
            float assignmentAverage = (float) Math.round((assignmentSum / grades.length) * 1000) / 1000;
            return assignmentAverage;
        } else {
            float studentSum = 0f;
            int studentLen = grades[index].length;
            for (int i = 0; i < studentLen; i++) {
                studentSum += grades[index][i];
            }
            float studentAverage = (float) Math.round((studentSum / studentLen) * 1000) / 1000;
            return studentAverage;
        }
    }

    /**
     * Problem 9
     * This method uses recursion to remove all the dots '.' in the given string.
     * @param str a string possibly contains '.'.
     * @return a string contains no '.'.
     */
    public static String noDots(String str) {
        char dot = '.';
        if (str.isEmpty()) {
            return "";
        }
        if (str.charAt(0) == dot) {
            return noDots(str.substring(1));
        } else {
            return str.charAt(0) + noDots(str.substring(1));
        }
    }

    /**
     * Problem 10
     * This method returns an array with length of 2 from the given array
     * under the following rules:
     * If the original array's length is less than two, return an empty array.
     * If the original array's length is even, return the middle two elements
     * from the original array.
     * If the original array's length is odd, return the length of the array
     * and the min between first and last elements.
     * @param elems an integer array.
     * @return an integer array.
     */
    public static int[] twoElements(int[] elems) {
        int len = elems.length;
        int min = elems[0];
        if (len < 2) {
            return new int[]{};
        }
        if (len % 2 == 0) {
            return new int[]{elems[(len / 2) - 1], elems[len / 2]};
        } else {
            if (elems[0] <= elems[len - 1]) {
                return new int[]{len, min};
            } else {
                min = elems[len - 1];
                return new int[]{len, min};
            }
        }
    }
}