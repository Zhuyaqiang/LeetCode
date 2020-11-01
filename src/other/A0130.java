package other;

import java.util.*;

/**
 * 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class A0130 {
    public Set<String> set = new HashSet<>();
    public int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int m;
    public int n;

    public void solve(char[][] board) {
        m = board.length;
        if (m == 0)
            return;
        n = board[0].length;
        if (n == 0)
            return;
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                backtrack(seen, board, i, 0);
            if (board[i][n - 1] == 'O')
                backtrack(seen, board, i, n - 1);
        }
        for (int i = 1; i < n - 1; i++) {
            if (board[0][i] == 'O')
                backtrack(seen, board, 0, i);
            if (board[m - 1][i] == 'O')
                backtrack(seen, board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !set.contains(i + "," + j))
                    board[i][j] = 'X';
            }
        }
    }

    public void backtrack(boolean[][] seen, char[][] board, int i, int j) {
        if (seen[i][j])
            return;
        seen[i][j] = true;
        set.add(i + "," + j);
        for (int k = 0; k < 4; k++) {
            int newI = i + dir[k][0];
            int newJ = j + dir[k][1];
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && board[newI][newJ] == 'O') {
                backtrack(seen, board, newI, newJ);
            }
        }
    }
}
