package other;

/**
 * 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class A0024 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head = swapPairs(head);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
    static ListNode pre;
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        head.next = temp.next;
        temp.next = head;
        pre = head;
        backtrack(head.next);
        return temp;
    }
    public static void backtrack(ListNode node) {
        if (node == null || node.next == null)
            return;
        ListNode temp = node.next;
        node.next = temp.next;
        temp.next = node;
        pre.next = temp;
        pre = node;
        backtrack(node.next);
    }
}
