/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.PriorityQueue;

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
        PriorityQueue<Knoop> knopen = new PriorityQueue(tekenFrequentie(karakters)); //N
        Knoop root = genereerHuffman(knopen);
        root.gaNaarBeneden("");
        for (int i = 0; i < karakters.length; i++) {
            System.out.print(root.bitCode(karakters[i]));
        }
        System.out.println(root.decode("0010110110001"));
    }
    
    public Knoop genereerHuffman(PriorityQueue knopen) {
        while (knopen.size() > 1) {
            Knoop rechts = (Knoop)knopen.poll();
            Knoop links = (Knoop)knopen.poll();
            Knoop nieuweKnoop = new Knoop(links,rechts);
            knopen.add(nieuweKnoop);
        }
        
        return (Knoop)knopen.poll();
    }
    
    public ArrayList<Knoop> tekenFrequentie (char[] chars) {
        ArrayList<Knoop> frequenties = new ArrayList<>();
        
        for (char c : chars) {
            boolean toevoegen = false;
            
            if (frequenties.isEmpty())
                    toevoegen = true;
            
            for (Knoop count : frequenties) {
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
                frequenties.add(new Knoop(c));
        }
        
        return frequenties;
    }
}
