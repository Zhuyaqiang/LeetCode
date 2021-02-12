package other;

import java.util.Arrays;

/**
 * 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * 输入：[100]
 * 输出：1
 * 提示：
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 */
public class A0978 {
    public static void main(String[] args) {
        System.out.println(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
        System.out.println(maxTurbulenceSize(new int[]{4,8,12,16}));
        System.out.println(maxTurbulenceSize(new int[]{100}));
        System.out.println(maxTurbulenceSize(new int[]{9, 9}));
    }
    // 双指针
    public static int maxTurbulenceSize(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        int[] temp = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            temp[i] = Integer.compare(arr[i], arr[i + 1]);
        }
        int l = 0, r = l + 1;
        int max = 1;
        do {
            if (l == r - 1) {
                if (arr[l] != arr[r]) {
                    r++;
                    max = Math.max(max, r - l);
                } else {
                    l++;
                }
            } else {
                if (temp[r - 1] > 0 && temp[r - 2] < 0 || temp[r - 1] < 0 && temp[r - 2] > 0) {
                    r++;
                    max = Math.max(max, r - l);
                } else {
                    l++;
                }
            }
            if (l > r - 1) {
                r = l + 1;
            }
        } while (r < len && l <= r - 1);
        return max;
    }

    // 动态规划
    public static int maxTurbulenceSize2(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        int[] up = new int[len];
        int[] down = new int[len];
        int max = 1;
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int i = 1; i < len; i++) {
            if (arr[i] > arr[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = 1;
            } else if (arr[i] < arr[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = 1;
            } else {
                down[i] = up[i] - 1;
            }
            max = Math.max(up[i], Math.max(down[i], max));
        }
        return max;
    }
}
