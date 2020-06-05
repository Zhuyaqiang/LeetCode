package array;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 数组的度
 *
 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 示例 1:
 输入: [1, 2, 2, 3, 1]
 输出: 2
 解释:
 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 连续子数组里面拥有相同度的有如下所示:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 最短连续子数组[2, 2]的长度为2，所以返回2.
 示例 2:
 输入: [1,2,2,3,1,4,2]
 输出: 6
 */
public class A0697 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1,4,2};
        System.out.println(findShortestSubArray2(nums));
    }
    public static int findShortestSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 1;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                int val = map.get(nums[i]);
                maxCount = Math.max(maxCount, val + 1);
                map.put(nums[i], val+1);
            }
        }

        if (maxCount == 1)
            return 1;
        Iterator iterator = map.entrySet().iterator();
        int minLen = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            int val = (int) entry.getValue();
            int key = (int) entry.getKey();
            if (val == maxCount) {
                int l, r;
                for (l = 0; l < len; l++) {
                    if (nums[l] == key)
                        break;
                }
                for (r = len-1; r >= 0; r--) {
                    if (nums[r] == key)
                        break;
                }
                minLen = Math.min(minLen, (r-l+1));
                break;
            }
        }

        return minLen;
    }

    // 三个map一个记录出现次数, 一个记录左边界, 一个记录右边界
    public static int findShortestSubArray2(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> left = new HashMap<>(), right = new HashMap<>(), count = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            if (!left.containsKey(val)) {
                left.put(val, i);
            }
            right.put(val, i);
            count.put(val, count.getOrDefault(val, 0) + 1);
        }
        int max = Collections.max(count.values());
        int ans = Integer.MAX_VALUE;
        for (int x: count.keySet()) {
            if (count.get(x) == max) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
}
