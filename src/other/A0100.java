package other;

public class A0100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return backtrack(p.right, q.right) && backtrack(p.left, q.left);
    }
    public boolean backtrack(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return backtrack(p.left, q.left) && backtrack(p.right, q.right);
    }
}
