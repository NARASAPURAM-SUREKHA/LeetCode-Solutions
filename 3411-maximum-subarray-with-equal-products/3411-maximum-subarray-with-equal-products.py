class Solution(object):
    def gcd(self, a, b):
        while b:
            a,b=b,a%b
        return a
    def lcm(self,a,b):
        return (a // self.gcd(a,b))*b
    def maxLength(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n =len(nums)
        ans=0
        for i in range(n):
            product=1
            g =0
            l=1
            for j in range(i,n):
                product*=nums[j]
                g =self.gcd(g,nums[j])
                l =self.lcm(l,nums[j])
                if product ==g*l:
                    ans=max(ans,j-i+1)
        return ans