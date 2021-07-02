package other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 蛇梯棋
 * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
 * <p>
 * 例如，一块 6 x 6 大小的棋盘，编号如下：
 * <p>
 * <p>
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
 * <p>
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 * <p>
 * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
 * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
 * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
 * <p>
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
 * <p>
 * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]]
 * 输出：4
 * 解释：
 * 首先，从方格 1 [第 5 行，第 0 列] 开始。
 * 你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
 * 然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。
 * 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
 * 然后你决定移动到方格 36, 游戏结束。
 * 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= board.length = board[0].length <= 20
 * board[i][j] 介于 1 和 N*N 之间或者等于 -1。
 * 编号为 1 的方格上没有蛇或梯子。
 * 编号为 N*N 的方格上没有蛇或梯子。
 */
public class A0909 {
    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};  // 4
//        int[][] board = {
//                {-1, -1, -1},
//                {-1, 9, 8},
//                {-1, 8, 9}
//        }; // 1
//        int[][] board = {{-1,-1,19,10,-1},{2,-1,-1,6,-1},{-1,17,-1,19,-1},{25,-1,20,-1,-1},{-1,-1,-1,-1,15}};  // 2
        System.out.println(snakesAndLadders(board));
    }

    //题目：一个蛇形棋盘，从1走到结尾，每次只能甩骰子1-6，遇到不是-1的值也就是（蛇或者楼梯）可以跳转到相应值的位置，问最少需要几步到达终点。
    // 展开成一维的
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        if (n == 0) {
            return 0;
        }
        int[] temp = new int[n * n];
        int len = n * n;
        int x = n - 1, y = 0;
        int dir = 1;
        for (int i = 0; i < len; i++) {
            temp[i] = board[x][y] == -1 ? -1 : board[x][y] - 1;
            y = y + dir;
            if (y == n || y < 0) {
                dir = -dir;
                y = y + dir;
                x--;
            }
        }
        if (temp[0] == len - 1) {
            return 0;
        }
        boolean[] visited = new boolean[len];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (visited[poll]) {
                    continue;
                }
                visited[poll] = true;
                for (int j = poll + 1; j <= poll + 6 && j < len; j++) {
                    if (j == len - 1 || temp[j] == len - 1) {
                        return count;
                    }
                    if (temp[j] == -1) {
                        queue.offer(j);
                    } else {
                        queue.offer(temp[j]);
                    }
                }
            }
        }
        return -1;
    }
}
