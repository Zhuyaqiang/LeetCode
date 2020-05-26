package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 适龄的朋友
 * 人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。
 * 当满足以下条件时，A 不能给 B（A、B不为同一人）发送好友请求：
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * 否则，A 可以给 B 发送好友请求。
 * 注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。 
 * 求总共会发出多少份好友请求?
 * 示例 1:
 * 输入: [16,16]
 * 输出: 2
 * 解释: 二人可以互发好友申请。
 * 示例 2:
 * 输入: [16,17,18]
 * 输出: 2
 * 解释: 好友请求可产生于 17 -> 16, 18 -> 17.
 * 示例 3:
 * 输入: [20,30,100,110,120]
 * 输出: 3
 * 解释: 好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.
 * 说明:
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 */
public class A0825 {
    public static void main(String[] args) {
//        int[] ages = {16,17,18,20,23,26};
//        for (int age : ages) {
//            System.out.println(binarySearch(ages, age*0.5+7));
//        }
        int []ages = {8,85,24,85,69};
        System.out.println(numFriendRequests3(ages));
    }
    // 暴力法, 超时
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int len = ages.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j)
                    continue;
                if (ages[j] > ages[i])
                    break;
                if (ages[j] > 0.5 * ages[i] + 7 && ages[j] <= ages[i] && (ages[j] <= 100 || ages[i] >= 100))
                    ans ++;
            }
        }
        return ans;
    }
    // 二分查找, 查到符合条件的左边界, 再找符合条件的右边界
    public static int numFriendRequests2(int[] ages) {
        Arrays.sort(ages);
        int len = ages.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (ages[i] * 0.5 + 7 >= ages[i])
                continue;
            int leftIndex = binarySearch(ages, ages[i]*0.5+7);
            int rightIndex = leftIndex;
            while (rightIndex + 1 < len && ages[rightIndex+1] <= ages[i])
                rightIndex++;
            ans += i - leftIndex;
            if (rightIndex > i)
                ans += rightIndex - i;
        }
        return ans;
    }
    public static int binarySearch(int[] ages, double num) {
        int len = ages.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (ages[mid] > num)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    // 桶思想, 120个桶存放该年龄人数, 两层遍历桶, 符合条件的话即桶A人数*桶B人数
    public static int numFriendRequests4(int[] ages) {
        int[] count = new int[121];
        for (int age: ages) count[age]++;

        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                ans += countA * countB;
                System.out.println(ageA + "  " + ageB + "  " + ans);
                if (ageA == ageB) ans -= countA;
            }
        }

        return ans;
    }
    // 桶思想, 哈希表
    public static int numFriendRequests3(int[] ages) {
        Map<Integer, Integer> bucket = new HashMap<>();
        for (int age : ages) {
            bucket.put(age, bucket.getOrDefault(age, 0) + 1);
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> aEntry: bucket.entrySet()) {
            int aAge = aEntry.getKey();
            for (Map.Entry<Integer, Integer> bEntry: bucket.entrySet()) {
                int bAge = bEntry.getKey();
                if (aAge * 0.5 + 7 >= bAge) continue;
                if (aAge < bAge) continue;
                if (aAge < 100 && 100 < bAge) continue;
                int aCount = aEntry.getValue();
                int bCount = bEntry.getValue();
                ans += aCount * bCount;
                if (aAge == bAge) // 减掉自身
                    ans -= aCount;
            }
        }
        return ans;
    }
}
