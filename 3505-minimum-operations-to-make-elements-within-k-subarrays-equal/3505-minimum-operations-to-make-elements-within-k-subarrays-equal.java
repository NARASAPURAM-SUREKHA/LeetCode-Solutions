class Solution {
        class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;
        void addLeft(int val) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSize++;
            leftSum += val;
        }
        void addRight(int val) {
            right.put(val, right.getOrDefault(val, 0) + 1);
            rightSize++;
            rightSum += val;
        }
        void removeLeft(int val) {
            int count = left.get(val);
            if (count == 1)
                left.remove(val);
            else
                left.put(val,count-1);
            leftSize--;
            leftSum-=val;
        }
        void removeRight(int val) {
            int count= right.get(val);
            if (count==1)
                right.remove(val);
            else
                right.put(val,count-1);
            rightSize--;
            rightSum-=val;
        }
        void balance() {
            int target =(leftSize+rightSize+1)/2;
            while (leftSize>target) {
                int val=left.lastKey();
                removeLeft(val);
                addRight(val);
            }
            while (leftSize<target) {
                int val=right.firstKey();
                removeRight(val);
                addLeft(val);
            }
        }
        void add(int val) {
            if (left.isEmpty()|| val<=left.lastKey())
                addLeft(val);
            else
                addRight(val);
            balance();
        }
        void remove(int val){
            if (left.containsKey(val))
                removeLeft(val);
            else
                removeRight(val);
            balance();
        }
        long getCost(){
            long median =left.lastKey();
            return median*leftSize-leftSum+rightSum-median*rightSize;
        }
    }
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n-x+1];
        MedianWindow window = new MedianWindow();
        for (int i =0;i<n;i++){
            if (i>=x)
                window.remove(nums[i-x]);
            window.add(nums[i]);
            if (i>=x-1) cost[i-x+1]=window.getCost();
        }
        long INF = Long.MAX_VALUE/4;
        long[] prev = new long[n+1];
        long[] curr = new long[n+1];
        Arrays.fill(prev,0);
        for (int j = 1;j<= k;j++) {
            Arrays.fill(curr,INF);
            for (int i =1;i <=n;i++) {
                curr[i] = curr[i-1];
                if (i >= x && prev[i-x] != INF){
                    curr[i] = Math.min(curr[i],prev[i-x]+cost[i-x]);
                }
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
}