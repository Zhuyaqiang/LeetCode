package array;

/**
 * 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 */
public class A0268 {
    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(rMissingNumber(nums));
    }
    public static int missingNumber(int[] nums) {
        int len = nums.length;
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                flag = true;
                nums[i] = len + 1;
            }
        }
        if (!flag)
            return 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == len) {
                nums[0] = -Math.abs(nums[0]);
            } else if (Math.abs(nums[i]) < len) {
                nums[Math.abs(nums[i])] = -Math.abs(nums[Math.abs(nums[i])]);
            }
        }
        int res = len;
        for (int i = 1; i < len; i++)
            if (nums[i] > 0) {
                res = i;
                break;
            }
        return res;
    }

    // 数学方法
    public static int missingNumber2(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    public static int rMissingNumber(int[] nums) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0)
                count ++;
            else
                nums[i] = len + 1;
        }
        if (count == len)
            return 0;
        boolean flag = false; // 判断n
        for (int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]);
            if (val == len)
                flag = true;
            else if (val == len + 1)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[val] = -Math.abs(nums[val]);
        }
        if (!flag)
            return len;
        int index;
        for (index = 0; index < len; index++) {
            if (nums[index] > 0)
                break;
        }
        return index;
    }
}
