package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 输入: [2,1,5,6,2,3]
 * 输出: 10 (5,6)
 */
public class A0084 {
    public static void main(String[] args) {
        int[] heights = {6, 4, 5, 2, 4, 3, 9};
        System.out.println(largestRectangleArea4(heights));
    }
    // 暴力法, 超时
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        if (heights.length == 1)
            return heights[0];
        int max = 0, currMin;
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                currMin = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    currMin = Math.min(currMin, heights[k]);
                }
                max = Math.max(max, currMin * (j - i + 1));
            }
        }
        return max;
    }
    // 暴力法, 优化
    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        if (heights.length == 1)
            return heights[0];
        int max = 0, currMin;
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            currMin = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                currMin = Math.min(currMin, heights[j]);
                max = Math.max(max, currMin * (j - i + 1));
            }
        }
        return max;
    }

    // 分治
    public static int largestRectangleArea3(int[] heights) {
        return divide(heights, 0, heights.length-1);
    }
    public static int divide(int[] heights, int start, int end) {
        if (start > end)
            return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++)
            if (heights[minIndex] > heights[i]) {
                minIndex = i;
            }
        return Math.max(heights[minIndex] * (end - start + 1), Math.max(divide(heights, start, minIndex - 1), divide(heights, minIndex + 1, end)));
    }

    // 单调栈
    public static int largestRectangleArea4(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        int len = heights.length;
        if (len == 1)
            return heights[0];
        int [] newHeight = new int[len+2];
        newHeight[0] = 0;
        for (int i = 0; i < len; i++) {
            newHeight[i + 1] = heights[i];
        }
        len += 2;
        heights = newHeight;
        int res = 0;
        // 先在数组头尾加0, 作为边界条件
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            // 指针指向严格小于栈顶指向时, 可以计算栈顶高度的最大面积
            while (heights[i] < heights[stack.peekLast()]) {
                int currHeight = heights[stack.pollLast()];
                int currWidth = i - stack.peekLast() - 1;
                res = Math.max(res, currHeight * currWidth);
            }
            stack.addLast(i);
        }
        return res;
    }
}
