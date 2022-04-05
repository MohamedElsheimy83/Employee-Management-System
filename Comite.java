package TP2;

import java.util.ArrayList;

public class Comite {

    private String nom;
    ArrayList<Employe> membres;

    public Comite() {
        membres = new ArrayList<>();
    }

    public Comite(String nom) {
        this();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Employe> getMembres() {
        return membres;
    }

    public void setMembres(ArrayList<Employe> membres) {
        this.membres = membres;
    }

    public void ajouterEmploye(Employe employe) {
        membres.add(employe);
    }
}
