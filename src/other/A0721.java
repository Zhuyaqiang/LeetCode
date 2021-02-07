package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 账户合并
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John",
 * "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"],
 * ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 提示：
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 */
public class A0721 {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        System.out.println(accountsMerge(accounts));
    }
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> mailToName = new HashMap<>();
        Map<String, Set<String>> nameToMail = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String mail = account.get(i);
                if (mailToName.containsKey(mail)) {
                    mailToName.get(mail).add(name);
                }  else {
                    mailToName.put(mail, new HashSet<>(Collections.singleton(name)));
                }

                if (nameToMail.containsKey(name)) {
                    nameToMail.get(name).add(mail);
                } else {
                    nameToMail.put(name, new HashSet<>(Collections.singleton(mail)));
                }
            }
        }
        Set<String> nameFilter = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        mailToName.forEach((k, v) -> {
            // v是nameSet
            for (String name : v) {
                Set<String> set = new HashSet<>(Collections.singleton(k));
                if (nameFilter.contains(name)) {
                    continue;
                }
                nameFilter.add(name);
                Set<String> mails = nameToMail.get(name);
                for (String mail : mails) {
                    set.add(mail);
                }
                List<String> list = new ArrayList<>(Collections.singletonList(name));
                list.addAll(set);
                res.add(list);
            }
        });
        return res;
    }
}
