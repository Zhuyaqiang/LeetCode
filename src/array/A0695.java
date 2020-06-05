package array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class A0695 {
    public static void main(String[] args) {
        int[][] grid = {
                   {0,0,1,0,0,0,0,1,0,0,0,0,0},
                   {0,0,0,0,0,0,0,1,1,1,0,0,0},
                   {0,1,1,0,1,0,0,0,0,0,0,0,0},
                   {0,1,0,0,1,1,0,0,1,0,1,0,0},
                   {0,1,0,0,1,1,0,0,1,1,1,0,0},
                   {0,0,0,0,0,0,0,0,0,0,1,0,0},
                   {0,0,0,0,0,0,0,1,1,1,0,0,0},
                   {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println(maxAreaOfIsland4(grid));
    }
    
    public static int res = 0;
    public static int m;
    public static int n;
    public static int count = 0;
    public static int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};

    public static int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        if (m == 0)
            return 0;
        n = grid[0].length;
        if (n == 0)
            return 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count = 0;
                    backtrack(grid, i, j);
                }
            }
        return res;
    }
    public static void backtrack(int[][] grid, int i, int j) {
        count ++;
        grid[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int newX = i + direction[k][0];
            int newY = j + direction[k][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                backtrack(grid, newX, newY);
            }
        }
        res = Math.max(res, count);
    }


    // 深度优先, 不使用全局变量, 递归
    public static int maxAreaOfIsland2(int[][] grid) {
        int res = 0;
        if (grid.length == 0)
            return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                res = Math.max(res, backtrack2(grid, i, j));
            }
        }
        return res;
    }
    public static int backtrack2(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        int count = 1;
        grid[i][j] = 0;
        count += backtrack2(grid, i+1, j);
        count += backtrack2(grid, i, j+1);
        count += backtrack2(grid, i-1, j);
        count += backtrack2(grid, i, j-1);
        return count;
    }
    // 深度优先, 使用栈
    public static int maxAreaOfIsland3(int[][] grid) {
        int res = 0;
        Deque<int[]> stack = new LinkedList<>();
        int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        if (grid.length == 0)
            return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                stack.push(new int[]{i, j});
                int currRes = 0;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int currI = pop[0];
                    int currJ = pop[1];
                    if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[i].length || grid[currI][currJ] == 0)
                        continue;
                    currRes ++;
                    grid[currI][currJ] = 0;
                    for (int k = 0; k < 4; k++) {
                        stack.push(new int[]{ currI + direction[k][0], currJ + direction[k][1] });
                    }
                }
                res = Math.max(res, currRes);
            }
        }
        return res;
    }
    // 广度优先, 使用队列
    public static int maxAreaOfIsland4(int[][] grid) {
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Deque<int[]> deque = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currRes = 0;
                deque.offer(new int[]{i, j});
                while (!deque.isEmpty()) {
                    int size = deque.size();
                    for (int k = 0; k < size; k++) {
                        int[] poll = deque.poll();
                        int currI = poll[0];
                        int currJ = poll[1];
                        if (currI < 0 || currI >= m || currJ < 0 || currJ >= n || grid[currI][currJ] == 0)
                            continue;
                        grid[currI][currJ] = 0;
                        currRes++;
                        for (int l = 0; l < 4; l++)
                            deque.offer(new int[] {currI + direction[l][0],currJ + direction[l][1]});
                    }
                }
                res = Math.max(res, currRes);
            }
        }
        return res;
    }
}
