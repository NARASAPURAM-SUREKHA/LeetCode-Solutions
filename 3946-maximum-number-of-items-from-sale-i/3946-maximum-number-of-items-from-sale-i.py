class Solution(object):
    def maximumSaleItems(self, items, budget):
        """
        :type items: List[List[int]]
        :type budget: int
        :rtype: int
        """
        n =len(items)
        dp=[0]*(budget+1)
        for i in range(n):
            fact=items[i][0]
            p=items[i][1]
            fr=0
            for j in range(n):
                if i !=j and items[j][0]%fact==0:
                    fr+=1
            for j in range(budget,p-1,-1):
                dp[j]=max(dp[j],dp[j-p]+fr+1)
            for j in range(p,budget+1):
                dp[j]=max(dp[j],dp[j-p]+1)
        return dp[budget]