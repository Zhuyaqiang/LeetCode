package array;

import java.util.*;

/**
 * 找出第k小的距离对
 *
 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 示例 1:
 输入：
 nums = [1,3,1]
 k = 1
 输出：0
 解释：
 所有数对如下：
 (1,3) -> 2
 (1,1) -> 0
 (3,1) -> 2
 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 提示:
 2 <= len(nums) <= 10000.
 0 <= nums[i] < 1000000.
 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
public class A0719 {
    public static void main(String[] args) {
        int[] nums = {1,6,1};
        System.out.println(smallestDistancePair(nums, 3));
    }
    public static int smallestDistancePair(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                list.add(Math.abs(nums[i] - nums[j]));
            }
        }
        Collections.sort(list);
        System.out.println(list);
        int index = 1;
        int res = 0;
        for (Integer integer : list) {
            if (index == k) {
                res = integer;
                break;
            }
            index ++;
        }
        return res;
    }
}
