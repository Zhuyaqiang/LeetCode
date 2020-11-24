package other;

/**
 * 排序链表
 *
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 进阶：
 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 输入：head = [4,2,1,3]
 输出：[1,2,3,4]
 输入：head = [-1,5,3,4,0]
 输出：[-1,0,3,4,5]
 示例 3：
 输入：head = []
 输出：[]
 */
public class A0148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        sortList(head);
    }
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode sortedTail = head, unSortedHead = head.next;
        while (unSortedHead != null) {
            if (sortedTail.val <= unSortedHead.val)
                sortedTail = sortedTail.next;
            else {
                ListNode pre = dummyHead;
                while (pre.next.val <= unSortedHead.val)
                    pre = pre.next;
                // unsortedhead插在 pre 和 pre.next之间
                sortedTail.next = unSortedHead.next;
                unSortedHead.next = pre.next;
                pre.next = unSortedHead;
            }
            unSortedHead = sortedTail.next;
        }
        return dummyHead.next;
    }

    public static ListNode sortList2(ListNode head) {
    }
}
