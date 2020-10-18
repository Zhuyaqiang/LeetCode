package other;

/**
 * 剑指Offer
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 */
public class J01 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,2,5,6,1,6};
        findNumsAppearOne(nums);
    }
    public static void findNumsAppearOne(int[] nums){
        int len = nums.length;
        int temp = 0;
        // 两个相同的数异或结果为0, 因此temp结果为两个不同的数字的异或结果
        for (int i = 0; i < len; i++) {
            temp = temp ^ nums[i];
        }
        int index = 1;
        // 找出两个不同数字中第一个不同的位数
        for (; true; index = index << 1) {
            if ((temp & index) != 0)
                break;
        }
        int one = 0, two = 0;
        for (int i = 0; i < len; i++) {
            if ((nums[i] & index) != 0)
                one = one ^ nums[i];
            else
                two = two ^ nums[i];
        }
        System.out.println(one);
        System.out.println(two);
    }
}
