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
public class Medewerker {
    private int id;
    private String naam;
    private double loon;
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
}
