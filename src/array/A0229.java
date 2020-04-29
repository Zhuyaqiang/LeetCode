package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 求众数2
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 */
public class A0229 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,3,4,5,6};
        List<Integer> integers = majorityElement(nums);
        System.out.println(integers);
    }
    // 摩尔投票, 因为大于n/k的数至多k-1个, 所以需要两个count
    public static List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        List<Integer> res = new ArrayList<>();
        int len =nums.length;
        if (len == 0)
            return res;
        int candidate1 = nums[0], candidate2 = nums[0];
        for (int i = 0; i < len; i++) {
            if (candidate1 == nums[i]) {
                count1 ++;
                continue;
            }
            if (candidate2 == nums[i]) {
                count2 ++;
                continue;
            }
            if (count1 == 0) {
                count1++;
                candidate1 = nums[i];
                continue;
            }
            if (count2 == 0) {
                count2++;
                candidate2 = nums[i];
                continue;
            }
            count1--;
            count2--;
        }
        System.out.println("candidate1: " + candidate1 + ", count: " + count1);
        System.out.println("candidate2: " + candidate2 + ", count: " + count2);
        count1 = count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            }
        }
        if (count1 > len / 3)
            res.add(candidate1);
        if (count2 > len / 3)
            res.add(candidate2);
        return res;
    }
}
