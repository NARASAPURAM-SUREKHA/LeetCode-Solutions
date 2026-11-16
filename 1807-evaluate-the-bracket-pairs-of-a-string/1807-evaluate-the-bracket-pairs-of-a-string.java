class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
    for (List<String> pair:knowledge){
           map.put(pair.get(0),pair.get(1));
        }
        StringBuilder ans =new StringBuilder();
        int start=0;
        while(start<s.length()){
            int open =s.indexOf('(',start);
            if (open==-1){
                ans.append(s.substring(start));
                break;
            }
            ans.append(s.substring(start,open));
            int close =s.indexOf(')',open);
            String key =s.substring(open+1,close);
            ans.append(map.getOrDefault(key,"?"));
            start=close+1;
        }
        return ans.toString();
    }
}