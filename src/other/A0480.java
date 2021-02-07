package other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 *
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 *  
 *
 * 示例：
 *
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0480 {
    public static void main(String[] args) {
        int[] nums = {1,4,2,3};
        System.out.println(Arrays.toString(medianSlidingWindow(nums, 4)));
        int[] nums2 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(medianSlidingWindow(nums2, 3)));
        int[] nums3 = {2147483647,2147483647};
        System.out.println(Arrays.toString(medianSlidingWindow(nums3, 2)));
    }
    // 朴素优先队列, 超时
    public static double[] medianSlidingWindow(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int len = nums.length;
        if (len < k) {
            return null;
        }
        double[] res = new double[len - k + 1];
        for (int i = 0; i < k - 1; i++) {
            queue.add(nums[i]);
        }
        int index = 0;
        for (int i = k - 1; i < len; i++) {
            if (i > k - 1) {
                queue.remove(nums[i - k]);
            }
            queue.add(nums[i]);
            int mid = (k - 1) / 2;
            int mid2 = k / 2;
            for (int j = 0; j < mid; j++) {
                stack.push(queue.poll());
            }
            double one = queue.poll();
            double two;
            if (mid  == mid2) {
                two = one;
            } else {
                two = queue.peek();
            }
            res[index++] = (one + two) / 2;
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }
            queue.add((int) one);
        }
        return res;
    }
}
