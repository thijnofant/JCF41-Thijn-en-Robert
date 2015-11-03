/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

/**
 *
 * @author robert
 */
public class Medewerker implements Comparable<Medewerker> {
    private int id;
    private String naam;
    private double loon; // TODO welk type moeten deze velden zijn zodat je ze inline in de table kunt aanpassen? 
    private String email;
    private Afdeling afdeling;
    
    public Medewerker(int id, String naam, double loon, String email) {
        this.naam = naam;
        this.loon = loon;
        this.email = email;
    }
    
    public void pasMedewerkerAan(String naam, double loon, String email) {
        this.naam = naam;
        this.loon = loon;
        this.email = email;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNaam() {
        return this.naam;
    }
    
    public double getLoon() {
        return this.loon;
    }
    
    public String getMail() {
        return this.email;
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
        Medewerker m = (Medewerker) o;
        return ( this.id == m.id && this.naam.equals(m.naam) &&  this.email.equals(m.email) && this.afdeling.equals(m.afdeling) );
    }    
}
