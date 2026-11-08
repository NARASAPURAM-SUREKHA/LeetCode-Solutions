class Solution {
    private boolean isSafe(int row,int col,char[][] board,int n){
        //horizontol
        for(int i=0;i<n;i++ ){
            if(board[row][i]=='Q')return false;
        }
        //vertical
        for(int j=0;j<n;j++ ){
            if(board[j][col]=='Q')return false;
        }
        //left diagonal
        for(int i=row,j=col; i>=0 && j>=0;i--,j--){
            if(board[i][j]=='Q') return false;
        }
        //right diagonal
        for(int i=row,j=col; i>=0 && j<n;i--,j++){
            if(board[i][j]=='Q') return false;
        }
        return true;

    }
    private void backTracking(int row,int n,char[][] board,List<List<String>> res){
        if( row== n){
            List<String> x = new ArrayList<>();
            for(int i=0;i<n;i++){
                x.add( new String (board[i]));

            }
            res.add(x);
            return ; 
        }
        for(int col=0;col<n;col++){
            if( isSafe(row,col,board,n)){
            board[row][col]='Q';
            backTracking(row+1,n,board,res);
            board[row][col]='.';
        }}
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
     
        char[][] board = new char[n][n];
        for(char[] c: board){
            Arrays.fill(c,'.');
        }
        backTracking(0,n,board,res);
        return res;
    }
}