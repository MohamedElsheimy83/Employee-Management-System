package TP2;

import java.util.ArrayList;

public class Presidence extends Unite {

    ArrayList<Unite> listeUnites;

    public Presidence(int num, String nom) {
        super(num, nom);
        listeUnites = new ArrayList<>();
    }

    public ArrayList<Unite> getListeUnites() {
        return listeUnites;
    }

    public void setListeUnites(ArrayList<Unite> listeUnites) {
        this.listeUnites = listeUnites;
    }

    public void ajouterUnite(Unite unite) {
        listeUnites.add(unite);
    }
}
