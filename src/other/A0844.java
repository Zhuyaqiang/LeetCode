package other;

/**
 * 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 * 进阶：
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 */
public class A0844 {
    public static void main(String[] args) {
//        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab##", "c#d#"));
//        System.out.println(backspaceCompare("bxj##tw", "bxo#j##tw"));
    }

    public static boolean backspaceCompare(String S, String T) {
        int sLen = S.length(), tLen = T.length(), sIndex = sLen - 1, tIndex = tLen - 1;
        int sCount = 0, tCount = 0;
        while (sIndex >= 0 && tIndex >= 0) {
            char s = S.charAt(sIndex), t = T.charAt(tIndex);
            if (s == '#') {
                sCount++;
                sIndex--;
                continue;
            }
            if (t == '#') {
                tCount++;
                tIndex--;
                continue;
            }
            if (sCount > 0) {
                sIndex--;
                sCount--;
                continue;
            }
            if (tCount > 0) {
                tIndex--;
                tCount--;
                continue;
            }
            if (s != t)
                return false;
            sIndex--;
            tIndex--;
        }
        if (sIndex >= 0) {
            while (true) {
                if (sIndex < 0 || (sCount <= 0 && S.charAt(sIndex) != '#'))
                    break;
                if (S.charAt(sIndex) == '#') {
                    sIndex--;
                    sCount++;
                } else {
                    sIndex--;
                    sCount--;
                }
            }
        }
        if (tIndex >= 0) {
            while (true) {
                if (tIndex < 0 || (tCount <= 0 && T.charAt(tIndex) != '#'))
                    break;
                if (T.charAt(tIndex) == '#') {
                    tIndex--;
                    tCount++;
                } else {
                    tIndex--;
                    tCount--;
                }
            }
        }
        return tIndex < 0 && sIndex < 0;
    }
}
