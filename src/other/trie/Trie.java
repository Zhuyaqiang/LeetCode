package other.trie;

public class Trie {

    class Node {
        Node[] children;
        boolean flag;
        public Node() {
            children = new Node[26];
            flag = false;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new Node();
            }
            temp = temp.children[index];
            System.out.print(index + " ");
        }
        System.out.println();
        temp.flag = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            temp = temp.children[index];
            if (temp == null) {
                return false;
            }
            System.out.print(index + " ");
        }
        System.out.println();
        return temp.flag;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            temp = temp.children[index];
            if (temp == null) {
                return false;
            }
            System.out.print(index + " ");
        }
        System.out.println();
        return true;
    }
}