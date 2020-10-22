package other;

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
        int[] nums = {7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive1(nums));
        int[] nums2 = {1,2,0};
        System.out.println(firstMissingPositive1(nums2));
        int[] nums3 = {3,4,-1,1};
        System.out.println(firstMissingPositive1(nums3));
    }

    public static int firstMissingPositive1(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 1;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] >= len + 1)
                nums[i] = len + 2;
        }
        for (int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]);
            if (val > 0 && val < len + 1) {
                if (val == len) {
                    nums[0] = -Math.abs(nums[0]);
                } else
                    nums[val] = -Math.abs(nums[val]);
            }
        }
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0)
                return i;
        }
        if (nums[0] > 0)
            return len;
        return len + 1;
    }
    // 数组长度为n返回值的取值范围为[1, n+1]
    // n+1的出现用nums[0]表示


    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len)
                nums[i] = len + 1;
        }
        for (int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]);
            if (val >= 1 && val <= len)
                nums[val - 1] = -Math.abs(nums[val - 1]);
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0)
                return i + 1;
        }
        return len + 1;
    }
}
