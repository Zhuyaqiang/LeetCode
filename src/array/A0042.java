package array;

import java.util.Stack;

public class A0042 {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap5(height));
    }
    // 按行求
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
            // 更新height[left], 因为对于height[left]来说, 其左边最高的边一定比右边最高的边低
            if (height[left-1] < height[right+1]) {
                leftMax = Math.max(leftMax, height[left-1]);
                int min = leftMax;
                if (min > height[left])
                    sum += min - height[left];
                left++;
            }
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
