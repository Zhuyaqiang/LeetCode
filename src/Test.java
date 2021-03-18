import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-18
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = {2,2,51,3,6,7,12,7,2};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
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
