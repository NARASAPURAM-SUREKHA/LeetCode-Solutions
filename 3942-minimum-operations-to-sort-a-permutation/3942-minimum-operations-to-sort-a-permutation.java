class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        boolean[][] visited = new boolean[2][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,0}); // type, shift, steps
        visited[0][0] =true;
        while(!queue.isEmpty()){
            int[] curr =queue.poll();
            int type=curr[0];
            int shift= curr[1];
            int steps=curr[2];
            if (isSorted(nums, type, shift))  return steps;
            int nextShift =(shift+1)%n;
            if (!visited[type][nextShift]){
                visited[type][nextShift]=true;
                queue.offer(new int[]{type,nextShift,steps+1});
            }
            int nextType = 1-type;
            int reverseShift =(n- shift)%n;
            if (!visited[nextType][reverseShift]) {
                visited[nextType][reverseShift]=true;
                queue.offer(new int[]{ nextType,reverseShift,steps+1});
            }
        }
        return -1;
    }
    private boolean isSorted(int[] nums,int type,int shift) {
        int n= nums.length;
        for(int i =0;i<n;i++){
            int index;
            if (type ==0) index = (i+shift)%n;
            else index=n-1-((i+shift)%n);
            if(nums[index]!=i) return false;
        }
        return true;
    }
}