package other;

import java.util.*;

/**
 * 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class A0349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i]))
                res.add(nums2[i]);
        }
        int[] ans = new int[res.size()];
        int index = 0;
        for (Integer re : res) {
            ans[index ++] = re;
        }
        return ans;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int oneIndex = 0, twoIndex = 0;
        int len1 = nums1.length, len2 = nums2.length;
        int[] res = new int[len1];
        int index = 0;
        while (oneIndex < len1 && twoIndex < len2) {
            if (oneIndex > 0 && nums1[oneIndex] == nums1[oneIndex - 1])
                oneIndex ++;
            else if (twoIndex > 0 && nums2[twoIndex] == nums2[twoIndex - 1])
                twoIndex ++;
            else if (nums1[oneIndex] == nums2[twoIndex]) {
                res[index ++] = nums1[oneIndex];
                oneIndex ++;
                twoIndex ++;
            } else if (nums1[oneIndex] > nums2[twoIndex]) {
                twoIndex ++;
            } else
                oneIndex ++;
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
