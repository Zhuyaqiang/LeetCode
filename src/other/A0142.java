package other;

/**
 * 环形链表
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 */
public class A0142 {
    // 快慢指针相遇时, 快指针走的距离为 f,慢指针走的距离为 s, 设环之前的路径长度为 a
    // f = 2s, 设环长度为 c, 快指针比慢指针多走若干圈: f = s + nc
    // 由此得 s = nc  (慢指针走的总距离为 nc, 即慢指针在环中走了 nc-a)
    // 因此多来一个从起点开始走的指针, 当它走到环入口时, 慢指针在环中走了 nc, 即在入口相遇
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        ListNode listNode = detectCycle2(head);
        System.out.println(listNode.val);
    }
    public static ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
