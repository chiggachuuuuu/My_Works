import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammingChallengesTest {
    @org.junit.jupiter.api.Test
    void store() {
        String item = "ice-cream";
        float inWallet = 100f;
        float needed = 5f;
        boolean expected = true;
        boolean actual = ProgrammingChallenges.store(item, inWallet, needed);
        assertEquals(expected, actual);
    }

    @Test
    void compareArrays() {
        int[] arr1 = new int[]{ 1 };
        int[] arr2 = new int[]{ 1 };
        boolean expected = false;
        boolean actual = ProgrammingChallenges.compareArrays(arr1, arr2);
        assertEquals(expected, actual);
    }

    @Test
    void countNumbers() {
        int[] arr1 = new int[]{};
        int expected = 0;
        int actual = ProgrammingChallenges.countNumbers(arr1);
        assertEquals(expected, actual);
    }

    @Test
    void positiveAverage() {
        int[] arr1 = new int[]{ 1, 3, 4 };
        float[] expected = new float[]{3.0f, 2.67f};
        float[] actual = ProgrammingChallenges.positiveAverage(arr1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void sameDigitFirstAndLast() {
        int num1 =0;
        int num2 =100;
        boolean expected = true;
        boolean actual = ProgrammingChallenges.sameDigitFirstAndLast(num1, num2);
        assertEquals(expected, actual);
    }

    @Test
    void decreasingOrder() {
        int[] elems = new int[]{ 6, 3, 5, 2, 4, 1 };
        boolean expected = false;
        boolean actual = ProgrammingChallenges.decreasingOrder(elems);
        assertEquals(expected, actual);
    }

    @Test
    void replaceMainDiagonal() {
        int[][] elems = new int[][]{ {-4, -4, -6 }, { -4, -4, -6 }, { -4, -4, -6  } };
        ProgrammingChallenges.replaceMainDiagonal(elems);
        for (int[] elem : elems) {
            System.out.println(Arrays.toString(elem));
        }
    }

    @Test
    void averageGrade() {
        int[][] grades = new int[][]{ {} };
        int index = 0;
        boolean choice = false;
        float expected = 0.0f;
        float actual = ProgrammingChallenges.averageGrade(grades, index, choice);
        assertEquals(expected, actual);
    }

    @Test
    void noDots() {
        String str = ".......................";
        String expected = "";
        String actual = ProgrammingChallenges.noDots(str);
        assertEquals(expected, actual);
    }

    @Test
    void twoElements() {
        int[] elms = new int[]{ 3 };
        int[] expected = new int[]{  };
        int[] actual = ProgrammingChallenges.twoElements(elms);
        assertArrayEquals(expected, actual);
    }
}