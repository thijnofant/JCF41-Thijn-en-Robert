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
    
    public Medewerker(String naam, double loon, String email) {
        this.naam = naam;
        this.loon = loon;
        this.email = email;
    }
    
    public void pasMedewerkerAan(String naam, double loon, String email) {
        this.naam = naam;
        this.loon = loon;
        this.email = email;
    }
}
