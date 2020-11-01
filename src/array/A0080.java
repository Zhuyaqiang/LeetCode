package array;

/**
 * 删除排序数组中的重复项II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class A0080 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int i = removeDuplicates2(nums);
        System.out.println(i);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    // 快慢指针, 慢指针指向返回结果的最后一个数字
    public static int removeDuplicates(int[] nums) {
        int count = 1, dup = 0;
        int len = nums.length;
        int quick, slow = 0;
        for (quick = 1; quick < nums.length; quick++) {
            if (nums[quick] == nums[quick - 1]) {
                if (count >= 2) {
                    dup ++;
                    continue;
                }
                count ++;
            } else {
                count = 1;
            }
            slow++;
            nums[slow] = nums[quick];
        }
        System.out.println("dup: " + dup);
        return len-dup;
    }
    // 快慢指针, 官方解法
    public static int removeDuplicates2(int[] nums) {
        int j = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1])
                count ++;
            else
                count = 1;
            if (count <= 2) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}
