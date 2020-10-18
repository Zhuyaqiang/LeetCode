package other;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * N皇后2
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class A0052 {
    public static void main(String[] args) {
        int n = 1;
        System.out.println(totalNQueens(n));
        System.out.println(totalNQueens2(n));
    }

    public static int res = 0;

    // 暴力check
    public static int totalNQueens(int n) {
        if (n == 0)
            return 0;
        boolean[][] seen = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            seen[0][i] = true;
            backtrack(0, i, seen, 1, n);
            seen[0][i] = false;
        }
        return res;
    }

    public static void backtrack(int i, int j, boolean[][] seen, int count, int n) {
        if (i == n - 1) {
            res++;
            return;
        }
        for (int k = 0; k < n; k++) {
            if (check(i + 1, k, seen, n)) {
                seen[i + 1][k] = true;
                backtrack(i + 1, k, seen, count + 1, n);
                seen[i + 1][k] = false;
            }
        }
    }

    // 暴力check
    public static boolean check(int i, int j, boolean[][] seen, int n) {
        for (int k = 1; i - k >= 0; k++) {
            if (seen[i - k][j])
                return false;
            if (j - k >= 0 && seen[i - k][j - k])
                return false;
            if (j + k < n && seen[i - k][j + k])
                return false;
        }
        return true;
    }

    // 位于同一条左上向右下直线的点的行坐标-列坐标和相等
    // 位于同一条右上向左下直线的点的行坐标+列坐标和相等
    public static int res2 = 0;
    public static int totalNQueens2(int n) {
        if (n == 0)
            return 0;
        Set<Integer> col = new HashSet<>();
        Set<Integer> rightUps = new HashSet<>();
        Set<Integer> leftUps = new HashSet<>();
        backtrack2(col, rightUps, leftUps, 0, n);
        return res2;
    }

    public static void backtrack2(Set<Integer> col, Set<Integer> rightUps, Set<Integer> leftUps, int row, int n) {
        if (row == n) {
            res2 ++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col.contains(i))
                continue;
            int leftUp = row + i;
            int rightUp = row - i;
            if (leftUps.contains(leftUp) || rightUps.contains(rightUp))
                continue;

            col.add(i);
            leftUps.add(leftUp);
            rightUps.add(rightUp);
            backtrack2(col, rightUps, leftUps, row + 1, n);
            col.remove(i);
            leftUps.remove(leftUp);
            rightUps.remove(rightUp);
        }
    }
}
