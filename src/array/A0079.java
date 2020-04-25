package array;

/**
 * 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class A0079 {
    public static void main(String[] args) {
        char[][] board =
                {
                        {'A'},
                };
        System.out.println(exist(board, "A"));

    }

    private static boolean[][] marked;
    private static int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static int m;
    private static int n;
    private static String words;
    private static char[][] boards;

    public static boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0)
            return false;
        n = board[0].length;
        marked = new boolean[m][n];
        words = word;
        boards = board;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0))
                    return true;
            }
        return false;
    }

    public static boolean dfs(int i, int j, int start) {
        // 返回条件需要注意-----应在剩最后一个字母时判断是否返回
        if (start == words.length() - 1) {
            return boards[i][j] == words.charAt(start);
        }
        if (boards[i][j] == words.charAt(start)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !marked[newX][newY]) {
                    if (dfs(newX, newY, start + 1))
                        return true;
                }
            }
            marked[i][j] = false;
        }
        return false;
    }
}
