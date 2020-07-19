package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大整除子集
 * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 * 如果有多个目标子集，返回其中任何一个均可。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2] (当然, [1,3] 也正确)
 * 示例 2:
 * 输入: [1,2,4,8]
 * 输出: [1,2,4,8]
 */
public class A0368 {
    // 排序之后从前往后, 每个数字再遍历自己之前所有的, 找到最长整除子集
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] lasts = new int[len];
        int[] lengths = new int[len];
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int length = -1;
            int last = -1;
            // 找到nums[i]前能整除nums[i]的最长整除子集
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && lengths[j] + 1 > length) {
                    last = j;
                    length = lengths[j] + 1;
                }
            }
            lengths[i] = length == -1 ? 1 : length;
            lasts[i] = last == -1 ? i : last;
        }
        int max = -1, index = -1;
        for (int i = 0; i < len; i++) {
            if (lengths[i] > max) {
                max = lengths[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            res.add(nums[index]);
            index = lasts[index];
        }
        return res;
    }
}
