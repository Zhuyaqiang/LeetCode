package other;

class Trie {
    class Node {
        boolean exist;
        Node[] children;
        public Node() {
            exist = false;
            children = new Node[26];
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Node now = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (now.children[index] == null) {
                now.children[index] = new Node();
            }
            now = now.children[index];
            if (i == chars.length - 1) {
                now.exist = true;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node now = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (now.children[index] == null) {
                return false;
            }
            now = now.children[index];
        }
        return now.exist;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node now = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (now.children[index] == null) {
                return false;
            }
            now = now.children[index];
        }
        return true;
    }
}

/**
 * Your other.Trie object will be instantiated and called as such:
 * other.Trie obj = new other.Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */