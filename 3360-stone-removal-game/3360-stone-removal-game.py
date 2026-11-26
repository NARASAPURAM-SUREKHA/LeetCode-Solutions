class Solution(object):
    def canAliceWin(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n < 10:
            return False
        for i in range(10,0,-1):
            if n<i:
                return i%2==1
            n -=i
        return True