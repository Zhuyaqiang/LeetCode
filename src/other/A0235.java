package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class A0235 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        rLowestCommonAncestor(root, root.left, root.left.right);
    }
    public static TreeNode rLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        TreeNode temp = root;
        while (temp != p) {
            pPath.add(temp);
            temp = temp.val > p.val ? temp.left : temp.right;
        }
        pPath.add(p);
        temp = root;
        while (temp != q) {
            qPath.add(temp);
            temp = temp.val > q.val ? temp.left : temp.right;
        }
        qPath.add(q);
        temp = root;
        for (int i = 1; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) != qPath.get(i))
                break;
            temp = pPath.get(i);
        }
        return temp;
    }









    // 先找根节点到 p 的路径, 再找根节点到 q 的路径, 两条路径的分叉点就是祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        getPath(root, p, pPath);
        getPath(root, q, qPath);
        int max = Math.min(pPath.size(), qPath.size());
        int index;
        for (index = 0; index < max; index++) {
            if (pPath.get(index) != qPath.get(index))
                break;
        }
        return pPath.get(index - 1);
    }
    public void getPath(TreeNode root, TreeNode node, List<TreeNode> list) {
        list.add(root);
        if (root.val == node.val) {
            return;
        } else {
            if (root.val > node.val)
                getPath(root.left, node, list);
            else
                getPath(root.right, node, list);
        }
    }
}
