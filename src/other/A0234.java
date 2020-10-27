package other;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class A0234 {
    public static void main(String[] args) {
        ListNode ln = new ListNode(1);
        ln.next = new ListNode(2);
        ln.next.next = new ListNode(3);
        ln.next.next.next = new ListNode(4);
        ln.next.next.next.next = new ListNode(5);
        ln.next.next.next.next.next = new ListNode(6);
        isPalindrome(ln);
    }
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode quick = head, slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        ListNode tempHead = slow;
        if (quick != null)
            tempHead = slow.next;
        ListNode pre = null, next;
        while (tempHead != null) {
            next = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = next;
        }
        while (pre != null) {
            if (pre.val != head.val)
                return false;
            pre = pre.next;
            head = head.next;
        }
        return true;
    }
}
