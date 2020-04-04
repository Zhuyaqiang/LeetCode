package array.a0001;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] ints = TwoSum2(arr, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    public static int[] TwoSum(int[] nums, int target) {
        if (nums.length < 2)
            return null;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (a + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    public static int[] TwoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if (map.containsKey(b))
                return new int[] {map.get(b), i};
            else
                map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
