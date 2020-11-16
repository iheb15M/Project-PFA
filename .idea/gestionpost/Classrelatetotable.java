/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpost;

/**
 *
 * @author hp
 */
public class Classrelatetotable { 
    String Poste , Action ; 
    Float Salaire ; 

    public Classrelatetotable(String Poste, String Action, Float Salaire) {
        this.Poste = Poste;
        this.Action = Action;
        this.Salaire = Salaire;
    }

    public String getPoste() {
        return Poste;
    }

    public String getAction() {
        return Action;
    }

    public Float getSalaire() {
        return Salaire;
    }
    
    
     
}
