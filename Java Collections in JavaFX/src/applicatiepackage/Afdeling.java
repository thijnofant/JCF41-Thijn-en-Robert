/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatiepackage;

import java.util.List;

/**
 *
 * @author robert
 */
class Afdeling {
    private String naam;
    private Afdeling superAfdeling;
    private List<Medewerker> medewerkers;
    
    public Afdeling(String naam) {
        this.naam = naam;
    }
    
    public Afdeling(String naam, Afdeling superAfdeling) {
        this.naam = naam;
        this.superAfdeling = superAfdeling;
    }
    
    public void VoegMedewerkerToe(Medewerker medewerker) {
        this.medewerkers.add(medewerker);
    }
}
