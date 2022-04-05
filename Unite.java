package TP2;

import java.util.ArrayList;

abstract public class Unite {

    private int num;
    private String nom;
    private Employe responsable;
    ArrayList<Employe> employes;
    private Comite comite;

    Unite(int num, String nom) {
        this.num = num;
        this.nom = nom;
        employes = new ArrayList<>();
    }

    Unite(int num, String nom, Employe responsable, Comite comite) {
        this(num, nom);
        this.num = num;
        this.nom = nom;
        this.responsable = responsable;
        this.comite = comite;
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

    public Employe getResponsable() {
        return responsable;
    }

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(ArrayList<Employe> employes) {
        this.employes = employes;
    }

    public Comite getComite() {
        return comite;
    }

    public void setComite(Comite comite) {
        this.comite = comite;
    }

    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
    }
}

