package array;

/**
 * 1比特字符与2比特字符
 * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
 * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
 * 示例 1:
 * 输入:
 * bits = [1, 0, 0]
 * 输出: True
 * 解释:
 * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
 * 示例 2:
 * 输入:
 * bits = [1, 1, 1, 0]
 * 输出: False
 * 解释:
 * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
 */
public class A0717 {
    public static void main(String[] args) {
        int[] bits = {1, 0, 0};
        System.out.println(isOneBitCharacter2(bits));
    }
    public static boolean isOneBitCharacter(int[] bits) {
        int index = 0;
        int len = bits.length;
        while (index < len-1) {
            if (bits[index] == 1)
                index += 2;
            else
                index ++;
        }
        if (index == len-1)
            return true;
        else
            return false;
    }

    // 三种字符, 0, 10, 11
    // 可以得出0一定是字符的结尾, 因此只需要判断从len-2至0中第一次出现0之前是否出现过以及出现过几次1
    public static boolean isOneBitCharacter2(int[] bits) {
        int len = bits.length;
        int index = len - 2;
        while (index >= 0 && bits[index] > 0) index--;
        return (len-2-index) % 2 == 0;
    }
}
