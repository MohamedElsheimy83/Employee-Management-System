package TP2;

import java.util.ArrayList;

abstract public class Employe {

    private int num;
    private String nom;
    private String titre;
    private Unite affectation;
    private double salaire;
    static int numEmp = 101;

    public Employe(String nom, String titre, Unite affectation) {
        this.num = numEmp;
        this.nom = nom;
        this.titre = titre;
        this.affectation = affectation;
        this.salaire = salaire;
        numEmp++;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Unite getAffectation() {
        return affectation;
    }

    public void setAffectation(Unite affectation) {
        this.affectation = affectation;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

}
