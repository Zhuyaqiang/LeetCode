package array;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class A0287 {
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
        System.out.println(findDuplicate2(nums));
    }
    // 排序 哈希表不满足要求
    // 二分查找
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int l = 1, r = len;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            // 统计出现次数小于等于mid
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) {
                    count ++;
                }
            }
            if (count > mid)
                // 重复元素位于[left, mid]
                r = mid;
            else
                // [mid+1, right]
                l = mid + 1;
        }
        return l;
    }
    // 快慢指针
    public static int findDuplicate2(int[] nums) {
        int quick = 0;
        int slow = 0;
        //[3,1,3,4,2]
        // 循环是3-4-2-3-4-2
        //[1,3,4,2,2]
        // 循环是1-3-2-4-2-4-2
        while (true) {
            quick = nums[nums[quick]];
            slow = nums[slow];
            if (quick == slow)
                break;
        }
        // 快慢指针一定会在后面的循环中的某一点相遇,此时快指针走了2n, 慢指针走了n, 设循环圈周长c, 即快指针多走的n是周长的整倍数
        // 假设在进入圈之前长度为m, 则慢指针在圈中走了n-m步,
        // 在快慢指针相遇时, 只需要再从头设置一个指针, 和慢指针同步, 它们相遇的地方一定是入口, 即重复数字, 因为相遇时新指针走了m步,
        // 慢指针在圈中走了n-m+m步(n步, 为圈周长的整倍数), 都是在起点
        int p = 0;
        while (true) {
            slow = nums[slow];
            p = nums[p];
            if (p == slow)
                break;
        }
        return slow;
    }
}
