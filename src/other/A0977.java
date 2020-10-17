package other;

import java.util.Arrays;

/**
 * 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class A0977 {
    public static void main(String[] args) {
        int[] C = {-2, 0};
        System.out.println(Arrays.toString(sortedSquares(C)));
        int[] A = {-4,-1,0,3,10};
        System.out.println(Arrays.toString(sortedSquares(A)));
        int[] B = {-7,-3,2,3,11};
        System.out.println(Arrays.toString(sortedSquares(B)));
    }
    public static int[] sortedSquares(int[] A) {
        int len = A.length;
        if (len == 0)
            return A;
        if (A[0] >= 0) {
            for (int i = 0; i < len; i++) {
                A[i] = A[i] * A[i];
            }
            return A;
        } else if (A[len-1] <= 0) {
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                res[len-1-i] = A[i] * A[i];
            }
            return res;
        }
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (A[mid] >= 0) {
                r = mid - 1;
            } else
                l = mid + 1;
        }
        l --;
        int[] res = new int[len];
        r = l + 1;
        int index = 0;
        while (l >= 0 && r < len) {
            if (-A[l] < A[r]) {
                res[index++] = A[l] * A[l];
                l--;
            } else {
                res[index++] = A[r] * A[r];
                r++;
            }
        }
        while (l >= 0) {
            res[index ++] = A[l] * A[l];
            l --;
        }
        while (r < len) {
            res[index ++] = A[r] * A[r];
            r ++;
        }
        return res;
    }
}
