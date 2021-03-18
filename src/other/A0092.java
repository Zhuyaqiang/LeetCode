package other;

/**
 * 反转链表
 * 给你单链表的头节点 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *  
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class A0092 {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        reverseBetween(head, 1, 1);
    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        if (head == null) {
            return head;
        }
        ListNode tempHead = head, pre = fakeHead;
        int index = 1;
        for (; index < left; index++) {
            pre = tempHead;
            tempHead = tempHead.next;
        }
        ListNode newRight = pre.next;
        ListNode next = tempHead.next, preNode = null;
        for (; index < right; index++) {
            tempHead.next = preNode;
            preNode = tempHead;
            tempHead = next;
            next = next.next;
        }
        tempHead.next = preNode;
        pre.next = tempHead;
        newRight.next = next;
        return fakeHead.next;
    }
}
