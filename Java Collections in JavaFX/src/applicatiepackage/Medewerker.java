/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author robert
 */
public class Medewerker implements Comparable<Medewerker> {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naam;
    private SimpleIntegerProperty loon; // TODO welk type moeten deze velden zijn zodat je ze inline in de table kunt aanpassen? 
    private SimpleStringProperty email;
    private Afdeling afdeling;
    
    public Medewerker(int id, String naam, int loon, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.naam = new SimpleStringProperty(naam);
        this.loon = new SimpleIntegerProperty(loon);
        this.email = new SimpleStringProperty(email);
    }
    
    public int getId() {
        return this.id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getNaam() {
        return this.naam.get();
    }
    
    public void setNaam(String naam) {
        this.naam.set(naam);
    }
    
    public int getLoon() {
        return this.loon.get();
    }
    
    public void setLoon(int loon) {
        this.loon.set(loon);
    }
    
    public String getMail() {
        return this.email.get();
    }
    
    public void setMail(String mail) {
        this.email.set(mail);
    }
    
    public Afdeling getAfdeling() {
        return this.afdeling;
    }
    
    public void setAfdeling(Afdeling afdeling) {
        this.afdeling = afdeling;
    }
    
    @Override
    public int compareTo(Medewerker o) {
        if ( this.equals(o) ) {
            return 0;
        }
        else
        {
            int i = this.naam.get().compareTo(o.getNaam());
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
        Medewerker m = (Medewerker) o;
        return ( this.id == m.id && this.naam.equals(m.naam) &&  this.email.equals(m.email) && this.afdeling.equals(m.afdeling) );
    }    
}
