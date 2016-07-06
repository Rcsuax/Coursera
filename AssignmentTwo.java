import edu.duke.*;
import java.io.*;
public class AssignmentTwo {
   public static void main(String[]args){
        String page = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource urlres = new URLResource(page);
        for(String word : urlres.words()){
            int read = word.indexOf("youtube.com");
            if(read > -1 ) {
                int startOfLink = word.indexOf("\"");
    			int endOfLink = word.indexOf("\"", startOfLink+1);
    			System.out.println(word.substring(startOfLink,endOfLink+1));
            }
        }
    }
}
