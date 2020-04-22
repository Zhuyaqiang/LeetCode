package array;

/**
 * 不同路径2
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class A0063 {
    public static void main(String[] args) {
//        int[][] nums = {{0,0}};
        int[][] nums = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles3(nums));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                for (int j = n - 1; j >= i; j--)
                    res[0][j] = 0;
                break;
            }
            res[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                for (int j = m - 1; j >= i; j--)
                    res[j][0] = 0;
                break;
            }
            res[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    res[i][j] = 0;
                else
                    res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] cur = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                for (int j = n - 1; j >= i; j--)
                    pre[j] = 0;
                break;
            }
            pre[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                } else {
                    if (j == 0) {
                        cur[j] = pre[j];
                    } else {
                        cur[j] = cur[j - 1] + pre[j];
                    }
                }
            }
            for (int j = 0; j < n; j++)
                pre[j] = cur[j];
        }
        return pre[n - 1];
    }

    public static int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                for (int j = n - 1; j >= i; j--)
                    res[j] = 0;
                break;
            }
            res[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    res[j] = 0;
                else {
                    if (j != 0)
                        res[j] = res[j - 1] + res[j];
                }
            }
        }
        return res[n - 1];
    }
}
