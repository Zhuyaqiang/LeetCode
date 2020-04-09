package array;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 示例:
 输入: [2,3,1,1,4]
 输出: 2
 解释: 跳到最后一个位置的最小跳跃数是 2。
      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 说明:
 假设你总是可以到达数组的最后一个位置。
 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/jump-game-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class A0045 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump2(nums));
    }
    // 超时, 动态规划
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int[] jump = new int[len];
        jump[0] = 0;
        for (int i = 1; i < len; i++) {
            jump[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    jump[i] = Math.min(jump[j] + 1, jump[i]);
                }
            }
        }
        return jump[len-1];
    }
    // 贪心算法1
    // 从第0个点开始确定边界, 之后在边界范围内可以跳到的最远的点为新的边界
    public static int jump2(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;
        int len = nums.length;
        int start = 0, step = 0, end = 0, max = 0;
        while (start < len-1) {
            max = Math.max(nums[start] + start, max);
            // 指针移动到边界了, 更新边界
            if (start == end) {
                end = max;
                step ++;
            }
            start ++;
        }
        return step;
    }
    // 贪心算法2
    // 从右往左遍历, 找到能跳到该点的最左边的点
    public static int jump3(int[] nums) {
        int len = nums.length;
        int pos = len-1, step = 0;
        while (pos > 0) {
            for (int i = 0; i < pos; i++) {
                if (nums[i] + i >= pos) {
                    step ++;
                    pos = i;
                    break;
                }
            }
        }
        return step;
    }
}
