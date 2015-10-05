/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Thijn
 */
public class HuffmanCodering { 
    
    String invoer = "bananen";
    char[] karakters = invoer.toCharArray();
    
    public static void main(String[] args) {
        HuffmanCodering instantie = new HuffmanCodering();
        instantie.initialiseer();
    }
    
    public void initialiseer() {
        ArrayList<CharCount> karakterFreq = tekenFrequentie(karakters); //N
        Collections.sort(karakterFreq, Collections.reverseOrder()); //N*Log(N)
        
        for (CharCount c : karakterFreq) { //N
            System.out.println(c.karakter + ": " + c.aantal);
        }
    }
    
    public ArrayList<CharCount> tekenFrequentie (char[] chars) {
        ArrayList<CharCount> frequenties = new ArrayList<>();
        
        for (char c : chars) {
            boolean toevoegen = false;
            
            if (frequenties.isEmpty())
                    toevoegen = true;
            
            for (CharCount count : frequenties) {
                if (count.karakter  == c) {
                    count.aantal += 1;
                    toevoegen = false;
                    break;
                }
                else {
                    toevoegen = true;
                }
            }
            
            if (toevoegen) 
                frequenties.add(new CharCount(c));
        }
        
        return frequenties;
    }
}
