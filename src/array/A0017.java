package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class A0017 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        while (nums.length < 4)
            return ans;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len-3; i++) {
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            if (nums[len-1] + nums[len-2] + nums[len-3] + nums[len-4] < target)
                break;
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i + 1; j < len-2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1])
                    continue;
                int l = j + 1, r = len - 1;
                if (nums[i] + nums[j] + nums[r] + nums[r-1] < target)
                    continue;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[r] == nums[r-1])
                            r--;
                        while (l < r && nums[l] == nums[l+1])
                            l++;
                        l++;
                        r--;
                    } else if (sum < target)
                        l++;
                    else r--;
                }
            }
        }
        return ans;
    }
}
