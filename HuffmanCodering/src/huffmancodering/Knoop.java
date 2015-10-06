/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

/**
 *
 * @author Robert
 */
public class Knoop implements Comparable<Knoop> {
    public char karakter;
    public int aantal;
    public Knoop links, rechts;
    public String bitcode;
    
    
    public Knoop(char c) {
        this.karakter = c;
        this.aantal = 1;
    }
    
    public Knoop(Knoop links, Knoop rechts) {
        this.links = links;
        this.rechts = rechts;
        this.aantal = links.aantal + rechts.aantal;
        this.karakter = 0;
    }
    
    @Override
    public int compareTo(Knoop c) {
        if (this.aantal > c.aantal) 
            return 1;
        else if (this.aantal < c.aantal) 
            return -1;
        else
            return 0;
    }
    
    public void gaNaarBeneden(String bitcode){
        this.bitcode = bitcode;
        if (this.links != null){
            this.links.gaNaarBeneden(bitcode + "0");
        }
        if (this.rechts != null)
        {
            this.rechts.gaNaarBeneden(bitcode + "1");
        }
        
    }
    
    public String bitCode(char char_par){
        if (this.karakter == char_par) {
            return this.bitcode;
        }
        
        String foundCode = null;
        if (this.links != null){
            foundCode = links.bitCode(char_par);
        }
        if (! (foundCode != null)) {
            if (this.rechts != null){
                foundCode = rechts.bitCode(char_par);
            }
        }
            
        return foundCode;
    }
    
    public String decode(String bitcode) {
        String newbitcode = bitcode;
        String result = "";
        
        String tempString = "";
        while (newbitcode.length() > 0) {
            tempString += newbitcode.substring(0,1);
            
            newbitcode = newbitcode.substring(1);
            
            char returned = searchForChar(tempString,0);
            
            if(returned != 0){
                result+= returned;
                tempString = ""; 
            }
        }
        
        return result;
    }
    
    public char searchForChar(String bitcode,int searchpos){        
        
        if(this.bitcode.equals(bitcode))
        {
            return this.karakter;
        }
        else
        {
            int search = searchpos+1;
            
            String temp = bitcode.substring(searchpos, searchpos+1);

            switch (temp) {
                case "0":
                    if(links != null)
                        return links.searchForChar(bitcode, search);
                case "1":
                    if(rechts != null)
                        return rechts.searchForChar(bitcode, search);
            }
        }
        return 0;
       
    }
}
