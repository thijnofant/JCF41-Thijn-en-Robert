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
class Afdeling implements Comparable<Afdeling> {
    private String naam;
    private int id;
    public static int LastID = 0;
    private List<Afdeling> afdelingen;
    
    public Afdeling(String naam) {
        this.naam = naam;
        afdelingen = new ArrayList<>();
        this.id = Afdeling.LastID+1;
        Afdeling.LastID = this.id;
    }

    public int getId() {
        return id;
    }
    
    public String getNaam(){
        return this.naam;
    }
    
    public List<Afdeling> getAfdelingen() {
        return afdelingen;
    }
    
    public void addAfdeling(Afdeling afd){
        this.afdelingen.add(afd);
    }
    
    public TreeItem getTreeItems(){
        TreeItem<Afdeling> rootItem = new TreeItem<> (this);
        rootItem.setExpanded(true);
        for (int i = 0; i < afdelingen.size(); i++) {
            TreeItem<Afdeling> item = afdelingen.get(i).getTreeItems();
            rootItem.getChildren().add(item);
        }
        return rootItem;
    }
    
    @Override 
    public String toString() {
        return this.naam;
    }
    
    public void deleteThis(){
        for(Afdeling a : this.afdelingen){
            a.deleteThis();
        }
        this.afdelingen = new ArrayList<>();
    }

    @Override
    public int compareTo(Afdeling o) {
        if (this.naam.equals(o.naam) && this.afdelingen.equals(o.afdelingen) ) {
            return 0;
        }
        else
        {
            int i = this.naam.compareTo(o.getNaam());
            if (i != 0) {
                return i;
            }
            else{
                return -1;
            }
        }
    }
    
    @Override
    public boolean equals(Object o){
        return ( this.naam.equals(((Afdeling)o).naam) && (this.afdelingen.equals(((Afdeling)o).afdelingen) && (this.id == ((Afdeling)o).id )));
    }
}
