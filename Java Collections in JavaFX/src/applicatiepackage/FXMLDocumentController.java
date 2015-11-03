/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Thijn
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TreeView tvAfdelingen;  
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
    private ObservableList<Medewerker> medewerkers;
    private ObservableList<Afdeling> afdelingen;
    private Afdeling rootAfdeling;
    private int medewerkerCount = 0;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvAfdelingen.setEditable(true);
        tvAfdelingen.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue observable, Object oldValue, Object newValue) -> {
                selectedItem = (TreeItem) newValue;
                refreshTable();
        });
        
        medewerkers = FXCollections.observableArrayList();
        afdelingen = FXCollections.observableArrayList();
        populateData();
        
        afdelingen.addListener(new ListChangeListener(){
            @Override
            public void onChanged(ListChangeListener.Change change){
                refreshTree();
            }
        });
        
        medewerkers.addListener(new ListChangeListener(){
            @Override
            public void onChanged(ListChangeListener.Change change){
                refreshTable();
            }
        });
        
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("naam"));
        columnWage.setCellValueFactory(new PropertyValueFactory<>("loon"));
        columnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        
        refreshTree();
    } 
    
    @FXML
    private void handleAddAfdeling(ActionEvent event) {
        System.out.println("You clicked handleAddAfdeling!");
        
        if (selectedItem != null) {
            addAfdeling("Nieuwe Afdeling", selectedItem.getValue().getNaam());
        }
        else{
            addAfdeling("Nieuwe Afdeling", "");
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
    
    private void refreshTree() {
        TreeView temp = this.tvAfdelingen;
        
        if (rootAfdeling != null) {
            TreeItem<Afdeling> rootItem = rootAfdeling.getTreeItems();
        
            temp.setRoot(rootItem);
        }       
    }
    
    private void refreshTable() {
        ObservableList<Medewerker> selectedGroup = FXCollections.observableArrayList();
        for (Medewerker m : medewerkers) { 
            if (m.getAfdeling().equals(selectedItem.getValue())) {
                selectedGroup.add(m);
            }
        }
        tvMedewerkers.setItems(selectedGroup);
    }
    
    public void addAfdeling(String naam, String naamChildOf){
        Afdeling afd = new Afdeling(naam);
        for (Afdeling afdelingen1 : afdelingen) {
            if (afdelingen1.getNaam().equals(naamChildOf)) {
                afdelingen1.addAfdeling(afd);
            }
        }
        afdelingen.add(afd);
        
        if (naamChildOf.equals("")) {
            this.rootAfdeling = afd;
        }
    }
    
    public void addMedewerker(String naam, double loon, String mail, String afdelingNaam) {
        Medewerker med = new Medewerker(getMedewerkerId(), naam, loon, mail);
        for (Afdeling afd : afdelingen) {
            if (afd.getNaam().equals(afdelingNaam)) {
                med.setAfdeling(afd);
            }
        }
        
        medewerkers.add(med);
    }
    
    public void populateData() {
        addAfdeling("Hoofdafdeling", "");
        addAfdeling("Onderafdeling 1", "Hoofdafdeling");
        addAfdeling("Onderafdeling 2", "Hoofdafdeling");
        addAfdeling("Kantoor 1", "Onderafdeling 1");
        addAfdeling("Kantoor 2", "Onderafdeling 1");
        addAfdeling("Kantoor 3", "Onderafdeling 1");
        addAfdeling("Kantoor 4", "Onderafdeling 2");
        addAfdeling("Kantoor 5", "Onderafdeling 2");
        addAfdeling("Kantoor 6", "Onderafdeling 2");
        
        addMedewerker("Directeur", 80000, "dir@mail.nl", "Hoofdafdeling");
        addMedewerker("Manager 1", 65000, "man1@mail.nl", "Onderafdeling 1");
        addMedewerker("Manager 2", 65000, "man2@mail.nl", "Onderafdeling 1");
        addMedewerker("Manager 3", 65000, "man3@mail.nl", "Onderafdeling 2");
        addMedewerker("Manager 4", 65000, "man4@mail.nl", "Onderafdeling 2");
        addMedewerker("Werknemer 1", 40000, "wn1@mail.nl", "Kantoor 1");
        addMedewerker("Werknemer 2", 40000, "wn2@mail.nl", "Kantoor 1");
        addMedewerker("Werknemer 3", 40000, "wn3@mail.nl", "Kantoor 1");
        addMedewerker("Werknemer 4", 40000, "wn4@mail.nl", "Kantoor 2");
        addMedewerker("Werknemer 5", 40000, "wn5@mail.nl", "Kantoor 2");
        addMedewerker("Werknemer 6", 40000, "wn6@mail.nl", "Kantoor 2");
        addMedewerker("Werknemer 7", 40000, "wn7@mail.nl", "Kantoor 3");
        addMedewerker("Werknemer 8", 40000, "wn8@mail.nl", "Kantoor 3");
        addMedewerker("Werknemer 9", 40000, "wn9@mail.nl", "Kantoor 3");
        addMedewerker("Werknemer 10", 40000, "wn10@mail.nl", "Kantoor 4");
        addMedewerker("Werknemer 11", 40000, "wn11@mail.nl", "Kantoor 4");
        addMedewerker("Werknemer 12", 40000, "wn12@mail.nl", "Kantoor 4");
        addMedewerker("Werknemer 13", 40000, "wn13@mail.nl", "Kantoor 5");
        addMedewerker("Werknemer 14", 40000, "wn14@mail.nl", "Kantoor 5");
        addMedewerker("Werknemer 15", 40000, "wn15@mail.nl", "Kantoor 5");
        addMedewerker("Werknemer 16", 40000, "wn16@mail.nl", "Kantoor 6");
        addMedewerker("Werknemer 17", 40000, "wn17@mail.nl", "Kantoor 6");
        addMedewerker("Werknemer 18", 40000, "wn18@mail.nl", "Kantoor 6");
    }
    
    public int getMedewerkerId() {
        medewerkerCount += 1;
        return medewerkerCount;
    }
}
