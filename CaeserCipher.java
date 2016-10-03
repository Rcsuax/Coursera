import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input,int key){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//"abcdefghijklmnopqrstuvwxyz";
        
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        
        for(int i = 0; i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
        
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isLowerCase(currChar)){
                    encrypted.setCharAt(i,Character.toLowerCase(newChar));
                }
                else {
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaesar(){
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message,key);
        System.out.println(encrypted);
        
        String decrypted = encrypt(encrypted,26-key);
        System.out.println(decrypted);
    }
}
