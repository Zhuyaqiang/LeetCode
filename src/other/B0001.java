package other;

/**
 * 排序奇升偶降链表
 * https://mp.weixin.qq.com/s/0WVa2wIAeG0nYnVndZiEXQ
 * 给定一个奇数位升序，偶数位降序的链表，将其重新排序（升序）。
 *
 * 输入: 1->8->3->6->5->4->7->2->NULL
 * 输出: 1->2->3->4->5->6->7->8->NULL
 */
public class B0001 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(8);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(2);
        sort(head);
    }
    public static ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode zero = new ListNode(-1), one = new ListNode(-1);
        ListNode zeroPre = zero, onePre = one;
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = null;
            if (count % 2 == 0) {
                zeroPre.next = temp;
                zeroPre = temp;
            } else {
                onePre.next = temp;
                onePre = temp;
            }
            count++;
            temp = next;
        }
        temp = one.next;
        one = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = one;
            one = temp;
            temp = next;
        }
        ListNode hair = new ListNode(-1);
        ListNode pre = hair;
        zero = zero.next;
        while (one != null && zero != null) {
            if (one.val < zero.val) {
                pre.next = one;
                one = one.next;
            } else {
                pre.next = zero;
                zero = zero.next;
            }
            pre = pre.next;
        }
        if (one != null) {
            pre.next = one;
        }
        if (zero != null) {
            pre.next = zero;
        }
        return hair.next;
    }
}
