package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词搜索2
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *  
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 *  
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 */
public class A0212 {
    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
        char[][] board1 = {{'a', 'b'}, {'c', 'd'}};
        String[] words1 = {"abcb"};
        System.out.println(findWords(board1, words1));
    }

    static class Node {
        boolean flag;
        Node[] children;

        public Node() {
            flag = false;
            children = new Node[26];
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String word : words) {
            insert(root, word);
        }
        List<String> res = new ArrayList<>();
        int m = board.length;
        if (m == 0) {
            return new ArrayList<>();
        }
        int n = board[0].length;
        if (n == 0) {
            return new ArrayList<>();
        }
        boolean[][] seen = new boolean[m][n];
        Set<String> set = new HashSet<>();
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                recursion2(root, set, new StringBuilder(), i, j, seen, dir, board);
            }
        }
        return new ArrayList<>(set);
    }

    public static void recursion2(Node root, Set<String> res, StringBuilder curr, int x, int y, boolean[][] seen, int[][] dir, char[][] board) {
        char ch = board[x][y];
        if (root.children[ch - 'a'] == null) {
            return;
        }

        seen[x][y] = true;
        curr.append(ch);

        root = root.children[ch - 'a'];
        if (root.flag) {
            res.add(curr.toString());
        }
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !seen[newX][newY]) {
                recursion2(root, res, curr, newX, newY, seen, dir, board);
            }
        }
        curr.delete(curr.length() - 1, curr.length());
        seen[x][y] = false;
    }

    public static void insert(Node root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (root.children[ch - 'a'] == null) {
                root.children[ch - 'a'] = new Node();
            }
            root = root.children[ch - 'a'];
        }
        root.flag = true;
    }

    public static void recursion(Node root, List<String> res, StringBuilder curr, int x, int y, boolean[][] seen, int[][] dir, char[][] board) {
        seen[x][y] = true;
        curr.append(board[x][y]);
        int val = curr.length() == 0 ? 0 : find(root, curr.toString());
        if (val == -1) {
            curr.delete(curr.length() - 1, curr.length());
            seen[x][y] = false;
            return;
        } else {
            if (val == 1) {
                res.add(curr.toString());
            }
            for (int i = 0; i < 4; i++) {
                int newX = x + dir[i][0];
                int newY = y + dir[i][1];
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !seen[newX][newY]) {
                    recursion(root, res, curr, newX, newY, seen, dir, board);
                }
            }
        }
        curr.delete(curr.length() - 1, curr.length());
        seen[x][y] = false;
    }

    public static int find(Node root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (root.children[ch - 'a'] == null) {
                return -1;
            }
            root = root.children[ch - 'a'];
        }
        return root.flag ? 1 : 0;
    }
}
