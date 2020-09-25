package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序遍历和后续遍历序列构造二叉树
 *根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class A0106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        postIndex = postorder.length - 1;
        for (int i = 0; i <= postIndex; i++) {
            map.put(inorder[i], i);
        }
        return backtrack(postorder, map, 0, postIndex);
    }
    int postIndex;
    public TreeNode backtrack(int[] postorder, Map<Integer, Integer> map, int left, int right) {
        if (left > right)
            return null;
        int val = postorder[postIndex];
        TreeNode root = new TreeNode(val);
        postIndex --;
        int index = map.get(val);

        root.right = backtrack(postorder, map, index + 1, right);
        root.left = backtrack(postorder, map, left, index - 1);

        return root;
    }
}
