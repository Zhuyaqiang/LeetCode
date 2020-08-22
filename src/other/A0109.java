package other;

/**
 * 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class A0109 {
    public TreeNode sortedListToBST(ListNode head) {
        return backtrakc(head, null);
    }
    // 时间复杂度 O(nlogn), logn 次递归, 每次递归 n.2次指针的移动
    // 左闭右开
    public TreeNode backtrakc(ListNode left, ListNode right) {
        if (left == right)
            return null;
        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.right = backtrakc(mid.next, right);
        root.left = backtrakc(left, mid);
        return root;
    }
    public ListNode getMid(ListNode left, ListNode right) {
        ListNode slow = left, quick = left;
        while (quick != right && quick.next != right) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    // 递归, 中序遍历
    // 左闭右闭, 获取长度是为了获得递归终止条件
    public ListNode globalHead;
    public TreeNode sortedListToBST2(ListNode head) {
        globalHead = head;
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length ++;
            temp = temp.next;
        }
        return backtrack(0, length-1);
    }
    public TreeNode backtrack(int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(0);
        root.left = backtrack(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = backtrack(mid + 1, right);
        return root;
    }
}
