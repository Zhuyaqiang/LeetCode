package other;

/**
 * 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * 输出: 16
 */
public class A0463 {
    static int res = 0;

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}};
        System.out.println(islandPerimeter2(matrix));
    }

    // 简单遍历， 判断有x个陆地和y个接壤边， 周长=4*x - 2 * y
    public static int islandPerimeter2(int[][] grid) {
        int land = 0, count = 0;
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        if (n == 0)
            return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    if (i < m - 1 && grid[i + 1][j] == 1)
                        count++;
                    if (j < n - 1 && grid[i][j + 1] == 1)
                        count++;
                }
            }
        }
        System.out.println(land + " " + count);
        return land * 4 - count * 2;
    }
    // 深度优先搜索
    public static int islandPerimeter(int[][] grid) {
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        if (n == 0)
            return 0;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!seen[i][j])
                    backtrack(grid, seen, i, j, dir, m, n);
            }
        }
        return res;
    }

    public static void backtrack(int[][] grid, boolean[][] seen, int x, int y, int[][] dir, int m, int n) {
        if (grid[x][y] == 0)
            return;
        int count = 0;
        seen[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                if (grid[newX][newY] == 1)
                    count++;
            }
        }
        res += (4 - count);
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && !seen[newX][newY]) {
                backtrack(grid, seen, newX, newY, dir, m, n);
            }
        }
    }
}
