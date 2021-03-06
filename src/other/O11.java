package other;

/**
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class O11 {
    public static void main(String[] args) {
        int[] numbers = {2,2,2,0,1};
        System.out.println(rMinArray(numbers));
    }
    public static int rMinArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 只能排除r
            if (numbers[mid] == numbers[r])
                r--;
            // 排除左边
            else if (numbers[mid] > numbers[r])
                l = mid + 1;
            // 排除右边
            else
                r = mid;
        }
        return numbers[l];
    }

    public static int minArray(int[] numbers) {
        int len = numbers.length, l = 0, r = len-1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] == numbers[r])
                r--;
            else if (numbers[mid] > numbers[r])
                l = mid + 1;
            else if (numbers[mid] < numbers[r])
                r = mid;
            else if (numbers[mid] < numbers[l])
                l = mid;
        }
        return numbers[l];
    }
}
