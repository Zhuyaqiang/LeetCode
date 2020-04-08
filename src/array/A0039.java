package array;

import java.util.ArrayList;
import java.util.List;

public class A0039 {
    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        List<List<Integer>> lists = combinationSum(candidates, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    public static List<List<Integer>> res= new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return res;
        backtrack(0, candidates, target, new ArrayList<>());
        return res;
    }
    public static void backtrack(int start, int[] candidates, int target, List<Integer> list) {
        if (target == 0)
            res.add(new ArrayList<>(list));
        else if (target < 0)
            return;
        else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                backtrack(i, candidates, target - candidates[i], list);
                list.remove(list.size()-1);
            }
        }
    }
}
