/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static junit.framework.Assert.*;

/**
 *
 * @author robert
 */

public class WoordenApplicatieTestSuite {
    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testCountWords(){
        woordenapplicatie.WoordenApplicatie temp = new woordenapplicatie.WoordenApplicatie();
        
        String input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        String expected = "Totaal aantal woorden:            15\n" +
                       "Aantal verschillende woorden:     8";
        
        String result = temp.countWords(input);
      
        assertEquals("Tellen van woorden1 is niet goed", expected, result);
        
        
        input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        expected = "Totaal aantal woorden:            30\n" +
                "Aantal verschillende woorden:     8";
        
        result = temp.countWords(input);
      
        assertEquals("Tellen van meer woorden is niet goed", expected, result);
        
        
    }
    
    @Test
    public void testOrderRevAlphabet(){
        woordenapplicatie.WoordenApplicatie temp = new woordenapplicatie.WoordenApplicatie();
        
        String input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        String expected = "vier\nvier\nvan\nvan\nvan\ntwee\ntwee\npapier\nhoedje\ndrie\ndrie\nHoedje\nHoedje\nEen\nEen\n";
        
        String result = temp.orderByRevAlphabet(input);
      
        assertEquals("Sort1 niet goed", expected, result);
        
        
        input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        expected = "vier\nvier\nvier\nvier\nvan\nvan\nvan\nvan\nvan\nvan\ntwee\ntwee\ntwee\ntwee\npapier\n"+
                "papier\nhoedje\nhoedje\ndrie\ndrie\ndrie\ndrie\nHoedje\nHoedje\nHoedje\nHoedje\nEen\nEen\nEen\nEen\n";
        
        result = temp.orderByRevAlphabet(input);
      
        assertEquals("Sort1 niet goed", expected, result);
    }
    
    @Test
    public void testFrequency(){
        woordenapplicatie.WoordenApplicatie temp = new woordenapplicatie.WoordenApplicatie();
        
        String input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        String expected =         
                    "hoedje:             1\n"+
                    "papier:             1\n"+
                    "twee:               2\n"+
                    "drie:               2\n"+
                    "vier:               2\n"+
                    "Hoedje:             2\n"+
                    "Een:                2\n"+
                    "van:                3\n";
        
        String result = temp.frequencyString(input);
      
        System.out.println(expected);
        System.out.println(result);
        assertEquals("Frequentie van woorden1 is niet goed", expected, result);
        
         input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
         expected = "hoedje:             2\npapier:             2\ntwee:               4\ndrie:               4\n"+
                "vier:               4\nHoedje:             4\nEen:                4\nvan:                6\n";
        
         result = temp.frequencyString(input);
      
        assertEquals("Frequentie van woorden2 is niet goed", expected, result);
    }
    
    @Test
    public void testConcordance(){
        woordenapplicatie.WoordenApplicatie temp = new woordenapplicatie.WoordenApplicatie();
        
        String input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier";
        
        String expected = "van:                [2, 2, 4]\nhoedje:             [2]\ntwee:               [1, 3]\ndrie:               [1, 3]\n"+
                "vier:               [1, 3]\nHoedje:             [2, 4]\npapier:             [4]\nEen:                [1, 3]\n";
        
        String result = temp.concordanceString(input);
        
        System.out.println(expected);
        System.out.println(result);
        assertEquals("Concordantie van woorden1 is niet goed", expected, result);
        
        
        input = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n";
        
        expected = "van:                [2, 2, 4, 6, 6, 8]\nhoedje:             [2, 6]\ntwee:               [1, 3, 5, 7]\ndrie:               [1, 3, 5, 7]\n"+
                "vier:               [1, 3, 5, 7]\nHoedje:             [2, 4, 6, 8]\npapier:             [4, 8]\nEen:                [1, 3, 5, 7]\n";
        
        result = temp.concordanceString(input);
      
        assertEquals("Concordantie van meer woorden is niet goed", expected, result);
    }
    

}
