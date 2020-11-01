package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0297 {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
//        System.out.println(rSerialize(root));
        String serialize = serialize(deserialize("[5,2,3,null,null,2,4,3,1]"));
        System.out.println(serialize);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        // ArrayDeque不能存null值
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll == null)
                    ans.append("null,");
                else {
                    ans.append(poll.val).append(",");
                    deque.offer(poll.left);
                    deque.offer(poll.right);
                }
            }
        }
        String str = ans.toString();
        int index = str.length() - 1;
        while (index >= 0) {
            if (str.charAt(index) >= '0' && str.charAt(index) <= '9')
                break;
            index --;
        }
        if (index == -1)
            return "[]";
        else
            return "[" + str.substring(0, index+1) + "]";
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        int len = data.length();
        if (len == 0 || data.equals("[]"))
            return null;
        data = data.substring(1, len - 1);
        if (data.charAt(0) == ',')
            data = data.substring(1);
        String[] split = data.split(",");
        len = split.length;
        boolean[] seen = new boolean[len];
        int[] count = new int[len+1];
        for (int i = 1; i <= len; i++) {
            if (split[i-1].equals("null"))
                count[i] = count[i-1] + 1;
            else
                count[i] = count[i-1];
        }
        TreeNode root = backtrace(0, seen, split, count);
        return root;
    }
    public static TreeNode backtrace(int index, boolean[] seen, String[] strs, int[] counts) {
        TreeNode root = null;
        if (!seen[index]) {
            seen[index] = true;
            String str = strs[index];
            if (!str.equals("null")) {
                int val = Integer.parseInt(str);
                root = new TreeNode(val);
//                int count = 0;
//                for (int i = 0; i < index; i++) {
//                    if (strs[i].equals("null"))
//                        count ++;
//                }
                int count = counts[index + 1];
                int newIndex = 2 * (index + 1) - 1 - count * 2;
                if (newIndex < strs.length) {
                    root.left = backtrace(newIndex, seen, strs, counts);
                    newIndex ++;
                    if (newIndex < strs.length)
                        root.right = backtrace(newIndex, seen, strs, counts);
                }
            }
        }
        return root;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}