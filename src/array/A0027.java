package array;

/**
 * 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class A0027 {
    public static void main(String[] args) {
        int nums[] = {0,1,2,2,3,0,4,2};
        int res = removeElement3(nums, 2);
        System.out.println(res);
        for (int i = 0; i < res; i++) {
            System.out.print(nums[i]);
        }
    }

    // 暴力法
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                int k = i;
                for (int j = k + 1; j < nums.length; j++) {
                    if (nums[j] != val) {
                        nums[k] = nums[j];
                        k++;
                    }
                }
                ans = k;
                break;
            }
        }
        return ans;
    }

    // 从前往后, 相等即跳过, 不等即交换
    public static int removeElement2(int[] nums, int val) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[ans] = nums[i];
                ans++;
            }
        }
        return ans;
    }

    public static int removeElement3(int[] nums, int val) {
        int ans = nums.length;
        int i = 0;
        while (i < ans) {
            if (nums[i] == val) {
                nums[i] = nums[ans-1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
