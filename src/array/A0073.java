package array;

import java.util.Arrays;

/**
 * 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 */
public class A0073 {
    // 对于mZero和nZero, 索引表示对应行, 数组对应值为0表示出现了0;
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length; // 行
        int n = matrix[0].length; // 列
        int[] mZero = new int[m]; // 出现0的行
        int[] nZero = new int[n]; // 出现0的列
        Arrays.fill(mZero, 1);
        Arrays.fill(nZero, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    mZero[i] = 0;
                    nZero[j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mZero[i] == 0 || nZero[j] == 0)
                    matrix[i][j] = 0;
            }
        }
    }
    // 将出现0的行或列中的非零元素用范围外的数字代替, 题目中未体现, 仅提供思路
    public static void setZeroes2(int[][] matrix) {
        int minValue = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < m; k++) {
                        if (matrix[k][j] != 0)
                            matrix[k][j] = minValue;
                    }
                    for (int k = 0; k < n; k++) {
                        if (matrix[i][k] != 0)
                            matrix[i][k] = minValue;
                    }
                }
            }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minValue) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    // 通过行第一个元素 和 列第一个元素判断该行列是否置零
    // 这样有一个特殊情况, matrix[0][0]同时表示第0行和第0列
    // 因此使用一个额外变量表示第0列, matrix[0][0]表示第0行是否置零
    public static void setZeroes3(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                flag = false;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 第0行第0列单独计算
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if (matrix[0][0] == 0) {
            for (int i = 0; i < n; i++)
                matrix[0][i] = 0;
        }
        if (!flag)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }
}
