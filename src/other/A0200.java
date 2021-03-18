package other;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class A0200 {
    public static void main(String[] args) {
        char[][] grid = {{'1', '0', '1', '1', '0', '1', '1'}};
        System.out.println(numIslands2(grid));
    }

    // 深度优先搜索
    public static int numIslands2(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int max = 0;
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    max++;
                    backtrack(grid, i, j, dir, m, n);
                }
            }
        }
        return max;
    }
    public static void backtrack(char[][] grid, int x, int y, int[][] dir, int m, int n) {
        grid[x][y] = '.';
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == '1') {
                backtrack(grid, newX, newY, dir, m, n);
            }
        }
    }
        // 并查集
    public static int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[] parents = new int[m * n];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '.';
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        union(parents, i * n + j, (i - 1) * n + j);
                    }
                    if (i + 1 < m && grid[i + 1][j] == '1') {
                        union(parents, i * n + j, (i + 1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        union(parents, i * n + j, i * n + j - 1);
                    }
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        union(parents, i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '.') {
                    set.add(find(parents, i * n + j));
                }
            }
        }
        return set.size();
    }

    public static void union(int[] parents, int x, int y) {
        int rootX = find(parents, x);
        int rootY = find(parents, y);
        if (rootX != rootY) {
            parents[rootX] = rootY;
        }
    }

    public static int find(int[] parents, int val) {
        if (parents[val] != val) {
            parents[val] = find(parents, parents[val]);
        }
        return parents[val];
    }
}
