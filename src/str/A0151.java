package str;

/**
 * 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 1：
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class A0151 {
    public static void main(String[] args) {
        System.out.println("|" + reverseWords2("  hello world  ") + "|");
    }
    public static String reverseWords(String s) {
        String[] sp = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sp.length; i++) {
            if (sp[i].length() == 0)
                continue;
            sb = sb.insert(0, " " + sp[i]);
        }
        return sb.substring(1);
    }
    public static String reverseWords2(String s) {
        // 去除无效空格
        StringBuilder sb = new StringBuilder();
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ')
            left ++;
        while (right >= left && s.charAt(right) == ' ')
            right --;
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ')
                sb.append(c);
            else {
                if (sb.charAt(sb.length() - 1) != ' ')
                    sb.append(c);
            }
            left ++;
        }

        // 反转字符串
        int len = sb.length();
        left = 0;
        right = len-1;
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left ++;
            right --;
        }

        // 反转每个单词
        left = 0;
        while (left < len) {
            right = left;
            while (right < len && sb.charAt(right) != ' ')
                right ++;
            int l = left, r = right - 1;
            while (l < r) {
                char temp = sb.charAt(l);
                sb.setCharAt(l, sb.charAt(r));
                sb.setCharAt(r, temp);
                l ++;
                r --;
            }
            left = right + 1;
        }
        return sb.toString();
    }
}
