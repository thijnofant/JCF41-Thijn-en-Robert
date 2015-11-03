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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

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
    private TableColumn<Medewerker, Integer> columnWage;
    @FXML
    private TableColumn<Medewerker, String> columnMail;
    
    private TreeItem<Afdeling> selectedItem;
    private ObservableList<Medewerker> medewerkers;
    private ObservableList<Afdeling> afdelingen;
    private Afdeling rootAfdeling;
    private int medewerkerCount = 0;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvMedewerkers.setEditable(true);
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
        
        columnID.setCellValueFactory(new PropertyValueFactory("id"));
        
        columnName.setCellValueFactory(new PropertyValueFactory("naam"));
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(
            new EventHandler<CellEditEvent<Medewerker, String>>() {
                @Override
                public void handle(CellEditEvent<Medewerker, String> t) {
                    ((Medewerker) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setNaam(t.getNewValue());
                }
            }
        );
        columnWage.setCellValueFactory(new PropertyValueFactory("loon"));
        columnWage.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        columnWage.setOnEditCommit(
            new EventHandler<CellEditEvent<Medewerker, Integer>>() {
                @Override
                public void handle(CellEditEvent<Medewerker, Integer> t) {
                    ((Medewerker) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setLoon(t.getNewValue());
                }
            }
        );
        columnMail.setCellValueFactory(new PropertyValueFactory("mail"));
        columnMail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnMail.setOnEditCommit(
            new EventHandler<CellEditEvent<Medewerker, String>>() {
                @Override
                public void handle(CellEditEvent<Medewerker, String> t) {
                    ((Medewerker) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setMail(t.getNewValue());
                }
            }
        );
        
        refreshTree();
    } 
    
    @FXML
    private void handleAddAfdeling(ActionEvent event) {
        System.out.println("You clicked handleAddAfdeling!");
        
        if (selectedItem != null) {
            addAfdeling("Nieuwe Afdeling", selectedItem.getValue());
        }
        else{
            addAfdeling("Nieuwe Afdeling", null);
        }    
    }
    
    @FXML
    private void handleEditAfdeling(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleAddMedewerker(ActionEvent event) {
        System.out.println("You clicked addmedewerker!");
        if (selectedItem.getValue() != null) {
            addMedewerker("NieuweMedewerker", 202020, "new@newmail.nl", selectedItem.getValue());
        }
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
            if (selectedItem != null) {
                if (m.getAfdeling().equals(selectedItem.getValue())) {
                    selectedGroup.add(m);
                }
            }
            
        }
        tvMedewerkers.setItems(selectedGroup);
    }
    
    public Afdeling addAfdeling(String naam, Afdeling parent){
        Afdeling afd = new Afdeling(naam);
        for (Afdeling afdelingen1 : afdelingen) {
            if (afdelingen1.equals(parent)) {
                afdelingen1.addAfdeling(afd);
            }
        }
        afdelingen.add(afd);
        
        if (!(parent != null)) {
            this.rootAfdeling = afd;
        }
        return afd;
    }
    
    public void addMedewerker(String naam, int loon, String mail, Afdeling afdeling) {
        Medewerker med = new Medewerker(getMedewerkerId(), naam, loon, mail);
        for (Afdeling afd : afdelingen) {
            if (afd.equals(afdeling)) {
                med.setAfdeling(afd);
            }
        }
        
        medewerkers.add(med);
    }
    
    public void populateData() {
        Afdeling a = addAfdeling("Hoofdafdeling", null);
        Afdeling b = addAfdeling("Onderafdeling 1", a);
        Afdeling c = addAfdeling("Onderafdeling 2", a);
        Afdeling d = addAfdeling("Kantoor 1", b);
        Afdeling e = addAfdeling("Kantoor 2", b);
        Afdeling f = addAfdeling("Kantoor 3", b);
        Afdeling g = addAfdeling("Kantoor 4", c);
        Afdeling h = addAfdeling("Kantoor 5", c);
        Afdeling i = addAfdeling("Kantoor 6", c);
        
        addMedewerker("Directeur", 80000, "dir@mail.nl", a);
        addMedewerker("Manager 1", 65000, "man1@mail.nl", b);
        addMedewerker("Manager 2", 65000, "man2@mail.nl", b);
        addMedewerker("Manager 3", 65000, "man3@mail.nl", c);
        addMedewerker("Manager 4", 65000, "man4@mail.nl", c);
        addMedewerker("Werknemer 1", 40000, "wn1@mail.nl", d);
        addMedewerker("Werknemer 2", 40000, "wn2@mail.nl", d);
        addMedewerker("Werknemer 3", 40000, "wn3@mail.nl", d);
        addMedewerker("Werknemer 4", 40000, "wn4@mail.nl", d);
        addMedewerker("Werknemer 5", 40000, "wn5@mail.nl", e);
        addMedewerker("Werknemer 6", 40000, "wn6@mail.nl", e);
        addMedewerker("Werknemer 7", 40000, "wn7@mail.nl", f);
        addMedewerker("Werknemer 8", 40000, "wn8@mail.nl", f);
        addMedewerker("Werknemer 9", 40000, "wn9@mail.nl", f);
        addMedewerker("Werknemer 10", 40000, "wn10@mail.nl", g);
        addMedewerker("Werknemer 11", 40000, "wn11@mail.nl", g);
        addMedewerker("Werknemer 12", 40000, "wn12@mail.nl", g);
        addMedewerker("Werknemer 13", 40000, "wn13@mail.nl", h);
        addMedewerker("Werknemer 14", 40000, "wn14@mail.nl", h);
        addMedewerker("Werknemer 15", 40000, "wn15@mail.nl", h);
        addMedewerker("Werknemer 16", 40000, "wn16@mail.nl", i);
        addMedewerker("Werknemer 17", 40000, "wn17@mail.nl", i);
        addMedewerker("Werknemer 18", 40000, "wn18@mail.nl", i);
    }
    
    public int getMedewerkerId() {
        medewerkerCount += 1;
        return medewerkerCount;
    }
}
