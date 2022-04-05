package TP2;

import java.util.ArrayList;

public class VicePresidence extends Unite {

    ArrayList<Unite> listeUnites;
    Unite parent;

    VicePresidence(int num, String nom) {
        super(num, nom);
        listeUnites = new ArrayList<>();
    }

    public ArrayList<Unite> getListeUnites() {
        return listeUnites;
    }

    public void setListeUnites(ArrayList<Unite> listeUnites) {
        this.listeUnites = listeUnites;
    }

    public Unite getParent() {
        return parent;
    }

    public void setParent(Unite parent) {
        this.parent = parent;
    }

    public void ajouterUnite(Unite unite) {
        listeUnites.add(unite);
    }
}
