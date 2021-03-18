package sort;

/**
 * 删除字符串中所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * 提示：
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 */
public class A1047 {
    // 栈
    public String removeDuplicates2(String S) {
        int len = S.length();
        char[] chs = S.toCharArray();
        int top = -1;
        for (int i = 0; i < len; i++) {
            if (top == -1 || chs[i] != chs[top]) {
                chs[++top] = S.charAt(i);
            } else {
                top--;
            }
        }
        return String.valueOf(chs, 0, top + 1);
    }

    // 贪心
    public String removeDuplicates(String S) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < len - 1; i++) {
                int j = i + 1;
                if (chs[j] == ' ') {
                    while (j < len && chs[j] == ' ') {
                        j++;
                    }
                }
                if (j == len) {
                    break;
                }
                if (chs[i] == chs[j]) {
                    chs[i] = ' ';
                    chs[j] = ' ';
                    flag = true;
                    if (i >= 2) {
                        i -= 2;
                    } else {
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        return String.valueOf(chs).replace(" ", "");
    }
}
