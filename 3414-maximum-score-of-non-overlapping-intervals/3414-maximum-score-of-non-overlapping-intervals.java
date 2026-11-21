class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a=new int[n][4];
        for (int i=0;i<n;i++) {
            a[i][0] =intervals.get(i).get(0);
            a[i][1] =intervals.get(i).get(1);
            a[i][2] =intervals.get(i).get(2);
            a[i][3]=i;
        }
        Arrays.sort(a,(x,y) ->{
            if (x[0] !=y[0])return x[0]-y[0];
            return x[1]-y[1];
        });
        int[] next = new int[n];
        for (int i = 0; i<n; i++) {
            next[i] = n;
            for (int j = i+1;j < n; j++) {
                if (a[j][0] >a[i][1]) {
                    next[i] =j;
                    break;
                }
            }
        }
        long[][] dp = new long[n + 1][5];
        for (int i = n-1; i>= 0; i--) {
            for (int k=1;k<= 4; k++) {
                long skip= dp[i+ 1][k];
                long take = a[i][2]+dp[next[i]][k - 1];
                dp[i][k] = Math.max(skip, take);
            }
        }
        List<Integer> ans = new ArrayList<>();
        int i = 0, k = 4;
        while (i < n && k > 0) {
            long skip = dp[i + 1][k];
            long take = a[i][2] + dp[next[i]][k -1];
            if (take>skip){
                ans.add(a[i][3]);
                i = next[i];
                k--;
            } else if (take < skip) {
                  i++;
            } else {
                List<Integer> takeList = new ArrayList<>();
                takeList.add(a[i][3]);
                int temp = next[i];
                int tempK = k - 1;
                while (temp < n && tempK > 0) {
                    long s = dp[temp + 1][tempK];
                    long t = a[temp][2] + dp[next[temp]][tempK - 1];
                    if (t >= s) {
                        takeList.add(a[temp][3]);
                        temp = next[temp];
                        tempK--;
                    } else temp++;
                }
                List<Integer> skipList = new ArrayList<>();
                temp = i + 1;
                tempK = k;
                while (temp < n && tempK > 0) {
                    long s = dp[temp + 1][tempK];
                    long t = a[temp][2] + dp[next[temp]][tempK - 1];
                    if (t > s) {
                        skipList.add(a[temp][3]);
                        temp = next[temp];
                        tempK--;
                    } else temp++;
                }
                Collections.sort(takeList);
                Collections.sort(skipList);
                if (isSmaller(takeList, skipList)) {
                    ans.add(a[i][3]);
                    i = next[i];
                    k--;
                } else i++;
            }
        }
        Collections.sort(ans);
        int[] result = new int[ans.size()];
        for (int j = 0; j < ans.size(); j++) {
            result[j] = ans.get(j);
        }
        return result;
    }
        private boolean isSmaller(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());
        for (int i = 0; i < size; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}