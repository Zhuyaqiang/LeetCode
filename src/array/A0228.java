package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇总区间
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 * 示例 1:
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 示例 2:
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 */
public class A0228 {
    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        List<String> strings = summaryRanges(nums);
        System.out.println(strings);
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int index = 0, len = nums.length;
        while (index < len) {
            if (index == len - 1 || nums[index+1] != nums[index] + 1) {
                res.add(nums[index] + "");
            } else {
                int start = index;
                while (index < len-1 && nums[index]+1 == nums[index+1]) index ++;
                res.add(nums[start] + "->" + nums[index]);
            }
            index ++;
        }
        return res;
    }
}
