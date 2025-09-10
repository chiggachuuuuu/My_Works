/**
 * PA7 Part 1 worksheet (style not required)
 */
public class Worksheet {

    /**
     * method that stores the result of Q1 in PA7 worksheet
     * @return heap's array representation after insertions
     */
    public static int[] insertionResult(){
        int[] output =  new int[]{99, 94, 68, 59, 83, 13, 37, 12, 44, 43, 71, 3};
        return output;
    }

    /**
     * method that stores the result of Q2 in PA7 worksheet
     * @return heap's array representations for each iteration of element removal, 5 iterations in total
     */
    public static int[][] removalResult(){
        int[][] output = new int[5][12];
        output[0] = new int[]{31, 46, 93, 49, 73, 99, 94, 53, 87, 91, 95, 0};
        output[1] = new int[]{46, 49, 93, 53, 73, 99, 94, 95, 87, 91, 0, 0};
        output[2] = new int[]{49, 53, 93, 87, 73, 99, 94, 95, 91, 0, 0, 0};
        output[3] = new int[]{53, 73, 93, 87, 91, 99, 94, 95, 0, 0, 0 , 0};
        output[4] = new int[]{73, 87, 93, 95, 91, 99, 94, 0, 0, 0, 0, 0};
        return output;
    }

    /**
     * method that stores the result of Q3 in PA7 worksheet
     * @return heap's array representations for d-ary heaps, where d = 3,4
     */
    public static int[][] dResult(){
        int[][] output = new int[2][11];
        output[0] = new int[]{92, 53, 90, 30, 12, 25, 50, 34, 75, 33, 2};
        output[1] = new int[]{92, 90, 34, 30, 25, 12, 50, 53, 75, 33, 2};
        return output;
    }

    /**
     * method that stores the result of Q4 in PA7 worksheet
     * @return array representations
     */
    public static int[][] heapOperations(){
        int[][] output = new int[5][12];
        output[0] = new int[]{58, 42, 53, 19, 27, 41, 12, 9, 12, 0, 0, 0};
        output[1] = new int[]{90, 42, 53, 58, 27, 41, 12, 9, 12, 10, 19, 0};
        output[2] = new int[]{58, 42, 53, 19, 27, 41, 12, 9, 12, 10, 0, 0};
        output[3] = new int[]{83, 42, 53, 67, 27, 41, 12, 9, 12, 10, 19, 58};
        output[4] = new int[]{41, 27, 12, 19, 9, 10, 12, 0, 0, 0, 0, 0};
        return output;
    }
}