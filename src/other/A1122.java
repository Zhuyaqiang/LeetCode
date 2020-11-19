package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 数组的相对排序
 *
 给你两个数组，arr1 和 arr2，
 arr2 中的元素各不相同
 arr2 中的每个元素都出现在 arr1 中
 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 示例：
 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 输出：[2,2,2,1,4,3,3,9,6,7,19]
 提示：
 arr1.length, arr2.length <= 1000
 0 <= arr1[i], arr2[i] <= 1000
 arr2 中的元素 arr2[i] 各不相同
 arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class A1122 {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> one = new HashMap<>(); // arr1中每个数字出现的次数
        for (int i : arr1) {
            one.put(i, one.getOrDefault(i, 0) + 1);
        }
        // 下标升序
        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            int count = one.get(arr2[i]);
            for (int j = 0; j < count; j++) {
                arr1[index] = arr2[i];
                index ++;
            }
            one.remove(arr2[i]);
        }
        int start = index;
        for (Map.Entry entry : one.entrySet()) {
            int count = (int)entry.getValue();
            int key = (int)entry.getKey();
            for (int j = 0; j < count; j++) {
                arr1[index] = key;
                index ++;
            }
        }
        quickSort(start, arr1.length -1, arr1);
        return arr1;
    }
    public static void quickSort(int l, int r, int[] nums) {
        if (l < r) {
            int i = l, j = r, pivot = nums[l];
            while (i < j) {
                while (i < j && nums[j] >= pivot) j--;
                if (i < j)
                    nums[i++] = nums[j];
                while (i < j && nums[i] < pivot) i++;
                if (i < j)
                    nums[j--] = nums[i];
            }
            nums[i] = pivot;
            quickSort(l, i-1, nums);
            quickSort(i+1, r, nums);
        }
    }
}
