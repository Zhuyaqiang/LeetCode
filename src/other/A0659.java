package other;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 示例 3：
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * 提示：
 * 1 <= nums.length <= 10000
 */
public class A0659 {
    // map, 键是x，值是以x结尾的子序列的长度最小堆
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num))
                map.put(num, new PriorityQueue<>());
            if (map.containsKey(num - 1)) {
                int preLength = map.get(num - 1).poll();
                if (map.get(num - 1).isEmpty())
                    map.remove(num - 1);
                map.get(num).offer(preLength + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int val = entry.getValue().peek();
            if (val < 3)
                return false;
        }
        return true;
    }
}
