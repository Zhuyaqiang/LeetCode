package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 */
public class A0767 {
    public static void main(String[] args) {
        System.out.println(reorganizeString2("vvvlo"));
    }
    // 贪心
    public static String reorganizeString(String S) {
        int len = S.length();
        int[][] count = new int[26][2];
        int c = 0;
        for (int i = 0; i < len; i++) {
            char ch = S.charAt(i);
            if (count[ch - 'a'][0] == 0)
                c ++;
            count[ch - 'a'][0] = ch - 'a';
            count[ch - 'a'][1] ++;
        }
        Arrays.sort(count, (o1, o2) -> {
            return o2[1] - o1[1];
        });
        if (count[0][1] > (len + 1) / 2) {
            return "";
        }
        int n = c * count[0][1];
        char[] chars = new char[n];
        Arrays.fill(chars, ',');
        int start = 0, mul = 0, cIndex = 0;
        for (int i = 0; i < len; i++) {
            if (count[cIndex][1] == 0)
                cIndex ++;
            int index = mul * c + start;
            chars[index] = (char) (count[cIndex][0] + 'a');
            count[cIndex][1] --;
            if (index + c < n)
                mul ++;
            else {
                mul = 0;
                start ++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            if (aChar != ',')
                sb.append(aChar);
        }
        return sb.toString();
    }
    // 最大堆, 每次取出最大堆的前两个
    public static String reorganizeString2(String S) {
        int len = S.length(), max = 0;
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            char ch = S.charAt(i);
            count[ch - 'a'] ++;
            max = Math.max(max, count[ch - 'a']);
        }
        if (max > (len + 1) / 2)
            return "";
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return count[o2 - 'a'] - count[o1 - 'a'];
            }
        });
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0)
                queue.offer((char)(i + 'a'));
        }
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char ch1 = queue.poll();
            char ch2 = queue.poll();
            sb.append(ch1);
            sb.append(ch2);
            int index1 = ch1 - 'a', index2 = ch2 - 'a';
            count[index1] --;
            count[index2] --;
            if (count[index1] > 0) {
                queue.offer(ch1);
            }
            if (count[index2] > 0) {
                queue.offer(ch2);
            }
        }
        if (queue.size() > 0)
            sb.append(queue.poll());
        return sb.toString();
    }
}
