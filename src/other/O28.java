package other;

import java.util.Arrays;

/**
 * 找出数组中两个只出现一次的数字
 */
public class O28 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,1,2};
        System.out.println(Arrays.toString(findNumsAppearOnce1(nums)));
    }
    public static int[] findNumsAppearOnce1(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        int i = 1;
        while (true) {
            if ((num & i) != 0)
                break;
            i = i << 1;
        }
        int[] res = new int[2];
        res[0] = res[1] = num;
        for (int j = 0; j < nums.length; j++) {
            if ((nums[j] & i) == 0)
                res[0] = res[0] ^ nums[j];
            else
                res[1] = res[1] ^ nums[j];
        }
        return res;
    }
}
