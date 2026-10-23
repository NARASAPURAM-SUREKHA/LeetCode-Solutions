class Solution {
    public int sortPermutation(int[] nums) {
        int sol =-1;
        for(int i =0;i<nums.length;i++){
            if(nums[i]!=i) sol &=nums[i];
        }
        return Math.max(sol,0);
    }
}
