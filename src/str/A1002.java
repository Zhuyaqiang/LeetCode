package str;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 */
public class A1002 {
    public static void main(String[] args) {
        String[] A = {"bella", "label", "roller"};
        System.out.println(commonChars(A));
    }
    public static List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        int len = A.length;
        if (len == 0)
            return res;
        int[][] count = new int[len][26];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < A[i].length(); j++) {
                count[i][A[i].charAt(j) - 'a'] ++;
            }
        }
        for (int j = 0; j < 26; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                if (count[i][j] == 0) {
                    min = Integer.MAX_VALUE;
                    break;
                }
                min = Math.min(min, count[i][j]);
            }
            if (min == Integer.MAX_VALUE)
                continue;
            for (int i = 0; i < min; i++)
                res.add((char)(j + 'a') + "");
        }
        return res;
    }
}
