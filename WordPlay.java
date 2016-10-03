
public class WordPlay {
    
    public Boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        char[] vowelArray = {'a','e','i','o','u'};
        for(char vowel: vowelArray){
           if(ch == vowel){
               return true;
           }
        }
        return false;
    }
    
    public void isVowelTest(){
        char[] charArr = {'a','A','d','D','O','o'};
        for(char vowel : charArr){
            System.out.println(vowel + " " + isVowel(vowel));
        }
    }
    
    public String replaceVowels(String phrase,char ch) {
        char[] chArray = phrase.toCharArray();
        for(int i=0;i < chArray.length;i++){
            if(isVowel(chArray[i])){
                chArray[i] = ch;
            }
        }
        String a = new String(chArray);
        return a;
    }
    
    public void replaceVowelsTest(){
        String test = "Hello world";
        char ch = '*';
        String response = replaceVowels(test,ch);
        System.out.println(response);
    }
}
