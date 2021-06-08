package str;

/**
 * 比较版本号
 * 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *  . 字符不代表小数点，而是用于分隔数字序列。
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 * 示例 1:
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * 示例 4：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 * 示例 5：
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 */
public class A0165 {
    public static void main(String[] args) {
        System.out.println(compareVersion2("0.1", "1.1"));
        System.out.println(compareVersion2("1.0.1", "1"));
        System.out.println(compareVersion2("7.5.2.4", "7.5.3"));
        System.out.println(compareVersion2("1.01", "1.001"));
        System.out.println(compareVersion2("1.0", "1.0.0"));
        System.out.println(compareVersion2("1.1", "1.10"));
    }




    public static int compareVersion2(String version1, String version2) {
        String[] splits1 = version1.split("\\.");
        String[] splits2 = version2.split("\\.");
        int flag = 1;
        if (splits1.length > splits2.length) {
            String[] temp = splits2;
            splits2 = splits1;
            splits1 = temp;
            flag = -1;
        }
        // splits1短
        int index1 = 0, index2 = 0;
        while (index1 < splits1.length && index2 < splits2.length) {
            String v1 = splits1[index1];
            String v2 = splits2[index2];
            int tempIndex1 = 0, tempIndex2 = 0;
            while (tempIndex1 < v1.length() - 1 && v1.charAt(tempIndex1) == '0') {
                tempIndex1++;
            }
            while (tempIndex2 < v2.length() - 1 && v2.charAt(tempIndex2) == '0') {
                tempIndex2++;
            }
            int one = Integer.parseInt(v1.substring(tempIndex1));
            int two = Integer.parseInt(v2.substring(tempIndex2));
            int res = Integer.compare(one, two);
            if (res != 0) {
                return flag * res;
            }
            index1++;
            index2++;
        }
        while (index2 < splits2.length) {
            int tempIndex = 0;
            String v2 = splits2[index2];
            while (tempIndex < v2.length() && v2.charAt(tempIndex) == '0') {
                tempIndex++;
            }
            if (tempIndex != v2.length()) {
                return -1 * flag;
            }
            index2++;
        }
        return 0;
    }
    public static int check(String str1, String str2) {
        int len = str1.length();
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }


    public static int compareVersion(String version1, String version2) {
        String[][] split = new String[2][];
        split[0] = version1.split("\\.");
        split[1] = version2.split("\\.");
        int minLen, big;
        if (split[0].length > split[1].length) {
            minLen = split[1].length;
            big = 0;
        } else {
            minLen = split[0]. length;
            big = 1;
        }
        for (int i = 0; i < minLen; i++) {
            int oneVal = getInt(split[0][i]);
            int twoVal = getInt(split[1][i]);
            if (oneVal > twoVal)
                return 1;
            else if (oneVal < twoVal)
                return -1;
        }
        for (int i = minLen; i < split[big].length; i++) {
            int val = getInt(split[big][i]);
            if (val != 0) {
                return big == 0 ? 1 : -1;
            }
        }
        return 0;
    }
    public static int getInt(String s) {
        int val = 0;
        for (int one = 0; one < s.length(); one++) {
            val = val * 10 + (s.charAt(one) - '0');
        }
        return val;
    }
}
