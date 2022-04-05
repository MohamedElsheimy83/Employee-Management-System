package TP2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    static final String FICHIER_EMPLOYES = "employes.txt";
    static final String FICHIER_UNITES = "unites.txt";
    static final String FICHIER_RELATIONS_UNITES = "unitesRelations.txt";
    static final String FICHIER_COMITES = "comites.txt";
    static final char COMMENTAIRE = '#';

    public static ArrayList<Unite> creerUnites() {
        String ligne;
        String[] champs;
        String type;
        int num;
        String nom;
        ArrayList<Unite> enfants;
        ArrayList<Unite> unites = new ArrayList<>();

        if (!new File(FICHIER_UNITES).exists()) {
            System.out.println("Le fichier " + FICHIER_UNITES + " n'existe pas");
        } else {
            try {
                BufferedReader input = new BufferedReader(new FileReader(FICHIER_UNITES));
                while (input.ready()) {
                    ligne = input.readLine().trim();
//                    System.out.println("***TRACE unite lue: " + ligne);
                    if (!ligne.isBlank() && (ligne.charAt(0) != COMMENTAIRE)){
                        champs = ligne.split(";");
                        num = Integer.parseInt(champs[0]);
                        type = champs[1].trim();
                        nom = champs[2].trim();
                        switch (type.toUpperCase()) {
                            case "PRESIDENCE":
                                unites.add(new Presidence(num, nom));
                                break;
                            case "VICE-PRESIDENCE":
                                unites.add(new VicePresidence(num, nom));
                                break;
                            case "DIRECTION":
                                unites.add(new Direction(num, nom));
                                break;
                        }
                    }
                }
                input.close();
            } catch (IOException e) {
                System.out.println("***** Erreur de lecture avec le fichier "
                        + FICHIER_UNITES);
            }
        }
        return unites;
    }

    public static ArrayList<Employe> creerEmployes(ArrayList<Unite> unites) {
        String ligne;
        String[] champs;
        String nom;
        String titre;
        String categorie;
        String nomUnite;
        Unite unite;
        boolean responsable;
        Employe employe;
        ArrayList<Employe> employes = new ArrayList<>();


        if (!new File(FICHIER_EMPLOYES).exists()) {
            System.out.println("Le fichier " + FICHIER_EMPLOYES + " n'existe pas");
        } else {
            try {
                BufferedReader input = new BufferedReader(new FileReader(FICHIER_EMPLOYES));
                while (input.ready()) {
                    ligne = input.readLine().trim();
//                    System.out.println("***TRACE employé lu: " + ligne);
                    if (!ligne.isBlank() && (ligne.charAt(0) != COMMENTAIRE)){
                        champs = ligne.split(";");
                        nom = champs[0];
                        titre = champs[1];
                        categorie = champs[2];
                        nomUnite = champs[3];
                        responsable= false;
                        if (champs.length > 4)
                            if (champs[4].equalsIgnoreCase("responsable"))
                                responsable = true;
                        switch (categorie.toUpperCase()) {
                            case "CADRE":
                                unite = getUniteParNom(unites, nomUnite);
                                employe = new Cadre(nom, titre, unite);
                                employes.add(employe);
                                unite.ajouterEmploye(employe);
                                if (responsable)
                                    unite.setResponsable(employe);
                                break;
                            case "GESTIONNAIRE":
                                unite = getUniteParNom(unites, nomUnite);
                                employe = new Gestionnaire(nom, titre, unite);
                                employes.add(employe);
                                unite.ajouterEmploye(employe);
                                if (responsable)
                                    unite.setResponsable(employe);
                                break;
                            case "PROFESSIONNEL":
                                unite = getUniteParNom(unites, nomUnite);
                                employe = new Professionnel(nom, titre, unite);
                                employes.add(employe);
                                unite.ajouterEmploye(employe);
                                break;
                            case "OUVRIER":
                                unite = getUniteParNom(unites, nomUnite);
                                employe = new Ouvrier(nom, titre, unite);
                                employes.add(employe);
                                unite.ajouterEmploye(employe);
                                break;
                            case "ADMINISTRATIF":
                                unite = getUniteParNom(unites, nomUnite);
                                employe = new Administratif(nom, titre, unite);
                                employes.add(employe);
                                unite.ajouterEmploye(employe);
                                break;
                        }
                    }
                }
                input.close();
            } catch (IOException e) {
                System.out.println("***** Erreur de lecture avec le fichier "
                        + FICHIER_EMPLOYES);
            }
        }
        return employes;
    }

    public static void creerRelationsUnites(ArrayList<Unite> unites) {

        String ligne;
        String[] champs;
        String nom;
        String nomParent;
        String nomEnfant;
        Unite unite;
        Unite parent;
        Unite enfant;
        ArrayList<Unite> enfants;

        if (!new File(FICHIER_RELATIONS_UNITES).exists()) {
            System.out.println("Le fichier " + FICHIER_UNITES + " n'existe pas");
        } else {
            try {
                BufferedReader input = new BufferedReader(new FileReader(FICHIER_RELATIONS_UNITES));
                while (input.ready()) {
                    ligne = input.readLine().trim();
//                    System.out.println("***TRACE relation lue: " + ligne);
                    if (!ligne.isBlank() && (ligne.charAt(0) != COMMENTAIRE)){
                        champs = ligne.split(";");
                        nom = champs[0].trim();
                        nomParent = champs[1].trim();
                        unite = getUniteParNom(unites, nom);
                        if (!nomParent.isEmpty())
                            parent = getUniteParNom(unites, nomParent);
                        else
                            parent = null;
                        enfants = new ArrayList<>();
                        for (int i = 2; i < champs.length; i++) {
                            nomEnfant = champs[i].trim();
                            enfant = getUniteParNom(unites, nomEnfant);
                            enfants.add(enfant);
                        }
                        if (unite instanceof Presidence)
                            ((Presidence) unite).setListeUnites(enfants);
                        else if (unite instanceof VicePresidence) {
                            ((VicePresidence) unite).setListeUnites(enfants);
                            ((VicePresidence) unite).setParent(parent);
                        } else if (unite instanceof Direction) {
                            ((Direction) unite).setParent(parent);
                        }
                    }
                }
                input.close();
            } catch (IOException e) {
                System.out.println("***** Erreur de lecture avec le fichier "
                        + FICHIER_RELATIONS_UNITES);
            }
        }
    }

    public static void creerComites(ArrayList<Unite> unites, ArrayList<Employe> employes) {
        String ligne;
        String[] champs;
        int num;
        String nom;
        Unite unite;
        Comite comite;
        String nomMembre;
        Employe employe;

        if (!new File(FICHIER_COMITES).exists()) {
            System.out.println("Le fichier " + FICHIER_COMITES + " n'existe pas");
        } else {
            try {
                BufferedReader input = new BufferedReader(new FileReader(FICHIER_COMITES));
                while (input.ready()) {
                    ligne = input.readLine().trim();
//                    System.out.println("***TRACE employé lu: " + ligne);
                    if (!ligne.isBlank() && (ligne.charAt(0) != COMMENTAIRE)){
                        champs = ligne.split(";");
                        num = Integer.parseInt(champs[0]);
                        nom = champs[1];
                        unite = getUniteParNum(unites, num);
                        comite = new Comite(nom);
                        unite.setComite(comite);
                        for (int i = 2; i < champs.length; i++) {
                            nomMembre = champs[i].trim();
                            employe = getEmployeParNom(employes, nomMembre);
                            comite.ajouterEmploye(employe);
                        }
                    }
                }
                input.close();
            } catch (IOException e) {
                System.out.println("***** Erreur de lecture avec le fichier "
                        + FICHIER_COMITES);
            }
        }
    }

    public static Unite getUniteParNom(ArrayList<Unite> unites, String nomUnite) {
        boolean trouve = false;
        int idx = 0;
        Unite unite = null;

        while (!trouve && (idx < unites.size())) {
            if (unites.get(idx).getNom().equalsIgnoreCase(nomUnite))
                trouve = true;
            else
                idx++;
        }
        if (trouve)
            unite = unites.get(idx);
        else
            System.out.println("***ATTENTION*** getUniteParNom retourne null avec: " + nomUnite);
        return unite;
    }

    public static Unite getUniteParNum(ArrayList<Unite> unites, int num) {
        boolean trouve = false;
        int idx = 0;
        Unite unite = null;

        while (!trouve && (idx < unites.size())) {
            if (unites.get(idx).getNum() == num)
                trouve = true;
            else
                idx++;
        }
        if (trouve)
            unite = unites.get(idx);
        else
            System.out.println("***ATTENTION*** getUniteParNom retourne null avec numero: " + num);
        return unite;
    }

    public static Employe getEmployeParNom(ArrayList<Employe> employes, String nom) {
        boolean trouve = false;
        int idx = 0;
        Employe employe = null;

        while (!trouve && (idx < employes.size())) {
            if (employes.get(idx).getNom().equalsIgnoreCase(nom))
                trouve = true;
            else
                idx++;
        }
        if (trouve)
            employe = employes.get(idx);
        else
            System.out.println("***ATTENTION*** getEmployeParNom retourne null avec: " + nom);
        return employe;
    }
}
