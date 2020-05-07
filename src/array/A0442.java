package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组中重复的数据
 *
 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 找到所有出现两次的元素。
 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 示例：
 输入:
 [4,3,2,7,8,2,3,1]
 输出:
 [2,3]
 */
public class A0442 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> duplicates = findDuplicates(nums);
        System.out.println(duplicates);
    }
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (Math.abs(nums[i]) == len) {
                if (nums[0] < 0) {
                    res.add(Math.abs(nums[i]));
                    continue;
                }
                nums[0] = -Math.abs(nums[0]);
            } else {
                int index = Math.abs(nums[i]);
                if (nums[index] < 0) {
                    res.add(Math.abs(nums[i]));
                    continue;
                }
                nums[index] = -Math.abs(nums[index]);
            }
        }
        return res;
    }
}
