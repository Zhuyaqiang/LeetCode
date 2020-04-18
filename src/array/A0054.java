package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class A0054 {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> integers = spiralOrder(matrix);
        System.out.println(integers);
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return ans;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dm = {0, 1, 0, -1};
        int[] dn = {1, 0, -1, 0};
        boolean[][] seen = new boolean[m][n];
        int x = 0, y = 0, change = 0;
        for (int i = 0; i < m * n; i++) {
            ans.add(matrix[x][y]);
            seen[x][y] = true;
            int nextX = x + dm[change];
            int nextY = y + dn[change];
            if (0 <= nextX && m > nextX && 0 <= nextY && n > nextY && seen[nextX][nextY] == false) {
                x = nextX;
                y = nextY;
            } else {
                change = (change + 1) % 4;
                x = x + dm[change];
                y = y + dn[change];
            }
        }
        return ans;
    }
}
