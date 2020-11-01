package array;

import java.util.*;

/**
 * 数组嵌套
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 * 示例 1:
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * 注意:
 * N是[1, 20,000]之间的整数。
 * A中不含有重复的元素。
 * A中的元素大小在[0, N-1]之间。
 */
public class A0565 {
    /**
     * 所有思想都是只要出现在别的遍历循环里的数字都可以不用再判断了
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {0, 2, 1};
        System.out.println(arrayNesting4(nums));
    }
    // 暴力法, 超时
    public static int arrayNesting(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = Math.max(count, max(nums, i));
        }
        return count;
    }
    public static int max(int[] nums, int start) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        while (!set.contains(nums[start])) {
            count ++;
            int val = nums[start];
            set.add(val);
            start = val;
        }
        return count;
    }

    public static int arrayNesting2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> temp;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                temp = new HashSet<>();
                int index = i;
                int tempCount = 0;
                while(!temp.contains(nums[index])) {
                    tempCount ++;
                    int val = nums[index];
                    set.add(val);
                    temp.add(val);
                    index = val;
                }
                count = Math.max(count, tempCount);
            }
        }
        return count;
    }

    // 额外数组
    public static int arrayNesting3(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int start = nums[i], index = i, count = 0;
                do {
                    count ++;
                    visited[index] = true;
                    index = nums[index];
                } while (nums[index] != start);
                res = Math.max(res, count);
            }
        }
        return res;
    }
    // 输入数组本身判断状态
    public static int arrayNesting4(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < Integer.MAX_VALUE) {
                int index = i, start = nums[i], count = 0;
                do {
                    count ++;
                    int temp = nums[index];
                    nums[index] = Integer.MAX_VALUE;
                    index = temp;
                } while (nums[index] < Integer.MAX_VALUE && nums[index] != start);
                res = Math.max(count, res);
            }
        }
        return res;
    }
}
