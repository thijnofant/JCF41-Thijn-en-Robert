/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author Thijn
 */
public class Controller {
    public ObservableList<Medewerker> medewerkers;
    public ObservableList<Afdeling> afdelingen;
    public Afdeling rootAfdeling;
    public TreeView treeView;
    
    public Controller(TreeView treeView){
        this.treeView = treeView;
        
        
        medewerkers = FXCollections.observableArrayList();
        afdelingen = FXCollections.observableArrayList();
        
        afdelingen.addListener(new ListChangeListener(){
            @Override
            public void onChanged(ListChangeListener.Change change){
                System.out.println("Afdelingen aangepast");
                refreshTree();
            }
        });
        
        medewerkers.addListener(new ListChangeListener(){
            @Override
            public void onChanged(ListChangeListener.Change change){
                System.out.println("Medewerkers aangepast");
                refreshTable();
            }
        });
        
        // gebrobeert met lambda maar werkt niet medewerkers.addListener((ListChangeListener c) -> { System.out.println("Medewerkers aangepast"); });
    }
    
    private void refreshTree(){
        TreeView temp = this.treeView;
        
        if (rootAfdeling != null) {
            TreeItem<Afdeling> rootItem = rootAfdeling.getTreeItems();
        
        temp.setRoot(rootItem);
        }
        
        
    }
    
    private void refreshTable(){
        
    }
    
    private void vulDefaults(){
        
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
}
