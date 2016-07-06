import edu.duke.*;
import java.io.*;

public class AssignmentOne{
    public static String findProtein(String dna) {
        int startATG = dna.indexOf("atg");
        if (startATG == -1) {
            return "404 start codon not found";
        }
        
        //TAG Codon
        int stopTAG = dna.indexOf("tag", startATG+3);
        if ((stopTAG - startATG) % 3 == 0) {
            return dna.substring(startATG, stopTAG+3); //returns the protein
        }
    
        //TGA Codon
        int stopTGA = dna.indexOf("tga", startATG+3);
        if ((stopTGA - startATG) % 3 == 0) {
            return dna.substring(startATG, stopTGA+3); //returns the protein
        }
            
        //TAA Codon
        int stopTAA = dna.indexOf("taa", startATG+3);
        if ((stopTAA - startATG) % 3 == 0) {
            return dna.substring(startATG, stopTAA+3); //returns the protein
        } 
        else {
            return "404 end codon not found"; //meant to be empty string ""
        }
    }
    public String stopCodon(String dna){
        String answer = findProtein(dna);
        int size =answer.length();
        if(size>6){
            return answer.substring(size-3,size); 
        }
        else {
            return "";
        }
    }
    public void testing() {
        String input ="atgccctga";
        //String input2 ="ATGCCCTGA";
        String input2 ="AAATGCCCTAACTAGATTGAAACC";
        
        String callfindP = findProtein(input);
        System.out.println(callfindP);
        
        String stop= stopCodon(input);
        System.out.println(stop);
        
        System.out.println(input2.toLowerCase());
    }
    public void rTest(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString().toLowerCase();
            System.out.println("read "+s.length()+" characters");
            String result = findProtein(s);
			System.out.println("found " + result);
        }
    }
}
