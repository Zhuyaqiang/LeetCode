package str;

import java.util.Arrays;

/**
 * Z字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * l       i
 * e     e s     g
 * e   d   h   n
 * t o     i i
 * c       r
 */
public class A0006 {
    public static void main(String[] args) {
        convert("LEETCODEISHIRING", 3);
    }
    // 暴力法
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int len = s.length();
        int count = numRows + numRows - 2;
        double colCount = (double)len / count;
        int col = (int) Math.ceil(colCount * (numRows - 1));
        char[][] board = new char[numRows][col];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
        int newI = 0, newJ = 0;
        // 0向下, 1向右上
        int dir = 0;
        for (int i = 0; i < len; i++) {
            board[newI][newJ] = s.charAt(i);
            if (dir == 0) {
                if (newI < numRows - 1) {
                    newI ++;
                } else {
                    dir = 1;
                    newI --;
                    newJ ++;
                }
            } else {
                if (newI > 0) {
                    newI --;
                    newJ ++;
                } else {
                    newI ++;
                    dir = 0;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == ' ')
                    continue;
                ans.append(aChar);
            }
        }
        return ans.toString();
    }
}
