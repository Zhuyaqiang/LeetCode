package other;

/**
 * 反转字符串中的单词3
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class A0557 {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        String res = "";
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            if (i != len-1) {
                res += new StringBuffer(strs[i]).reverse() + " ";
            } else {
                res += new StringBuffer(strs[i]).reverse();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = reverseWords2("1234 567");
        System.out.println(s);
    }
    public static String reverseWords2(String s) {
        StringBuffer res = new StringBuffer();
        int index = 0, len = s.length();
        while (index < len) {
            int end = index;
            while (end < len && s.charAt(end) != ' ')
                end ++;
            int r = end - 1;
            while (r >= index) {
                res.append(s.charAt(r));
                r--;
            }
            if (end != len)
                res.append(" ");
            index = end + 1;
        }
        return res.toString();
    }
}
