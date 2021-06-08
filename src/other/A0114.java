package other;

/**
 * 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
public class A0114 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        flatten(root);
    }

    public static void flatten(TreeNode root) {
        TreeNode pre = new TreeNode(0);
        backtrack(root, pre, null, null);
        System.out.println('-');
    }

    public static void backtrack(TreeNode root, TreeNode parent, TreeNode preRight, TreeNode grandpa) {
        if (root == null) {
            if (preRight == null){
                return;
            }
            backtrack(preRight, parent, null, null);
        }
        TreeNode right = root.right;
        parent.right = root;
        parent.left = null;
        backtrack(root.left, root, right, parent);
        backtrack(right, root, null, parent);
    }
}
