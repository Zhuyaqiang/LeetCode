package other;

import java.util.*;

/**
 * N皇后
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */

/**
 *[
 * ["Q....","..Q..","....Q",".Q...","...Q."],
 * ["Q....","...Q.",".Q...","....Q","..Q.."],
 * [".Q...","...Q.","Q....","..Q..","....Q"],
 * [".Q...","...Q.","Q....","....Q","..Q.."],
 * [".Q...","....Q","..Q..","Q....","...Q."],
 * ["..Q..","Q....","...Q.",".Q...","....Q"],
 * ["..Q..","Q....","....Q",".Q...","...Q."],
 * ["..Q..","....Q","Q....","...Q.",".Q..."],
 * ["..Q..","....Q",".Q...","...Q.","Q...."],
 * ["...Q.","Q....","..Q..","....Q",".Q..."],
 * ["...Q.",".Q...","....Q","Q....","..Q.."],
 * ["...Q.",".Q...","....Q","..Q..","Q...."],
 * ["....Q",".Q...","...Q.","Q....","..Q.."],
 * ["....Q","..Q..","Q....","...Q.",".Q..."]]
 *
 *
 *
 *[
 * ["Q....","..Q..","....Q",".Q...","...Q."],
 * ["Q....","...Q.",".Q...","....Q","..Q.."],
 * [".Q...","...Q.","Q....","..Q..","....Q"],
 * [".Q...","....Q","..Q..","Q....","...Q."],
 * ["..Q..","Q....","...Q.",".Q...","....Q"],
 * ["..Q..","....Q",".Q...","...Q.","Q...."],
 * ["...Q.","Q....","..Q..","....Q",".Q..."],
 * ["...Q.",".Q...","....Q","..Q..","Q...."],
 * ["....Q",".Q...","...Q.","Q....","..Q.."],
 * ["....Q","..Q..","Q....","...Q.",".Q..."]]
 */
public class A0051 {
    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens2(5);
        for (List<String> list : lists) {
            System.out.println(list);
        }
    }

    public static List<List<String>> ans = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new StringBuilder(sb));
        }
        backtrack(list, n, 0, new HashSet<>());
        return ans;
    }

    public static void backtrack(List<StringBuilder> list, int target, int now, Set<Integer> set) {
        if (target == now) {
            List<String> res = new ArrayList<>();
            list.forEach(o -> res.add(o.toString()));
            ans.add(res);
            return;
        }
        StringBuilder stringBuilder = list.get(now);
        for (int i = 0; i < target; i++) {
            if (set.contains(i))
                continue;
            boolean flag = false;
            if (now != 0) {
                for (int j = now - 1; j >= 0; j--) {
                    StringBuilder pre = list.get(j);
                    if (i - (now - j) >= 0 && pre.charAt(i-(now-j)) == 'Q') {
                        flag = true;
                        break;
                    }
                    if (i + (now - j) < target && pre.charAt(i+(now-j)) == 'Q') {
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
            }
            stringBuilder.replace(i, i + 1, "Q");
            set.add(i);
            backtrack(new ArrayList<>(list), target, now + 1, set);
            set.remove(i);
            stringBuilder.replace(i, i + 1, ".");
        }
    }




    public static List<List<String>> solveNQueens2(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] queues = new int[n];
        Arrays.fill(queues, -1);
        backtrack2(ans, n, 0, queues, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return ans;
    }

    public static void backtrack2(List<List<String>> res, int n, int row, int[] queues, Set<Integer> col, Set<Integer> leftUps, Set<Integer> rightUps) {
        if (row == n) {
            res.add(generateQueues(queues));
            return;
        }
        for (int i = 0; i < n; i++ ) {
            if (col.contains(i))
                continue;
            int leftUp = row - i;
            int rightUp = row + i;
            if (rightUps.contains(rightUp))
                continue;
            if (leftUps.contains(leftUp))
                continue;

            queues[row] = i;
            col.add(i);
            rightUps.add(rightUp);
            leftUps.add(leftUp);

            backtrack2(res, n, row + 1, queues, col, leftUps, rightUps);

            col.remove(i);
            rightUps.remove(rightUp);
            leftUps.remove(leftUp);
            queues[row] = -1;
        }
    }

    private static List<String> generateQueues(int[] queues) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < queues.length; i++) {
            char[] row = new char[queues.length];
            Arrays.fill(row, '.');
            row[queues[i]] = 'Q';
            res.add(new String(row));
        }
        return res;
    }
}
