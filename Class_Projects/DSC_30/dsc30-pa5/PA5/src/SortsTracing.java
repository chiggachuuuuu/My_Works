import java.util.ArrayList;

public class SortsTracing {

    //Note: Style is not required for this file

    //for reference
    public ArrayList<int[]> SelectionSortExampleList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{-1, 20, 18, 17, 9, 4, 2, 0, 40});
        answer.add(new int[]{-1, 0, 18, 17, 9, 4, 2, 20, 40});
        answer.add(new int[]{-1, 0, 2, 17, 9, 4, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        // etc... (the rest of the iterations)
        return answer;
    }

    //for reference
    public ArrayList<int[]> CountSortExampleList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        //After Counting Step
        answer.add(new int[]{0, 1, 1, 0, 1});
        //After "Running Total" Step
        answer.add(new int[]{0, 1, 2, 2, 3});
        //Rest of iterations to assemble output array
        answer.add(new int[]{0, 0, 2, 2, 3});
        answer.add(new int[]{0, 0, 2, 2, 2});
        answer.add(new int[]{0, 0, 1, 2, 2});
        return answer;
    }

    public ArrayList<int[]> InsertionSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{1, 6, 2, 7, 4, 9, 5});
        answer.add(new int[]{1, 2, 6, 7, 4, 9, 5});
        answer.add(new int[]{1, 2, 6, 7, 4, 9, 5});
        answer.add(new int[]{1, 2, 4, 6, 7, 9, 5});
        answer.add(new int[]{1, 2, 4, 6, 7, 9, 5});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        return answer;
    }

    public ArrayList<int[]> InsertionSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        return answer;
    }

    public ArrayList<int[]> InsertionSortReversedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{7, 9, 6, 5, 4, 2, 1});
        answer.add(new int[]{6, 7, 9, 5, 4, 2, 1});
        answer.add(new int[]{5, 6, 7, 9, 4, 2, 1});
        answer.add(new int[]{4, 5, 6, 7, 9, 2, 1});
        answer.add(new int[]{2, 4, 5, 6, 7, 9, 1});
        answer.add(new int[]{1, 2, 4, 5, 6, 7, 9});
        return answer;
    }
    
    public ArrayList<int[]> CountSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{0, 1, 1, 0, 1, 1, 1, 1, 0, 1});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 4, 6, 6, 7});
        answer.add(new int[]{0, 0, 2, 2, 3, 4, 4, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 3, 4, 4, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 3, 4, 4, 5, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 4, 4, 5, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 4, 4, 5, 6, 6});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 4, 5, 6, 6});
        return answer;
    }

    public ArrayList<int[]> CountSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{0, 1, 1, 0, 1, 1, 1, 1, 0, 1});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 0, 2, 2, 3, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 3, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 5, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 4, 6, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 4, 5, 6, 7});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 4, 5, 6, 6});
        return answer;
    }

    public ArrayList<int[]> CountSortReversedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{0, 1, 1, 0, 1, 1, 1, 1, 0, 1});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 5, 6, 6, 7});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 5, 6, 6, 6});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 5, 5, 6, 6});
        answer.add(new int[]{0, 1, 2, 2, 3, 4, 4, 5, 6, 6});
        answer.add(new int[]{0, 1, 2, 2, 3, 3, 4, 5, 6, 6});
        answer.add(new int[]{0, 1, 2, 2, 2, 3, 4, 5, 6, 6});
        answer.add(new int[]{0, 1, 1, 2, 2, 3, 4, 5, 6, 6});
        answer.add(new int[]{0, 0, 1, 2, 2, 3, 4, 5, 6, 6});
        return answer;
    }

    public ArrayList<int[]> QuickSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{1, 8, 7, 2, 5});
        answer.add(new int[]{1, 5, 7, 2, 8});
        answer.add(new int[]{1, 2, 7, 5, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        return answer;
    }

    public ArrayList<int[]> QuickSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{1, 2, 5, 7, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        return answer;
    }

    public ArrayList<int[]> QuickSortReversedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{1, 7, 5, 2, 8});
        answer.add(new int[]{1, 7, 5, 2, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        answer.add(new int[]{1, 2, 5, 7, 8});
        return answer;
    }

}