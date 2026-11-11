class Solution {
    public boolean isAnagram(String s, String t) {
        
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character , Integer> map2 = new HashMap<>();

        for(int i =0; i<s.length(); i++){
            char ch1 = s.charAt(i);
            map1.put(ch1, map1.getOrDefault(ch1,0)+1);
        }
        for(int j =0; j<t.length(); j++){
            char ch2 = t.charAt(j);
            map2.put(ch2, map2.getOrDefault(ch2,0)+1);
        }
        if(map1.equals(map2)) return true;
        else return false;
    }
}