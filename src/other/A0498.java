package other;

import java.util.Arrays;

/**
 * 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * <p>
 * 解释:
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class A0498 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        if (m == 0) {
            return new int[0];
        }
        int n = mat[0].length;
        if (n == 0) {
            return new int[0];
        }
        int x = 0, y = 0;
        int index = 0;
        int[] res = new int[m * n];
        boolean flag = true;
        while (x < m && y < n) {
            res[index++] = mat[x][y];
            if (flag) {
                if (y == n - 1) {
                    flag = !flag;
                    x++;
                } else {
                    if (x == 0) {
                        flag = !flag;
                    } else {
                        x--;
                    }
                    y++;
                }
            } else {
                if (x == m - 1) {
                    flag = !flag;
                    y++;
                } else {
                    if (y == 0) {
                        flag = !flag;
                    } else {
                        y--;
                    }
                    x++;
                }
            }
        }
        return res;
    }
}
