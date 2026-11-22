class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        num =x
        rev =0
        temp=num
        while num>0:
            rem =num%10
            rev =rev*10+rem
            num=num//10
        return rev ==temp