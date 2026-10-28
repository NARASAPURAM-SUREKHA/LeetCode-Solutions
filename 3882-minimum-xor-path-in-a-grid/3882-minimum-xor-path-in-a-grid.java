class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX = 1024;
        boolean[][] dp=new boolean[n][MAX];
        for (int i =0;i<m;i++){
            boolean[][] curr = new boolean[n][MAX];
            for (int j =0;j<n;j++){
                int val =grid[i][j];
                if (i ==0&&j ==0){
                    curr[j][val] =true;
                    continue;
                }
                for(int x=0;x<MAX;x++){
                    if (i>0&&dp[j][x]){
                        curr[j][x^val] =true;
                    }
                    if(j>0&&curr[j- 1][x]) curr[j][x^val] =true;
                }
            }
            dp = curr;
        }
        for(int x=0;x<MAX;x++){
            if(dp[n-1][x])return x;
        }
        return 0;
    }
}