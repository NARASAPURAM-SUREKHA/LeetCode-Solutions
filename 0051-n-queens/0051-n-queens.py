class Solution(object):
    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        def isSafe(row,col,board,n):
            # Horizontal
            for i in range(n):
                if board[row][i]=='Q':
                    return False
            # Vertical
            for j in range(n):
                if board[j][col]=='Q':
                    return False
            # Left diagonal
            i,j =row,col
            while i>=0 and j>=0:
                if board[i][j]=='Q':
                    return False
                i -=1
                j-=1
            # Right diagonal
            i,j= row,col
            while i >=0 and j<n:
                if board[i][j]=='Q':
                    return False
                i -=1
                j+=1
            return True
        def backTracking(row,n,board,res):
            if row==n:
                res.append(["".join(r) for r in board])
                return
            for col in range(n):
                if isSafe(row,col,board,n):
                    board[row][col]='Q'
                    backTracking(row+1,n,board,res)
                    board[row][col]='.'
        res =[]
        board=[['.' for _ in range(n)] for _ in range(n)]
        backTracking(0,n,board,res)
        return res