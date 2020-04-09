package array;

/**
 * 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class A0011 {
    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        System.out.println(Solution1(nums));
    }
    public static int Solution1(int[] height) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = height[i] > height[j] ? height[j] : height[i];
                int area = h * (j-i);
                if (area > res)
                    res = area;
            }
        }
        return res;
    }
    public static int Solution2(int[] height) {
        int start = 0, end = height.length-1;
        int res = Integer.MIN_VALUE;
        while (start < end) {
            int h = Math.min(height[start], height[end]);
            res = Math.max(res, h * (end-start));
            if (height[start] > height[end])
                end--;
            else
                start++;
        }
        return res;
    }
}
