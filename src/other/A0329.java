package other;

/**
 * 矩阵中的最长递增路径
 *
 *
 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。



 示例 1：


 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 输出：4
 解释：最长递增路径为 [1, 2, 6, 9]。
 示例 2：


 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 输出：4
 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 示例 3：

 输入：matrix = [[1]]
 输出：1


 提示：

 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 200
 0 <= matrix[i][j] <= 231 - 1
 */
public class A0329 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] distance = new int[m][n];
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (distance[i][j] == 0) {
                    getPath(matrix, distance, dir, i, j, m, n);
                }
            }
        }
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, distance[i][j]);
            }
        }
        return res;
    }
    public int getPath(int[][] matrix, int[][] distance, int[][] dir, int x, int y, int m, int n) {
        if (distance[x][y] != 0) {
            return distance[x][y];
        }
        distance[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[x][y] < matrix[newX][newY]) {
                distance[x][y] = Math.max(distance[x][y], getPath(matrix, distance, dir, newX, newY, m, n) + 1);
            }
        }
        return distance[x][y];
    }
}
