package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A0040 {
    public static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        List<List<Integer>> lists = combinationSum2(candidates, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0 || candidates == null || target < 0)
            return result;
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>());
        return result;
    }
    public static void backtrack(int start, int[] candidates, int target, List<Integer> list) {
        if(target == 0)
            result.add(new ArrayList<>(list));
        else if (target < 0)
            return;
        else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i-1])
                    continue;
                list.add(candidates[i]);
                backtrack(i + 1, candidates, target - candidates[i], list);
                list.remove(list.size()-1);
            }
        }
    }
}
