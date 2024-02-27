package project20280.exercises;

import java.util.Arrays;



public class leet {
    public static int[] removeDuplicates(int[] nums) {

        int count = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i-1] != nums[i]){
                nums[count] = nums[i];
                count++;

            }
        }
        return nums;

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int x = 0;
        int y = 0;
        int j;
        int tempIndex;
        int tempNum;
        while(x < nums1.length && y < n){

            if(nums2[y] < nums1[x] || x > y + m -1){
                for(j = x;j<nums1.length-1;j++){
                    tempIndex = j+1;
                    tempNum = nums1[j+1];
                    nums1[tempIndex] = nums1[j++];
                    if(j+1 != nums1.length){
                        nums1[j+1] = tempNum;
                    }
                }

                nums1[x] = nums2[y];
                x++;
                y++;
            }else{
                x++;
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(removeDuplicates(nums)));

        ////////////////
        int[] nums1 = {5,6,7,0,0,0};
        int[] nums2 = {1,2,3};


        merge(nums1,3,nums2,3);

        String[] strs = {"flower","flow","flight"};


        System.out.println(longestCommonPrefix(strs));
    }
}
