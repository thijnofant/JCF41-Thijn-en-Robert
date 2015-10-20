/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;

/**
 *
 * @author robert
 */
class Afdeling {
    private String naam;
    private List<Afdeling> afdelingen;
    
    public Afdeling(String naam) {
        this.naam = naam;
        afdelingen = new ArrayList<>();
    }
    
    public String getNaam(){
        return this.naam;
    }
    
    public void addAfdeling(Afdeling afd){
        this.afdelingen.add(afd);
    }
    
    public TreeItem getTreeItems(){
        TreeItem<Afdeling> rootItem = new TreeItem<> (this);
        rootItem.setExpanded(true);
        for (int i = 1; i < afdelingen.size(); i++) {
            TreeItem<Afdeling> item = afdelingen.get(i).getTreeItems();
            rootItem.getChildren().add(item);
        }
        return rootItem;
    }
    
    
    

}
