package other;

public class KMP {
    public static void main(String[] args) {
        String pattern = "abbacd#dcabba";
//        String pattern = "aba#aba";
        int[] next = getNext(pattern);
        for (int i : next) {
            System.out.print(i + " ");
        }
        System.out.println();
//        String str = "BBC ABCDAB ABCDABCDABEDE";
//        int[] res = check(str, next, pattern);
//        System.out.println(res[0] + " " + res[1]);
//        if (res[0] != -1)
//            System.out.println(str.substring(res[0], res[1]));
    }

    public static int[] check(String s, int[] next, String pattern) {
        int sIndex = 0, sLen = s.length(), nLen = next.length;
        while (sIndex < sLen) {
            int nIndex = 0;
            int temp = sIndex;
            while (nIndex < nLen) {
                if (s.charAt(temp) == pattern.charAt(nIndex)) {
                    temp++;
                    nIndex++;
                } else {
                    // 遭遇匹配失败, 向后移动的位数=已匹配位数-next[n]
                    sIndex += nIndex - next[nIndex];
                    break;
                }
            }
            if (nIndex == nLen)
                return new int[]{sIndex, sIndex + nIndex - 1};
        }
        return new int[]{-1, -1};
    }

    // next数组的思想就是在匹配失败时找到已匹配部分的后缀和pattern串前缀相同的最长部分
    public static int[] getNext(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] res = new int[len];
        // 第一个置-1
        res[0] = -1;
        for (int i = 1; i < len; i++) {
            // 0. 获取next[n]的值
            // 1. 设a = next[n-1], 如果a为-1则next[n] = 0
            // 2.                 如果a不为-1, 如果s[n-1] == s[a], 则next[n] = a+1
            // 3.                            如果s[n-1] != s[a], a = next[a], 重复1
            int a = res[i - 1];
            while (a != -1) {
                if (chars[a] == chars[i - 1]) {
                    res[i] = a + 1;
                    break;
                } else {
                    a = res[a];
                }
            }
            if (a == -1)
                res[i] = 0;
        }
        return res;
    }
}
