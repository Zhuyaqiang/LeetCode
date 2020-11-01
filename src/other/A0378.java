package other;

import java.util.Arrays;

/**
 * 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * 示例：
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 */
public class A0378 {
    public static void main(String[] args) {
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(kthSmallest(matrix, 8));
    }

    // 最大值在左上角, 最小值在右下角
    // 第k小, 说明小于等于他的数至少有k个
    public static int rKthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (count(matrix, k, n, mid)) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
    }

    // 判断小于等于mid的个数是否大于等于k
    // 个数大于k: r = mid - 1, 算上个数等于k: r = mid;
    // 个数小于k: l = mid + 1
    private static boolean count(int[][] matrix, int k, int n, int mid) {
        int i = n - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j ++;
            } else {
                i --;
            }
        }
        return count >= k;
    }


    // 临时数组, 排序
    public static  int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int[] temp = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i * n + j] = matrix[i][j];
            }
        }
        Arrays.sort(temp);
        return temp[k-1];
    }

    // 因为最小的值在左上角, 最大的值在右下角
    // 二分查找
    public static  int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n-1][n-1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(matrix, mid, k, n)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    // 查找小于等于mid的值是否大于等于k
    public static boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j ++;
            } else {
                i --;
            }
        }
        return count >= k;
    }
}
