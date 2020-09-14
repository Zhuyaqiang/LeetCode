package other;

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
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        if (word.length() == 0)
            return true;
        if (m == 0)
            return false;
        int n = board[0].length;
        if (n == 0)
            return false;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, word, 0, seen, i, j, dir, m, n))
                    return true;
            }
        }
        return false;
    }

    public static boolean backtrack(char[][] board, String word, int index, boolean[][] seen, int i, int j, int[][] dir, int m, int n) {
        if (index == word.length())
            return true;
        if (word.charAt(index) != board[i][j])
            return false;
        if (word.charAt(index) == board[i][j] && index == word.length() -1)
            return true;
        seen[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newX = i + dir[k][0];
            int newY = j + dir[k][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && !seen[newX][newY]) {
                if (backtrack(board, word, index + 1, seen, newX, newY, dir, m, n))
                    return true;
            }
        }
        seen[i][j] = false;
        return false;
    }
}
