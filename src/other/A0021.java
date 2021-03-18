package other;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class A0021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode ret = new ListNode(0), pre = ret;
        ListNode temp1 = l1, temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                pre.next = temp1;
                pre = temp1;
                temp1 = temp1.next;
            } else {
                pre.next = temp2;
                pre = temp2;
                temp2 = temp2.next;
            }
        }
        if (temp1 != null) {
            pre.next = temp1;
        }
        if (temp2 != null) {
            pre.next = temp2;
        }
        return ret.next;
    }
}
