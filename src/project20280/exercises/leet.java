package project20280.exercises;

import java.util.Arrays;

public class leet {
    public static int removeDuplicates(int[] nums) {
        int[] expectedNums = new int[nums.length];
        int lastNum = Integer.MIN_VALUE;
        int count = 0;

        for(int i = 0;i<nums.length;i++){
            if(nums[i] != lastNum){

                expectedNums[i] = nums[i];
                lastNum = nums[i];
                count++;
            }
        }
        System.out.println(Arrays.toString(expectedNums));
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(removeDuplicates(nums));
    }
}
