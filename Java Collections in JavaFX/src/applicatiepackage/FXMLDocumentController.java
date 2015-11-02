/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author Thijn
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public TreeView tvAfdelingen;  
    @FXML
    private TableView tvMedewerkers;
    @FXML
    private TableColumn<Medewerker, Integer> columnID; 
    @FXML
    private TableColumn<Medewerker, String> columnName;
    @FXML
    private TableColumn<Medewerker, Double> columnWage;
    @FXML
    private TableColumn<Medewerker, String> columnMail;
    
    private TreeItem<Afdeling> selectedItem;
    
    private Controller controller;
    
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        controller = new Controller(tvAfdelingen);
        tvAfdelingen.setEditable(true);
        tvAfdelingen.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue observable, Object oldValue, Object newValue) -> {
                selectedItem = (TreeItem) newValue;
        });
    } 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void handleAddAfdeling(ActionEvent event) {
        System.out.println("You clicked handleAddAfdeling!");
        
        if (selectedItem != null) {
            this.controller.addAfdeling("Nieuwe Afdeling", selectedItem.getValue().getNaam());
        }
        else{
            this.controller.addAfdeling("Nieuwe Afdeling", "");
        }    
    }
    
    @FXML
    private void handleEditAfdeling(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleAddMedewerker(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleEditMedewerker(ActionEvent event) {
        System.out.println("You clicked me!");
    }
   
    
}
