package array;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 两数之和
 给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 示例:
 给定 nums = [2, 7, 11, 15], target = 9
 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
public class A0001 {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] ints = TwoSum(arr, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    public int[] rTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException();
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
