package DP;

/**
 * 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * 示例:
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * 说明:
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 */
public class A0304 {

    public static void main(String[] args) {
//        int[][] matrix = {{-4, -5}};
//        int[][] matrix = {{1}, {-7}};
        int[][] matrix = {
  {3, 0, 1, 4, 2},
  {5, 6, 3, 2, 1},
  {1, 2, 0, 1, 5},
  {4, 1, 0, 1, 7},
  {1, 0, 3, 0, 5}
  };
        A0304 a0304 = new A0304(matrix);
//        System.out.println(a0304.sumRegion(0,0,0,0));
//        System.out.println(a0304.sumRegion(0,0,0,1));
//        System.out.println(a0304.sumRegion(0,1,0,1));
//        System.out.println(a0304.sumRegion(0,0,0,0));
//        System.out.println(a0304.sumRegion(1,0,1,0));
//        System.out.println(a0304.sumRegion(0,0,1,0));
        System.out.println(a0304.sumRegion(2, 1, 4, 3));
        System.out.println(a0304.sumRegion(1, 1, 2, 2));
        System.out.println(a0304.sumRegion(1, 2, 2, 4));
    }

    static int[] sum;
    static int[][] nums;
    static int m, n;
    public A0304(int[][] matrix) {
        m = matrix.length;
        if (m == 0)
            sum = new int[1];
        else {
            n = matrix[0].length;
            sum = new int[m * n + 1];
            nums = new int[m][n];
            for (int k = 1; k <= m * n; k++) {
                int i = (k - 1) / n;
                int j = (k - 1) % n;
                sum[k] = sum[k - 1] + matrix[i][j];
                nums[i][j] = matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int i = row1 * n + col1;
        int j = row2 * n + col2;
        int sub1 = 0, sub2 = 0;
        for (int j1 = col2 + 1; j1 < n; j1++) {
            for (int i1 = row1; i1 < row2; i1++) {
                sub1 += nums[i1][j1];
            }
        }
        for (int j1 = 0; j1 <col1; j1++) {
            for (int i1 = row1 + 1; i1 <= row2; i1++) {
                sub2 += nums[i1][j1];
            }
        }
        return sum[j+1] - sum[i] - sub1 - sub2;
    }
}
