package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的第K大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * 2  4  5  8
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *  
 * <p>
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class A0703 {
    public static void main(String[] args) {
//        KthLargest kthLargest = new KthLargest(2, new int[] {0});
//        System.out.println(kthLargest.add(-1));
//        System.out.println(kthLargest.add(1));
//        System.out.println(kthLargest.add(-2));
//        System.out.println(kthLargest.add(-4));
//        System.out.println(kthLargest.add(3));

        // -1 1 1 2 3
        KthLargest kthLargest = new KthLargest(3, new int[] {5, -1});
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(-1));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(4));
    }

    public static class KthLargest {
        private int k;
        private int count = 0;
        private ListNode pre = new ListNode(0);

        public KthLargest(int k, int[] nums) {
            this.k = k;
            Arrays.sort(nums);
            ListNode temp = pre;
            for (int i = nums.length - 1; i >= 0 && i >= nums.length - k; i--) {
                ListNode newNode = new ListNode(nums[i]);
                temp.next = newNode;
                temp = newNode;
                count++;
            }
        }

        public int add(int val) {
            ListNode head = pre;
            if (head.next == null) {
                count++;
                head.next = new ListNode(val);
                return head.next.val;
            }
            while (head.next != null && head.next.val >= val) {
                head = head.next;
            }

            if (head.next == null) {
                if (count < k) {
                    count++;
                    head.next = new ListNode(val);
                    head = head.next;
                }
                return head.val;
            }

            ListNode newNode = new ListNode(val);
            newNode.next = head.next;
            head.next = newNode;
            while (head.next.next != null) {
                head = head.next;
            }
            if (count == k) {
                head.next = null;
            } else {
                count++;
                return head.next.val;
            }
            return head.val;
        }
    }

    public class KthLargest2 {
        private int k;
        private PriorityQueue<Integer> queue = new PriorityQueue<>();
        public KthLargest2(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                queue.add(num);
            }
        }

        public int add(int val) {
            queue.add(val);
            while (queue.size() > k) {
                queue.poll();
            }
            return queue.peek();
        }
    }
}
