package str;

import java.util.ArrayList;
import java.util.List;

/**
 * 文本左右对齐
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class A0068 {
    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words2 = {"What","must","be","acknowledgment","shall","be"};
        String[] words3 = {"Science","is","what","we","understand","well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"};
        String[] words4 = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        String[] words5 = {"a"};
        List<String> strings = fullJustify(words, 16);
        List<String> strings1 = fullJustify(words2, 16);
        List<String> strings2 = fullJustify(words3, 20);
        List<String> strings3 = fullJustify(words4, 16);
        List<String> strings4 = fullJustify(words5, 2);
        for (String string : strings) {
            System.out.print(string + ": " + string.length() + "||");
        }
        System.out.println();
        for (String string : strings1) {
            System.out.print(string + ": " + string.length() + "||");
        }
        System.out.println();
        for (String string : strings2) {
            System.out.print(string + ": " + string.length() + "||");
        }
        System.out.println();
        for (String string : strings3) {
            System.out.print(string + ": " + string.length() + "||");
        }
        System.out.println();
        for (String string : strings4) {
            System.out.print(string + ": " + string.length() + "||");
        }
        System.out.println();
    }
    public static List<String> fullJustify(String[] words, int maxWidth) {
        int wordsLen = words.length;
        int i = 0;
        List<String> res = new ArrayList<>();
        while (i < wordsLen) {
            int start = i, count = 0, currLen = 0;
            while (i < wordsLen) {
                int len = words[i].length();
                if (currLen + len == maxWidth) {
                    currLen += len;
                    count ++;
                    i++;
                    break;
                } else if (currLen + len < maxWidth) {
                    currLen += len;
                    count ++;
                    i++;
                    // 如果不是最后一个单词, 应该在后面加上空格的长度
                    if (start + count != wordsLen)
                        currLen ++;
                } else {
                    currLen --;
                    break;
                }
            }
            StringBuilder tempRes = new StringBuilder();
            int diff = maxWidth - currLen;
            // 最后一行
            if (start + count == wordsLen) {
                for (int j = start; j < start + count; j++) {
                    tempRes.append(words[j]);
                    if (j != start + count - 1)
                        tempRes.append(" ");
                }
                for (int j = 0; j < diff; j++)
                    tempRes.append(" ");
            } else {
                if (diff == 0) {
                    for (int j = start; j < start + count; j++) {
                        tempRes.append(words[j]);
                        if (j != start + count - 1)
                            tempRes.append(" ");
                    }
                } else {
                    if (count == 1) {
                        tempRes.append(words[start]);
                        for (int j = tempRes.length(); j < maxWidth; j++)
                            tempRes.append(" ");
                    } else {
                        int a = diff % (count - 1);
                        diff = diff / (count - 1);
                        for (int j = start; j < start + count; j++) {
                            tempRes.append(words[j]);
                            if (a != 0) {
                                tempRes.append(" ");
                                a--;
                            }
                            if (j != start + count - 1) {
                                for (int k = 0; k < diff; k++) {
                                    tempRes.append(" ");
                                }
                                tempRes.append(" ");
                            }
                        }
                    }
                }
            }
            res.add(tempRes.toString());
        }
        return res;
    }
}
