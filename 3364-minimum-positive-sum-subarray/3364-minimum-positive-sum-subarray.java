class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int min_sum = Integer.MAX_VALUE;
        for (int i =0;i < n;i++){
            int sum =0;
            for(int j =i;j<n &&j< i+r;j++){
                sum += nums.get(j);
                int length =j-i+1;
                if(length>=l&&length<=r&&sum>0){
                    min_sum =Math.min(min_sum,sum);
                }
            }
        }
        return min_sum ==Integer.MAX_VALUE?-1:min_sum;
    }
}