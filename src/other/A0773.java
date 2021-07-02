package other;

import java.util.*;

/**
 * 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 *
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 示例：
 *
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 *
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 *
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 *
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 *
 * 提示：
 *
 *     board 是一个如上所述的 2 x 3 的数组.
 *     board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 */
public class A0773 {
    public static void main(String[] args) {
//        int[][] board = new int[][] {{4, 1, 2}, {5, 0, 3}};
//        int[][] board = new int[][] {{1, 2, 3}, {4, 0, 5}};
        int[][] board = new int[][] {{3, 2, 4}, {1, 5, 0}};
        System.out.println(slidingPuzzle(board));
    }
    public static int slidingPuzzle(int[][] board) {
        String res = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        if (Arrays.deepToString(board).equals(res)) {
            return 0;
        }
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Set<String> set = new HashSet<>();
        set.add(Arrays.deepToString(board));
        Queue<int[][]> queue = new LinkedList<>();
        queue.offer(board);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[][] poll = queue.poll();
                int[] index = getIndexs(poll);
                for (int j = 0; j < 4; j++) {
                    int newX = index[0] + dir[j][0], newY = index[1] + dir[j][1];
                    if (newX >= 0 && newX < 2 && newY >= 0 && newY < 3) {
                        swap(poll, newX, newY, index[0], index[1]);
                        String key = Arrays.deepToString(poll);
                        if (key.equals(res)) {
                            return count;
                        }
                        if (!set.contains(key)) {
                            set.add(key);
                            int[][] newPoll = new int[2][3];
                            for (int k = 0; k < 2; k++) {
                                newPoll[k] = Arrays.copyOf(poll[k], 3);
                            }
                            queue.offer(newPoll);
                        }
                        swap(poll, newX, newY, index[0], index[1]);
                    }
                }
            }
        }
        return -1;
    }
    public static int[] getIndexs(int[][] board) {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == 0) {
                    return new int[] {x, y};
                }
            }
        }
        return new int[] {0, 0};
    }
    public static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}
