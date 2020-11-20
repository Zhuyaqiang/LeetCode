package other;

/**
 * 奇偶链表
 *
 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 示例 1:
 输入: 1->2->3->4->5->NULL
 输出: 1->3->5->2->4->NULL
 示例 2:
 输入: 2->1->3->5->6->4->7->NULL
 输出: 2->3->6->7->1->5->4->NULL
 说明:
 */
public class A0328 {
    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(7);
        oddEvenList(head);
    }
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode start = head;
        ListNode end = head, tempStart = null;
        ListNode pre = head;
        while (start != null) {
            pre = start;
            ListNode next = start.next;
            if (next == null) {
                start.next = tempStart;
                break;
            }
            ListNode nextNext = next.next;
            if (nextNext == null) {
                start.next = tempStart;
                end.next = next;
                break;
            }
            start.next = nextNext;
            if (end == head) {
                next.next = null;
                end = next;
                tempStart = end;
            }
            else {
                next.next = null;
                end.next = next;
                end = end.next;
            }
            start = nextNext;
        }
        if (start == null)
            pre.next = tempStart;
        return head;
    }
}
