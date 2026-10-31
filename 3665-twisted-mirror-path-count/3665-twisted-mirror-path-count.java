class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[n+1][2];
        // Starting position
        dp[1][0] =1;
        dp[1][1] =1;
        for (int i =0;i<m;i++){
            for (int j =0;j<n;j++){
                if (grid[i][j]==0){
                    int total =(int)(((long)dp[j][0]+dp[j+1][1])%MOD);
                    dp[j+1][0] =total;
                    dp[j+1][1]=total;
                } else {
                    int left=dp[j][0];
                    int top=dp[j+1][1];
                    dp[j+1][0]=top;
                    dp[j+1][1]=left;
                }
            }
        }
        return dp[n][0];
    }
}