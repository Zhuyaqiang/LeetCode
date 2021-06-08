package array;

import java.util.Stack;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class A0042 {
    public static void main(String[] args) {
        int[] height = {2,0,2};
        System.out.println(rTrap(height));
    }
    public static int rTrap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        while (curr < len) {
            while (!stack.isEmpty() && height[curr] > stack.peek()) {
                int h = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int min = Math.min(height[left], height[curr]);
                int distance = curr - left - 1;
                res += distance * (min - h);
            }
            stack.push(curr);
            curr++;
        }
        return res;
    }
    // 按行求 超时
    public static int trap(int[] height) {
        int res = 0;
        int len = height.length;
        int max = height[0];
        for (int i = 1; i < len; i++) {
            max = max > height[i] ? max : height[i];
        }
        for (int i = 1; i <= max; i++) {
            int temp = 0;
            boolean isStart = false;
            for (int j = 0; j < len; j++) {
                if (height[j] < i && isStart)
                    temp ++;
                if (height[j] >= i) {
                    res += temp;
                    temp = 0;
                    isStart = true;
                }
            }
        }
        return res;
    }
    // 按列找, 找到左边最高和右边最高, 取两者最小值
    public static int trap2(int[] height) {
        int sum = 0;
        int len = height.length;
        for (int i = 1; i < len - 1; i++) {
            int leftMax = height[i-1];
            int rightMax = height[i+1];
            for (int j = i - 1; j >=0; j--)
                leftMax = Math.max(leftMax, height[j]);
            for (int j = i + 1; j < len; j++)
                rightMax = Math.max(rightMax, height[j]);
            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                sum += min - height[i];
            }
        }
        return sum;
    }
    // 按列求动态规划
    public static int trap3(int[] height) {
        int len = height.length;
        int sum = 0;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        for (int i = 1; i < len-1; i++)
            leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
        for (int i = len-2; i > 0; i--)
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        for (int i = 1; i < len-1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i])
                sum += min - height[i];
        }
        return sum;
    }
    // 按列求动态规划双指针
    public static int trap4(int[] height) {
        int len = height.length;
        int sum = 0;
        int rightMax = 0, leftMax = 0;
        int left = 1, right = len-2;
        for (int i = 1; i < height.length-1; i ++) {
            // 在right+1 >= left-1之前, 左边的最高一定小于右边的最高
            // 更新height[left], 因为对于height[left]来说, 其左边最高的边一定比右边最高的边低
            if (height[left-1] < height[right+1]) {
                leftMax = Math.max(leftMax, height[left-1]);
                int min = leftMax;
                if (min > height[left])
                    sum += min - height[left];
                left++;
            }
            // 在right+1 < left-1之前, 右边的最高一定小于等于左边的最高
            // 更新height[right], 因为对于height[right]来说, 其右边最高的边一定小于等于左边最高的边
            else {
                rightMax = Math.max(rightMax, height[right + 1]);
                int min = rightMax;
                if (min > height[right])
                    sum += min - height[right];
                right--;
            }
        }
        return sum;
    }
    // 单调栈
    public static int trap5(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[current], height[stack.peek()]);
                sum += distance * (min - h);
            }
            stack.push(current);
            current++;
        }
        return sum;
    }
}
