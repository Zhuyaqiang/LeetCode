package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class A0315 {
    public static void main(String[] args) {
        int[] nums = {0,2,1};
        List<Integer> integers = countSmaller(nums);
        System.out.println(integers);
    }
    // 暴力法, 超时
    public static List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return new ArrayList<>();
        int[] res = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[i])
                    count ++;
                if (nums[j] == nums[i]) {
                    count += res[j];
                    break;
                }
                if (nums[j] > nums[i] && res[j] == 0)
                    break;
            }
            res[i] = count;
        }
        List<Integer> ans = new ArrayList<>();
        for (int re : res) {
            ans.add(re);
        }
        return ans;
    }
//    public static List<Integer> countSmaller2(int[] nums) {
//
//
//    }
}
