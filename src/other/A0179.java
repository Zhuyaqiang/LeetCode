package other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 *
 * 输入：nums = [10]
 * 输出："10"
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class A0179 {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{10, 2}));
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(largestNumber(new int[]{1}));
    }
    public static String largestNumber(int[] nums) {
        Integer[] temp = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp, (o1, o2) -> {
            String one = o1 + "" + o2;
            String two = o2 + "" + o1;
            for (int i = 0; i < one.length(); i++) {
                if (one.charAt(i) > two.charAt(i)) {
                    return -1;
                } else if (one.charAt(i) < two.charAt(i)) {
                    return 1;
                }
            }
            return 0;
        });
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < temp.length && temp[index] == 0) {
            index++;
        }
        for (int i = index; i < temp.length; i++) {
            sb.append(temp[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
