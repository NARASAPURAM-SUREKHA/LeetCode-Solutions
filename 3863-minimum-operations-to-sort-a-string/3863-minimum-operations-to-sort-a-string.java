class Solution {
    public int minOperations(String s){
        int n=s.length();
        boolean sorted =true;
        for (int i =1;i<n;i++){
            if(s.charAt(i -1)>s.charAt(i)){
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        if (n==2)return -1;
        char min_alp='z';
        char max_alp='a';
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            min_alp= (char)Math.min(min_alp,ch);
            max_alp=(char)Math.max(max_alp,ch);
        }
        if(s.charAt(0)==min_alp|| s.charAt(n-1)==max_alp) return 1;
        for(int i=1;i<n-1;i++){
            if(s.charAt(i)==min_alp || s.charAt(i)==max_alp) return 2;
        }
        return 3;
    }
}
