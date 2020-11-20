package other;

import java.util.Arrays;

/**
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class O51 {
    // 暴力, 超时
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j])
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {7, 5, 4};
//        int[] nums = new int[50000];
//        for (int i = 0; i < 50000; i++) {
//            nums[i] = 50000-i;
//        }
        System.out.println(reversePairs2(nums));
        System.out.println(Arrays.toString(nums));
    }

    // 基于归并排序, 在归并元素时计算逆序对的个数
    // https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/bao-li-jie-fa-fen-zhi-si-xiang-shu-zhuang-shu-zu-b/
    public static int reversePairs2(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        int[] copy = Arrays.copyOf(nums, len);
        int[] temp = new int[len];
        return mergeSort(copy, 0, len - 1, temp);
    }

    public static int mergeSort(int[] nums, int start, int end, int[] temp) {
        if (start == end)
            return 0;
        int mid = start + (end - start) / 2;
        int l = mergeSort(nums, start, mid, temp);
        int r = mergeSort(nums, mid + 1, end, temp);
        // nums是有序的
        if (nums[mid] <= nums[mid + 1]) {
            return l + r;
        }
        int cross = mergeAndAccount(nums, start, end, mid, temp);
        return cross + l + r;

    }
    private static int mergeAndAccount(int[] nums, int l, int r, int mid, int[] temp) {
        for (int q = l; q <= r; q++)
            temp[q] = nums[q];
        int i = l, j = mid + 1;
        int count = 0;
        for (int k = l; k <= r; k++) {
            // 第一个区间越界
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == r + 1) {
                nums[k] = temp[i];
                count += r - mid; // 和81行对应, 归并第一个区间
                i++;
            } else if (temp[i] <= temp[j]) {
                // 为什么该判断条件是小于等于而不是小于?
                // 因为如果是小于的话, 无法判断(2,3,4), (2,2,3)这种情况
                nums[k] = temp[i];
                // temp的两端是分别升序的, 当temp[i] <= temp[j]且temp[i]要被归并到nums里的时候, temp的[mid+1,j-1], 均小于等于temp[i], 因此逆序对增加爱j - mid -1
                 count += j - mid - 1;  // 和74行对应, 归并第一个区间
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                // temp的两段是分别升序的, 当temp[i] > temp[j]且temp[j]要被归并到nums里的时候, i和j逆序, 同时, temp的[i, mid]均大于temp[j], 逆序对增加mid - i + 1
                // 此时, temp的[mid+1, j]均小于temp[i], 但是不使用该条件判断而使用上个条件判断的原因是会重复计算[mid+1, j-1]的次数
//                count += mid - i + 1;
            }
        }
        return count;
    }
}
