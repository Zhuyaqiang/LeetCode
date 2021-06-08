package other;

/**
 * 有效的数独
 */
public class A0036 {
    public static void main(String[] args) {
        String s = "AB2";
        System.out.println(s.toLowerCase());
    }
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowValid = new boolean[9][9];
        boolean[][] colValid = new boolean[9][9];
        boolean[][] gridValid = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (rowValid[i][num - 1]) {
                        return false;
                    }
                    rowValid[i][num - 1] = true;
                    if (colValid[j][num - 1]) {
                        return false;
                    }
                    colValid[j][num - 1] = true;
                    int gridIndex = i / 3 * 3 + j / 3;
                    if (gridValid[gridIndex][num - 1]) {
                        return false;
                    }
                    gridValid[gridIndex][num - 1] = true;
                }
            }
        }
        return true;
    }
}
