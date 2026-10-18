class Solution {
    int MOD = 1000000007;
    long[][] dp;
    public int numOfWays(int[] nums) {
        int n =nums.length;
        dp = new long[n+1][n +1];
        for (int i =0;i<=n;i++) {
            dp[i][0]= 1;
            dp[i][i]=1;
            for (int j=1;j< i;j++){
                dp[i][j] = (dp[i-1][j-1]+dp[i-1][j])%MOD;
            }
        }
        return (int)((solve(nums)-1+MOD)%MOD);
    }
    long solve(int[] nums){
        int n = nums.length;
        if (n<=2)
            return 1;
        int root =nums[0];
        java.util.List<Integer> left = new java.util.ArrayList<>();
        java.util.List<Integer> right = new java.util.ArrayList<>();
        for (int i=1;i<n;i++){
            if (nums[i]<root)left.add(nums[i]);
            else right.add(nums[i]);
        }
        int[] L = left.stream().mapToInt(Integer::intValue).toArray();
        int[] R = right.stream().mapToInt(Integer::intValue).toArray();
        long leftWays = solve(L);
        long rightWays = solve(R);
        long ways = dp[n-1][left.size()];
        ways = (ways*leftWays)%MOD;
        ways = (ways*rightWays)%MOD;
        return ways;
    }
}