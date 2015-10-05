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
public class CharCount implements Comparable<CharCount> {
    public char karakter;
    public int aantal;
    
    public CharCount(char c) {
        this.karakter = c;
        this.aantal = 1;
    }
    
    @Override
    public int compareTo(CharCount c) {
        if (this.aantal > c.aantal) 
            return 1;
        else if (this.aantal < c.aantal) 
            return -1;
        else
            return 0;
    }
}
