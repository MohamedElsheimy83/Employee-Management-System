package TP2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Employe> employes = new ArrayList<>();
    static ArrayList<Unite> unites = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        unites = Utils.creerUnites();
        Utils.creerRelationsUnites(unites);
        employes = Utils.creerEmployes(unites);
        Utils.creerComites(unites, employes);
        int choixMenu = -1;
        int choixSousMenu = -1;
        boolean fin = false;

        do {
            afficherMenuPrincipal();
            choixMenu = verifierEntier(scan.nextLine());
            switch (choixMenu) {
                case 0:
                    fin = true;
                    choixMenu = -1;
                    break;
                case 1:
                    afficherMenuOrganigramme();
                    choixSousMenu = verifierEntier(scan.nextLine());
                    choixMenu = -1;
                    switch (choixSousMenu) {
                        case 1:
                            listerUnites();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 2:
                            //need revision listerUniteReleve();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 3:
                            listerUniteNiveau();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 4:
                            rechercherUnite();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 5:
                            listerComite();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        default:
                            System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
                            choixSousMenu = -1;
                            choixMenu = -1;
                    }
                    break;
                case 2:
                    afficherMenuEmployes();
                    choixSousMenu = verifierEntier(scan.nextLine());

                    switch (choixSousMenu) {
                        case 1:
                            //remove affectation
                            listerEmployes();
                             choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 2:
                            listerEmployeUnite();
                             choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 3:
                            //need revision
                            listerEmployeUniteReleve();
                            choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 4:
                            //need revision
                            listerEmployeCategorie();
                             choixSousMenu = -1;
                            choixMenu = -1;
                            break;
                        case 5:
                            rechercherEmploye();
                             choixSousMenu = -1;
                            choixMenu = -1;

                            break;
                        default:
                            System.out.println("Votre choix n'est pas valide >> Retour au menu principal22222222>>");
                            choixSousMenu = -1;
                            choixMenu = -1;
                    }
                    break;
                default:
                    System.out.println("Votre choix n'est pas valide >> Retour au menu principal2.5555555555>>");
                    choixMenu = -1;

            }
        } while (!fin);
        System.out.println("Fin du program");


    }// Methode main

    public static void afficherMenuPrincipal() {
        System.out.print("===================================================" + '\n' +
                "Menu Pricipal" + '\n' +
                "===================================================" + '\n' +
                "1- Interroger l'organigramme" + '\n' +
                "2- Interroger les employes" + '\n' +
                "0- Terminer le program" + '\n' +
                "===================================================" + '\n' +
                "Votre choix: (0 - 1 - 2): ");
    }

    public static void afficherMenuOrganigramme() {
        System.out.print("====================================" + '\n' +
                "Interroger l’organigramme" + '\n' +
                "====================================" + '\n' +
                "1. Lister les unites de l’entreprise" + '\n' +
                "2. Lister les unites qui relevent d’une unite" + '\n' +
                "3. Lister toutes les unites d’un niveau/type donne" + '\n' +
                "4. Rechercher une unite" + '\n' +
                "5. Lister les comites de gestion" + '\n' +
                "====================================" + '\n' +
                "Entrez votre choix: ");
    }

    public static void afficherMenuEmployes() {
        System.out.print("====================================" + '\n' +
                "Interroger les employes" + '\n' +
                "====================================" + '\n' +
                "1. Lister les employes de l’entreprise" + '\n' +
                "2. Lister les employes d’une unite en particulier" + '\n' +
                "3. Lister les employes d’une unite en particulier ainsi que des unites qui relevent de celle ci" + '\n' +
                "4. Lister les employes d’une categorie de poste" + '\n' +
                "5. Rechercher un employe" + '\n' +
                "====================================" + '\n' +
                "Entrez votre choix: ");
    }

    public static int verifierEntier(String input) {
        int nombre = 0;
        try {
            nombre = Integer.parseInt(input);
            return nombre;
        } catch (Exception e) {
            System.out.println("Vous n'avez ps saisi un nombre entier!");
            return 0;
        }
    }

    public static String convertMajMin(String data) {
        String firstLetter = data.substring(0, 1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    public static void listerUnites() {
        System.out.println("====================================");
        System.out.println("Les unites de l’entreprise:");
        System.out.println("====================================");
        for (int i = 0; i < unites.size(); i++) {
            System.out.println("Numero d'unite: " + unites.get(i).getNum() + '\n' +
                    "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                    "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                    "---------------------------------------------");
        }
    }

    public static void listerUniteReleve() {
        int choix = 0;
        boolean trouve = false;
        int idx = 0;
        String x = "";
        String y = "";

        System.out.println("Entrez le numero de l'unite svp: " + '\n' +
                "(2000, 3000, 4000, 5000, 6000)");
        System.out.print("Votre choix: ");
        choix = verifierEntier(scan.nextLine());

        if (choix == -1) {
            choix = 0;
            trouve = false;
            idx = 0;
            x = "";
            y = "";
            return;
        }

        while (!trouve && (idx < unites.size())) {
            if (unites.get(idx).getNum() == choix) {
                trouve = true;
                System.out.println("====================================================" + '\n' +
                        "Unites relevant de " + unites.get(idx).getNom() + ":" + '\n' +
                        "====================================================");
                for (int i = 0; i < unites.size(); i++) {
                    x = Integer.toString(unites.get(i).getNum());
                    y = Integer.toString(unites.get(idx).getNum());
                    if ((unites.get(i).getNum() != choix && x.charAt(0) == y.charAt(0) && y.charAt(1) == '0') ||
                            (unites.get(idx).getNum() == 1000 && unites.get(i).getNum() != choix)) {

                        System.out.println("Numero d'unite: " + unites.get(i).getNum() + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                    if (y.charAt(1) != '0') {
                        System.out.println("Cette unite n'a pas d'unite sous elle");
                        choix = 0;
                        trouve = false;
                        idx = 0;
                        x = "";
                        y = "";
                        break;
                    }
                }
                choix = 0;
                trouve = false;
                idx = 0;
                x = "";
                y = "";
            } else
                idx++;
        }
        if (trouve) {
            choix = 0;
            trouve = false;
            idx = 0;
            x = "";
            y = "";
        } else if (!trouve) {
            System.out.println("Le numero de l'unite n'existe pas");
            choix = 0;
            trouve = false;
            idx = 0;
            x = "";
            y = "";
        }
    }

    public static void listerComite() {
        System.out.println("====================================");
        System.out.println("Les comites de gestion:");
        System.out.println("====================================");
        for (int i = 0; i < unites.size(); i++) {
            System.out.print("Numero d'unite: " + unites.get(i).getNum() + '\n' +
                    "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                    "Nom des membres: ");
            for (int j = 0; j < unites.get(i).getComite().getMembres().size(); j++) {
                System.out.print(unites.get(i).getComite().getMembres().get(j).getNom());
                // ajoutter ,
                if ((j + 1) < unites.get(i).getComite().getMembres().size())
                    System.out.print(", ");
            }
            System.out.println('\n' + "------------------------------------------------");
        }
    }

    public static void listerEmployes() {
        System.out.println("====================================");
        System.out.println("Les employes de l’entreprise:");
        System.out.println("====================================");
        for (int i = 0; i < employes.size(); i++) {
            System.out.print("Numero d'employe: " + employes.get(i).getNum() + '\n' +
                    "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                    "Titre d'employe: " + employes.get(i).getTitre() + '\n' +
                    "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                    "----------------------------------" + '\n' + employes.get(i).getAffectation().getNum());
        }
    }


    public static void listerUniteNiveau() {
        String niveau = "";

        System.out.println("Entrez le niveau svp: " + '\n' +
                "(presidence , vice-presidence, direction )");
        System.out.print("Votre choix: ");
        niveau = scan.nextLine();

        switch (niveau.toUpperCase()) {
            case "PRESIDENCE":
                System.out.println("====================================================" + '\n' +
                        "Unites de niveau Presidence:" + '\n' +
                        "====================================================");
                for (int i = 0; i < unites.size(); i++) {
                    if (unites.get(i) instanceof Presidence) {
                        System.out.println("Numero d'unite: " + unites.get(i).getNum() + " " + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "VICE-PRESIDENCE":
                System.out.println("====================================================" + '\n' +
                        "Unites de niveau Vice-Presidence:" + '\n' +
                        "====================================================");
                for (int i = 0; i < unites.size(); i++) {
                    if (unites.get(i) instanceof VicePresidence) {
                        System.out.println("Numero d'unite: " + unites.get(i).getNum() + " " + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "DIRECTION":
                System.out.println("====================================================" + '\n' +
                        "Unites de niveau Direction:" + '\n' +
                        "====================================================");
                for (int i = 0; i < unites.size(); i++) {
                    if (unites.get(i) instanceof Direction) {
                        System.out.println("Numero d'unite: " + unites.get(i).getNum() + " " + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            default:
                niveau = "";
                System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
        }
    }

    public static void rechercherUnite() {
        int choix = 0;
        boolean trouve = false;
        int idx = 0;
        String input = "";

        System.out.println("Selectionnez 1 pour rechercher par nom d'unite");
        System.out.println("Selectionnez 2 pour rechercher par responsable");
        System.out.print("Votre choix (1 ou 2): ");
        choix = verifierEntier(scan.nextLine());

        switch (choix) {
            case 1:
                System.out.println("Entrez le nom d'unite svp (nom complet ou une partie de celui-ci):" + '\n' +
                        "-------------------------------------------------------------");
                for (int i = 0; i < unites.size(); i++) {
                    System.out.println(unites.get(i).getNom());
                }
                System.out.print("-------------------------------------------------------------" + '\n' +
                        "Votre choix: ");
                input = scan.nextLine();

                for (int i = 0; i < unites.size(); i++) {
                    if ((unites.get(i).getNom().toUpperCase()).contains(input.toUpperCase())) {
                        System.out.println("-------------------------------------------------------------" + '\n' +
                                "Numero d'unite: " + unites.get(i).getNum() + " " + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                        idx++;
                    }
                }
                if (idx == 0) {
                    choix = 0;
                    trouve = false;
                    input = "";
                    System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
                }
                choix = 0;
                trouve = false;
                idx = 0;
                input = "";
                break;
            case 2:
                System.out.println("Entrez le nom du responsable svp (nom complet ou une partie de celui-ci):" + '\n' +
                        "-------------------------------------------------------------");
                for (int i = 0; i < unites.size(); i++) {
                    System.out.println(unites.get(i).getResponsable().getNom());
                }
                System.out.print("-------------------------------------------------------------" + '\n' +
                        "Votre choix: ");
                input = scan.nextLine();

                for (int i = 0; i < unites.size(); i++) {
                    if ((unites.get(i).getResponsable().getNom().toUpperCase()).contains(input.toUpperCase())) {
                        System.out.println("-------------------------------------------------------------" + '\n' +
                                "Numero d'unite: " + unites.get(i).getNum() + " " + '\n' +
                                "Nom d'unite: " + unites.get(i).getNom() + '\n' +
                                "Responsable: " + unites.get(i).getResponsable().getNom() + '\n' +
                                "-------------------------------------------------------------");
                        idx++;
                    }
                }
                if (idx == 0) {
                    choix = 0;
                    trouve = false;
                    idx = 0;
                    input = "";
                    System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
                }
                choix = 0;
                trouve = false;
                idx = 0;
                input = "";
                break;
            default:
                System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
        }
    }

    public static void listerEmployeUnite() {
        int choix = -1;
        int idx = 0;

        System.out.println("Entrez le numero de l'unite svp: ");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < unites.size(); i++) {
            System.out.println(unites.get(i).getNum());
        }
        System.out.print("-------------------------------------------------------------" + '\n' +
                "Votre choix: ");
        choix = verifierEntier(scan.nextLine());
        for (int i = 0; i < unites.size(); i++) {
            if (unites.get(i).getNum() == choix) {
                for (int j = 0; j < unites.get(i).getEmployes().size(); j++) {
                    System.out.println("-------------------------------------------------------------" + '\n' +
                            "Numero d'employe: " + unites.get(i).getEmployes().get(j).getNum() + " " + '\n' +
                            "Nom d'employe: " + unites.get(i).getEmployes().get(j).getNom() + '\n' +
                            "Titre d'employe: " + unites.get(i).getEmployes().get(j).getTitre() + '\n' +
                            "-------------------------------------------------------------");
                    idx++;
                }
            }
        }
        choix = -1;
        if (idx == 0) {
            choix = -1;
            System.out.println("Le numero d'unite n'est pas correct >>Retour au menu principal>>");
        }
    }


    public static void listerEmployeUniteReleve() {
        int choix = -1;
        int idx = 0;

        System.out.println("Entrez le numero de l'unite svp: ");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < unites.size(); i++) {
            System.out.println(unites.get(i).getNum());
        }
        System.out.print("-------------------------------------------------------------" + '\n' +
                "Votre choix: ");
        choix = verifierEntier(scan.nextLine());
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).getAffectation().getNum() == choix) {

                System.out.println("-------------------------------------------------------------" + '\n' +
                        "Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                        "Nom d'employe: " + employes.get(i).getNom() + " " + '\n' +
                        "Titre d'employe: " + employes.get(i).getTitre() + '\n' +
                        "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                        "-------------------------------------------------------------");
                idx++;
            }
        }
        choix = -1;
//        for (int i = 0; i < unites.size(); i++) {
//            if (unites.get(i) instanceof Presidence) {
//                for (int j = 0; j < unites.get(i).getEmployes().size(); j++) {
//                    System.out.println("Numero d'employe: " + unites.get(i).getEmployes().get(j).getNum() + " " + '\n' +
//                            "Nom d'employe: " + unites.get(i).getEmployes().get(j).getNom() + " " + '\n' +
//                            "Titre d'employe: " + unites.get(i).getEmployes().get(j).getTitre() + '\n' +
//                            "Affectation: " + unites.get(i).getEmployes().get(j).getAffectation().getNom() + '\n' +
//                            "-------------------------------------------------------------");
//                }
//            }
//        }
        choix = -1;
    }

    public static void listerEmployeCategorie() {
        String niveau = "";

        System.out.println("Entrez le niveau svp: " + '\n' +
                "(Administratif, Cadre , Gestionnaire, Ouvrier, Professionnel)");
        System.out.print("Votre choix: ");
        niveau = scan.nextLine();

        switch (niveau.toUpperCase()) {
            case "CADRE":
                System.out.println("====================================================" + '\n' +
                        "Employes de categorie Cadre:" + '\n' +
                        "====================================================");
                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i) instanceof Cadre) {
                        System.out.println("Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "GESTIONNAIRE":
                System.out.println("====================================================" + '\n' +
                        "Employes de categorie Gestionnaire:" + '\n' +
                        "====================================================");
                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i) instanceof Gestionnaire) {
                        System.out.println("Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "ADMINISTRATIF":
                System.out.println("====================================================" + '\n' +
                        "Employes de categorie Administratif:" + '\n' +
                        "====================================================");
                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i) instanceof Administratif) {
                        System.out.println("Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "OUVRIER":
                System.out.println("====================================================" + '\n' +
                        "Employes de categorie Cadre:" + '\n' +
                        "====================================================");
                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i) instanceof Ouvrier) {
                        System.out.println("Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            case "PROFESSIONNEL":
                System.out.println("====================================================" + '\n' +
                        "Employes de categorie Professionnel:" + '\n' +
                        "====================================================");
                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i) instanceof Professionnel) {
                        System.out.println("Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                    }
                }
                niveau = "";
                break;
            default:
                niveau = "";
                System.out.println("Votre choix n'est pas valide >> Retour au menu principal>>");
        }
    }

    public static void rechercherEmploye() {
        int choix = 0;
        boolean trouve = false;
        int idx = 0;
        String input = "";

        System.out.println("Selectionnez 1 pour rechercher par nom d'employe");
        System.out.println("Selectionnez 2 pour rechercher par numero d'employe");
        System.out.print("Votre choix (1 ou 2): ");
        choix = verifierEntier(scan.nextLine());

        switch (choix) {
            case 1:
                System.out.println("Entrez le nom d'employe svp (nom complet ou une partie de celui-ci):" + '\n' +
                        "-------------------------------------------------------------");
                for (int i = 0; i < employes.size(); i++) {
                    System.out.println(employes.get(i).getNom());
                }
                System.out.print("-------------------------------------------------------------" + '\n' +
                        "Votre choix: ");
                input = scan.nextLine();

                for (int i = 0; i < employes.size(); i++) {
                    if ((employes.get(i).getNom().toUpperCase()).contains(input.toUpperCase())) {
                        System.out.println("-------------------------------------------------------------" + '\n' +
                                "Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                        idx++;
                    }
                }
                if (idx == 0) {
                    choix = 0;
                    trouve = false;
                    input = "";
                    System.out.println("Votre choix n'est pas valide >> Retour au menu principal//////>>");
                }
                choix = 0;
                trouve = false;
                idx = 0;
                input = "";
                break;
            ///////////////////////////////////////////////////////////////////////////////////////////////
            case 2:
                System.out.println("Entrez le numero du responsable svp :" + '\n' +
                        "-------------------------------------------------------------");
                for (int i = 0; i < employes.size(); i++) {
                    System.out.println(employes.get(i).getNum());
                }
                System.out.print("-------------------------------------------------------------" + '\n' +
                        "Votre choix: ");
                choix = verifierEntier(scan.nextLine());

                for (int i = 0; i < employes.size(); i++) {
                    if (employes.get(i).getNum() == choix) {
                        System.out.println("-------------------------------------------------------------" + '\n' +
                                "Numero d'employe: " + employes.get(i).getNum() + " " + '\n' +
                                "Nom d'employe: " + employes.get(i).getNom() + '\n' +
                                "Titre: " + employes.get(i).getTitre() + '\n' +
                                "Affectation: " + employes.get(i).getAffectation().getNom() + '\n' +
                                "-------------------------------------------------------------");
                        idx++;
                    }
                }
                if (idx == 0) {
                    choix = 0;
                    trouve = false;
                    input = "";
                    System.out.println("Votre choix n'est pas valide >> Retour au menu principa+++++++l>>");
                }
                choix = 0;
                trouve = false;
                idx = 0;
                input = "";
                break;
            default:
                choix = 0;
                trouve = false;
                idx = 0;
                input = "";
                System.out.println("Votre choix n'est pas valide >> Retour au menu principal!!!!!!!!>>");
        }
    }
}


