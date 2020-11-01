package array;

import java.util.*;

/**
 * O(1)时间插入, 删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 */
public class A0381 {
    public static void main(String[] args) {
        insert(1);
        remove(1);
    }
    public static Map<Integer, Set<Integer>> map = new HashMap<>();
    public static List<Integer> list = new ArrayList<>();
    public static Random random = new Random();
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public static boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return map.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public static boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        Set<Integer> integers = map.get(val);
        int index = integers.iterator().next();
        integers.remove(index);
        int last = list.get(list.size() - 1);
        list.set(index, last);
        map.get(last).add(index);
        map.get(last).remove(list.size()-1);
        list.remove(list.size()-1);
        if (integers.size() == 0)
            map.remove(val);
        return true;
    }

    /** Get a random element from the collection. */
    public static int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
