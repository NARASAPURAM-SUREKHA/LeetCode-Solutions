class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max_Area = 0;
        for (int i =0;i<=heights.length;i++){
            int currentHeight =(i==heights.length)?0:heights[i];
            while (!stack.isEmpty()&&currentHeight<heights[stack.peek()]) {
                int height=heights[stack.pop()];
                int width=stack.isEmpty()?i:i-stack.peek()- 1;
                max_Area =Math.max(max_Area,height*width);
            }
            stack.push(i);
        }
        return max_Area;
    }
}