package array;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class A0064 {
    public static void main(String[] args) {
        int[][] grid = {{1,2},{1,1}};
        System.out.println(minPathSum2(grid));
    }
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] pre = new int[n], cur = new int[n];
        pre[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            cur[0] = pre[0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                int min = Math.min(cur[j-1], pre[j]);
                cur[j] = min + grid[i][j];
            }
            pre = cur.clone();
        }
        return pre[n-1];
    }
    public static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] a = new int[n];
        a[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            a[i] = a[i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            a[0] = a[0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                int min = Math.min(a[j-1], a[j]);
                a[j] = min + grid[i][j];
            }
        }
        return a[n-1];
    }
}
