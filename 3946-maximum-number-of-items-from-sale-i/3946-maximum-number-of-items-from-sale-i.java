class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget+1];
        for(int i =0;i<n;i++){
            int fact=items[i][0];
            int price=items[i][1];
            int free =0;
            for(int j=0;j<n;j++){
                if(i!=j&&items[j][0]%fact==0){
                    free++;
                }
            }
            for(int j=budget;j>=price;j--){
                dp[j]=Math.max(dp[j],dp[j-price]+free+1);
            }
            for(int j=price;j<=budget;j++){
                dp[j]=Math.max(dp[j],dp[j- price]+1);
            }
        }
        return dp[budget];
    }
}