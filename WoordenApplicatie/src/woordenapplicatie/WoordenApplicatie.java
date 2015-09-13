/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package woordenapplicatie;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author frankcoenen
 */
public class WoordenApplicatie extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/Woorden.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * 
     * @param stringToCount The string of words that needs to be counted
     * @return 
     */
    public String countWords(String stringToCount){
        
        String reVal = "";
        String[] splitWords = stringToCount.split("\n\n|\n|, |,| ");
        reVal += "Totaal aantal woorden:               "+ splitWords.length;
        reVal += "\n";
        reVal += "Aantal verschillende woorden:   "+new HashSet<>(Arrays.asList(splitWords)).size();
        return reVal;
    }
    
    /**
     * 
     * @param stringToOrder The string of words that is going to be ordered;
     * @return 
     */
    public String orderByRevAlphabet(String stringToOrder){
        String[] splitWords = stringToOrder.split("\n\n|\n|, |,| ");
        List<String> sortList = Arrays.asList(splitWords);
        
        Collections.sort(sortList, Collections.reverseOrder());
        
        String reVal = "";
        
        for (String sortList1 : sortList) {
            reVal+= sortList1 + "\n";
                    
        }
        
        return reVal;
    }
    
    /**
     * 
     * @param stringToCount
     * @return 
     */
    public String frequencyString(String stringToCount){
        String[] splitWords = stringToCount.split("\n\n|\n|, |,| ");
        List<String> sortList = Arrays.asList(splitWords);
        
        Map<String, Integer> mp = new HashMap<>();
        for(String item: sortList){
            if (mp.keySet().contains(item)) {
                mp.put(item, mp.get(item)+1);
            }
            else{
                mp.put(item, 1);
            }
        }
        
        String reString = "";
        for (Map.Entry<String, Integer> entrySet : mp.entrySet()) {
            
            reString += entrySet.getKey() + ":\t" + entrySet.getValue() + "\n";            
        }
        
        return reString;
    }
    
}
