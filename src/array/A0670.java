package array;

/**
 * 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * 给定数字的范围是 [0, 10的八次方]
 */
public class A0670 {
    public static void main(String[] args) {
        System.out.println(maximumSwap(1993));
    }

    // 先找到非降序的第一个数字, 再找到非降序后面序列中最大的值, 交换该最大值和前面降序排列中第一个小于它的值
    public static int maximumSwap(int num) {
        String numStr = num + "";
        if (numStr.length() <= 1)
            return num;
        char[] numChars = numStr.toCharArray();
        int checkIndex = check(numChars);
        if (checkIndex == -1)
            return num;
        char max = numChars[checkIndex + 1];
        int maxIndex = checkIndex + 1;
        for (int i = checkIndex+1; i < numChars.length; i++) {
            if (numChars[i] >= max) {
                max = numChars[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < checkIndex + 1; i++) {
            if (numChars[i] < max) {
                char temp = numChars[i];
                numChars[i] = max;
                numChars[maxIndex] = temp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(numChars));
    }
    public static int check(char[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] < nums[i + 1])
                return i;
        }
        return -1;
    }
}
