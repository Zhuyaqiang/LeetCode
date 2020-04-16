package array;

/**
 * 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 */
public class A0041 {
    public static void main(String[] args) {
        int[] nums = {1,2,0};
        System.out.println(firstMissingPositive(nums));
    }
    // 返回值一定在[1, nums.length+1]中
    // 先判断1有没有出现
    // 将nums中负值和大于等于nums.length+1(如果出现nums.length+1, 那么缺失的最小值一定小于等于nums.length)的值用1替换
    // 通过原数组数值的符号判断是否出现
    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 1;
        int len = nums.length;
        int contains = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1)
                contains ++;
        }
        if (contains == 0)
            return 1;
        if (len == 1)
            return 2;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 1 || nums[i] > len)
                nums[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            int temp = Math.abs(nums[i]);
            if (temp == len)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[temp] = -Math.abs(nums[temp]);
        }
        for (int i = 1; i < len; i++)
            if (nums[i] > 0)
                return i;
        if (nums[0] > 0)
            return len;
        return len + 1;
    }
}
