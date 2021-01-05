package other;

/**
 * 分隔链表
 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 你应当保留两个分区中每个节点的初始相对位置。
 示例：
 输入：head = 1->4->3->2->5->2, x = 3
 输出：1->2->2->4->3->5
 */
public class A0086 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = new ListNode(-5);
        ListNode right = new ListNode(-5);
        ListNode tempLeft = left;
        ListNode tempRight = right;

        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                tempLeft.next = head;
                tempLeft = tempLeft.next;
            } else {
                tempRight.next = head;
                tempRight = tempRight.next;
            }
            head = next;
        }
        tempLeft.next = right.next;
        return left.next;
    }
}
