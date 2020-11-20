package array;

import java.util.Arrays;

/**
 * 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class A0055 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump2(nums));
    }
    public static boolean canJump(int[] nums) {
        int len = nums.length;
        int i = len - 1;
        while (i > 0) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] + j >= i) {
                    flag = true;
                    i = j;
                    break;
                }
            }
            if (!flag)
                return false;
        }
        return true;
    }
    // 自后向前动态规划
    public static boolean canJump2(int[] nums) {
        int len = nums.length;
        boolean[] ans = new boolean[len];
        for (int i = 0; i < len-1; i++)
            ans[i] = false;
        ans[len-1] = true;
        for (int i = len-2; i >=0; i--) {
            int max = i + nums[i];
            for (int j = i + 1; j < len && j <= max; j++) {
                if (ans[j]) {
                    ans[i] = true;
                    break;
                }
            }
        }
        return ans[0];
    }
    // 自后向前动态规划递归
    static int ans[];
    public static boolean canJump3(int[] nums) {
        ans = new int[nums.length];
        for (int i = 0; i < nums.length-1; i++)
            ans[i] = 2;
        ans[nums.length-1] = 1;
        return can(0, nums);
    }
    public static boolean can(int start, int[] nums) {
        if (ans[start] != 2)
            return ans[start] == 1;
        int max = nums[start] + start;
        for (int i = start + 1; i < nums.length && i <= max; i++) {
            if (can(i, nums)) {
                ans[start] = 1;
                return true;
            }
        }
        ans[start] = 0;
        return false;
    }
    // 贪心
    public static boolean canJump4(int[] nums) {
        int len = nums.length;
        // lastPos为从左往右数能跳到nums数组的最后一个数字
        int lastPos = len-1;
        for (int i = len-1; i >= 0; i--) {
            if (nums[i] + i >= lastPos)
                lastPos = i;
        }
        return lastPos == 0;
    }

    // 从前往后, 设置边界和最大值
    public static boolean rCanjump(int[] nums) {
        int end = 0, max = 0, start;
        int len = nums.length;
        for (start = 0; start < len; start++) {
            max = Math.max(max, start + nums[start]);
            if (end == start) {
                if (end == max && start < len-1)
                    return false;
                end = max;
            }
        }
        return true;
    }
}
