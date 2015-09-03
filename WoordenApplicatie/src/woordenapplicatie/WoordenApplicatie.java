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
     * @param total A bool that decides if it should count the total amount of words or the distinct words
     * @return 
     */
    public String countWords(String stringToCount){
        
        String reVal = "";
        String[] splitWords = stringToCount.split(" ");
        reVal += "Totaal aantal woorden:               "+ splitWords.length;
        reVal += "\n";
        reVal += "Aantal verschillende woorden:   "+new HashSet<String>(Arrays.asList(splitWords)).size();
        return reVal;
    }
    
}
