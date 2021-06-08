package other;

/**
 * 找出第K大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 106
 * 1 <= k <= m * n
 */
public class A1738 {
    public static void main(String[] args) {
//        int[][] matrix = {{5, 2}, {1, 6}};
//        System.out.println(kthLargestValue2(matrix, 1));
//        System.out.println(kthLargestValue2(matrix, 2));
//        System.out.println(kthLargestValue2(matrix, 3));
//        System.out.println(kthLargestValue2(matrix, 4));
        int[][] matrix = {{10,9,5},{2,0,4},{1,0,9},{3,4,8}};
        System.out.println(kthLargestValue2(matrix, 10));
    }
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] ^ matrix[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] ^ matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] ^ dp[i][j - 1] ^ dp[i - 1][j - 1] ^ matrix[i][j];
            }
        }
        int index = 0;
        int[] newMatrix = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[index] = dp[i][j];
                index++;
            }
        }
        return quickFind(0, m * n - 1, newMatrix, k);
    }


    public static int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int[] newMatrix = new int[m * n];
        newMatrix[0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            newMatrix[i * n] = newMatrix[(i - 1) * n] ^ matrix[i][0];
        }
        for (int j = 1; j < n; j++) {
            newMatrix[j] = newMatrix[j - 1] ^ matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                newMatrix[i * n + j] = newMatrix[(i - 1) * n + j] ^ newMatrix[i * n + j - 1] ^ newMatrix[(i - 1) * n + j - 1] ^ matrix[i][j];
            }
        }
        return quickFind(0, m * n - 1, newMatrix, k);
    }

    public static int quickFind(int l, int r, int[] nums, int target) {
        if (l > r) {
            return -1;
        }
        int start = l, end = r, pivot = nums[l];
        while (start < end) {
            while (end > start && nums[end] <= pivot) {
                end--;
            }
            if (start < end) {
                nums[start] = nums[end];
                start++;
            }
            while (end > start && nums[start] >= pivot) {
                start++;
            }
            if (start < end) {
                nums[end] = nums[start];
                end--;
            }
        }
        nums[start] = pivot;
        if (start - l == target - 1) {
            return nums[start];
        }
        if (start - l > target - 1) {
            return quickFind(l, start - 1, nums, target);
        }
        return quickFind(start + 1, r, nums, target - (start - l + 1));
    }
}
