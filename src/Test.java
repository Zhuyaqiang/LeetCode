import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-18
 */
public class Test {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(null);
        System.out.println(stack.size());
        System.out.println(stack.pop());
    }
    public static void quickSort(int[] nums) {
        qS(nums, 0, nums.length - 1);
    }
    public static void qS(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int left = l, right = r, pivot = nums[l];
        while (left < right) {
            while (right > left && nums[right] >= pivot) {
                right--;
            }
            if (right > left) {
                nums[left++] = nums[right];
            }
            while (right > left && nums[left] <= pivot) {
                left++;
            }
            if (right > left) {
                nums[right--] = nums[left++];
            }
        }
        nums[right] = pivot;
        qS(nums, l, right - 1);
        qS(nums, right + 1, r);
    }
}
