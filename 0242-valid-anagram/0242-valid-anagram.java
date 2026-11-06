class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!= t.length()) return false;
        HashMap<Character,Integer> hm1= new HashMap<>();
        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            hm1.put(ch, hm1.getOrDefault(ch,0)+1);
        }
        for (int i=0;i<t.length();i++){
            char ch =t.charAt(i);
            if(!hm1.containsKey(ch)) return false;
            hm1.put(ch,hm1.get(ch)-1);
            if (hm1.get(ch)== 0) {
                hm1.remove(ch);
            }
        }
        return hm1.isEmpty();
    }
}