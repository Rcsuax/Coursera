import edu.duke.*;
import java.io.*;
import java.util.ArrayList;
public class StoreAllGenes {
    public static int findStopIndex(String dna, int index) {
        
        int[] lengthArray = {0,0,0};

        // Look for TAG Codon
        int stopTAG = dna.indexOf("tag", index + 3);
        if (stopTAG != -1) {
            if ((stopTAG - index) % 3 == 0) {
                lengthArray[0] = dna.substring(index, stopTAG + 3).length();
            }
        }

        // Look for TGA Codon
        int stopTGA = dna.indexOf("tga", index + 3);
        if (stopTGA != -1) {
            if ((stopTGA - index) % 3 == 0) {
                lengthArray[1] = dna.substring(index, stopTGA + 3).length();
            }
        }

        // Look for TAA Codon
        int stopTAA = dna.indexOf("taa", index + 3);
        if (stopTAA != -1) {
            if ((stopTAA - index) % 3 == 0) {
                lengthArray[2] = dna.substring(index, stopTAA + 3).length();
            }
        }

        int smallestGene = Integer.MAX_VALUE;
        for (int i = 0; i < lengthArray.length; i++) {
            if (lengthArray[i] > 0 && lengthArray[i] < smallestGene) {
                smallestGene = lengthArray[i];
            }
        }

        if (smallestGene != Integer.MAX_VALUE) {
            return index + smallestGene - 3;
        }
        
        return -1;        
    }
    public static void printAll(String dna) {
        
        int startCodon=0;
        int stopCodon=0;
        
        while (startCodon != -1) {
            
            // Find the start codon
            startCodon = dna.toLowerCase().indexOf("atg", startCodon);
            // Find the stop codon
            if (startCodon != -1) {

                stopCodon = findStopIndex(dna.toLowerCase(), startCodon);

                if (stopCodon != -1) {
                    System.out.println("Found gene: " + dna.substring(startCodon, stopCodon+3));
                }
                
                if ((stopCodon == -1) && (startCodon < dna.length())) {
                    startCodon = startCodon+3;
                } else {
                    startCodon = stopCodon;
                }
            }
        
        }
        
        System.out.println();
        return;
    }
        public static StorageResource StoreAll(String dna) {
        System.out.println("start");
        int startCodon=0;
        int stopCodon=0;
        StorageResource sr = new StorageResource();
        
        while (startCodon != -1) {
            
            // Find the start codon
            startCodon = dna.toLowerCase().indexOf("atg", startCodon);
            // Find the stop codon
            if (startCodon != -1) {

                stopCodon = findStopIndex(dna.toLowerCase(), startCodon);

                if (stopCodon != -1) {
                    sr.add(dna.substring(startCodon, stopCodon+3));
                }
                
                if ((stopCodon == -1) && (startCodon < dna.length())) {
                    startCodon = startCodon+3;
                } else {
                    startCodon = stopCodon;
                }
            }
        }
        
        System.out.println("Number of genes found: "+ sr.size());
        return sr;
    }
    public static void testStoragefinder(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println("string s: " + s);
        StorageResource str = StoreAll(s);
        System.out.println("testing return value: " + str.size());
        System.out.println("end");
        System.out.println("calling printGenes");
        printGenes(str);
    }
    public static void testFinder(String dna) {
        
        System.out.println("DNA string is: " + dna);
        printAll(dna);
        
        return;
    }
    public static float cgRatio(String dna){
        dna = dna.toUpperCase();  
        int CGCount = 0;
        int dlen = dna.length();
        for(int i=0; i<dlen; i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                CGCount++;
            }
            System.out.println("CGCount " + CGCount + " Length: " + dna.length() + " Ratio: " + (float)CGCount/dlen);
        }
        return (float)CGCount/dlen;
    }
    public static void printGenes(StorageResource sr) {
        int GT60 = 0;
        int CGratioGT = 0;
        for (String item : sr.data()){
            if(item.length() > 60){
                System.out.println("Length: " +item);
                GT60++;
            }
            if (cgRatio(item) > 0.35){
                System.out.println("CGRATIO: "+ item);
                CGratioGT++;
            }
        }
        System.out.println("Number of strings with length greater than 60: " + GT60);
        System.out.println("Number of strings with CGRatio greater than 0.35: "+ CGratioGT);
    }
    public static void main(String[] args) {
        
        //testFinder("ATGAAATGAAAA");
        
        //testFinder("ccatgccctaataaatgtctgtaatgtaga");
    
        //testFinder("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA");
        
        //StoreAllGenes sag = new StoreAllGenes();
        //sag.testStoragefinder();
    }
}
