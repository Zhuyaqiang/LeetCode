package array;

/**
 * 两数之和2--输入有序数组
 *
 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

 说明:

 返回的下标值（index1 和 index2）不是从零开始的。
 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 示例:

 输入: numbers = [2, 7, 11, 15], target = 9
 输出: [1,2]
 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class A0167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers.length <= 1)
            return res;
        int len = numbers.length;
        for (int i = 0; i < len-1; i++) {
            if (numbers[i] + numbers[i + 1] > target)
                return res;
            if (numbers[i] + numbers[len - 1] < target)
                continue;
            for (int j = i + 1; j < len; j++) {
                if (numbers[i] + numbers[j] == target) {
                    res[0] = i+1;
                    res[1] = j+1;
                    break;
                }
            }
        }
        return res;
    }

    // 双指针, 一次遍历
    public int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];
        int len = numbers.length, l = 0, r = len-1;
        while (l < r) {
            if (target - numbers[l] > numbers[r])
                l++;
            else if (target - numbers[l] < numbers[r])
                r--;
            else {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            }
        }
        return res;
    }
}
