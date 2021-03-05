package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 示例 1：
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 * 提示：
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class A0697 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                int val = map.get(num);
                val++;
                max = Math.max(max, val);
                map.put(num, val);
            } else {
                map.put(num, 1);
                max = Math.max(1, max);
            }
        }
        int len = nums.length;
        int min = len;
        for (Map.Entry entry : map.entrySet()) {
            int count = (int) entry.getValue();
            int num = (int) entry.getKey();
            if (num == max) {
                int l = 0, r = len - 1;
                while (l <= r) {
                    while (l <= r && nums[l] != num) {
                        l++;
                    }
                    while (l <= r && nums[r] != num) {
                        r--;
                    }
                    if (nums[l] == num && nums[r] == num) {
                        min = Math.min(min, r - l + 1);
                    }
                }
            }
        }
        return min;
    }
}
