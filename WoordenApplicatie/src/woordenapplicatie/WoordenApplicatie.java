/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package woordenapplicatie;

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
        reVal += "Totaal aantal woorden:            "+ splitWords.length;
        reVal += "\n";
        reVal += "Aantal verschillende woorden:     "+new HashSet<>(Arrays.asList(splitWords)).size();
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
        
        Map<String, Integer> mp = new HashMap<>();
        for(String item: splitWords){
            if (mp.keySet().contains(item)) {
                mp.put(item, mp.get(item)+1);
            }
            else{
                mp.put(item, 1);
            }
        }
        
        Map<String, Integer> smp = sortMapByValue(mp);
        
        String reString = "";
        for (Map.Entry<String, Integer> entrySet : smp.entrySet()) {          
            String key = entrySet.getKey() + ":";
            String value = Integer.toString(entrySet.getValue());
            reString += String.format("%-20s%s" + "\n", key, value);           
        }
        
        return reString;
    }
    
    public String concordanceString(String stringToProcess) {
        String[] sentences = stringToProcess.split("\n\n|\n");
        
        Map<String, String> mp = new HashMap<>();
        for(int i = 0; i < sentences.length; i++) {
            String[] splitWords = sentences[i].split(", |,| ");
            for(String item : splitWords) {
                if (!mp.keySet().contains(item)) {
                    mp.put(item, Integer.toString(i+1));
                }
                else {
                    mp.put(item, mp.get(item) + ", " + Integer.toString(i+1));
                }
            }
        }
        
        String reString = "";
        for (Map.Entry<String, String> entrySet : mp.entrySet()) {
            String key = entrySet.getKey() + ":";
            String value = "[" + entrySet.getValue() + "]";
            reString += String.format("%-20s%s"+"\n", key, value);
        }
        
        return reString;
    }
    
    /**
     * inspiratie: http://www.mkyong.com/java/how-to-sort-a-map-in-java/
     * @param map
     * @return 
     */
    private Map sortMapByValue(Map map) {
        //map omzetten naar linkedlist
        List list = new LinkedList(map.entrySet());
        
        //list sorteren met comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
           @Override
           public int compare(Map.Entry<String, Integer> val1, Map.Entry<String, Integer> val2) {
               return val1.getValue().compareTo(val2.getValue());
           }
        });
        
        //gesorteerde lijst terug omzetten naar hashmap
        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<>();
        for(Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
