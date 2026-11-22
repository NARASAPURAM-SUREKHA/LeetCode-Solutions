class Solution {
    public boolean checkString(String s) {
        boolean found_b = false;

        for (int i =0;i<s.length();i++){
            if(s.charAt(i)=='b'){
                found_b =true;
            }
            if(s.charAt(i) =='a'&&found_b){
                return false;
            }
        }
        return true;
    }
}