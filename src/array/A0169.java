package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class A0169 {
    public static void main(String[] args) {
        int[] num = {6, 5, 5};
        System.out.println(majorityElement3(num));
    }
    // 先排序, 再查找
    public static int majorityElement(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        return nums[nums.length/2];
    }
    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int start = l, end = r, pivot = nums[start];
            while (start < end) {
                while (start < end && nums[end] >= pivot) end--;
                if (start < end) {
                    nums[start] = nums[end];
                    start ++;
                }
                while (start < end && nums[start] < pivot) start++;
                if (start < end) {
                    nums[end] = nums[start];
                    end --;
                }
            }
            nums[start] = pivot;
            quickSort(nums, l, start - 1);
            quickSort(nums, start + 1, r);
        }
    }

    // 哈希表
    public static int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int num : nums) {
            if (map.containsKey(num)) {
                int res = map.get(num);
                if (res > len / 2 - 1)
                    return num;
                else
                    map.put(num, res + 1);
            }
            else
                map.put(num, 1);
        }
        return nums[0];
    }
    // 摩尔投票算法
    public static int majorityElement3(int[] nums) {
        int count = 1, res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (res == nums[i])
                count++;
            else {
                count--;
                if (count == 0) {
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }
}
