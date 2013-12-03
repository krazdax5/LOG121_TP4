package ets;


import ets.gui.FenetrePrincipale;
import ets.gui.PanneauPrincipal;
import ets.gui.VueActive;
import ets.gui.VueOriginale;

import java.io.File;

/**
 * Le controlleur general de l'application. Toutes les modifications captees par
 * les vues passent par lui.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 : Implementation de la classe
 */
public class Controleur {

    /**
     * Attribut singleton de l'instance simple du controlleur.
     */
    private static Controleur instance;
    /**
     * Panneau principal de l'application
     */
    private PanneauPrincipal panneauPrincipal;
    /**
     * Gestionnaire des commandes. Garde en memoire celle effectuee.
     */
    private GestionCommande gestionnaire;

    /**
     * Image concrete qui est observee par l'application, le panneau principal et les vues.
     */
    private ImageConcrete image;

    /**
     * main de l'application.
     * @param args
     */
    public static void main(String args[]) {

        FenetrePrincipale applicationImages = FenetrePrincipale.getFenetrePrincipale();

        Thread threadApplication = new Thread(applicationImages);

        threadApplication.start();
    }

    /**
     * Constructeur prive.
     */
    private Controleur() {
        panneauPrincipal = PanneauPrincipal.getPanneauPrincipal();

        image = ImageConcrete.createImage(null);
        image.addObserver(VueOriginale.getVueOriginale());

        image.addObserver(panneauPrincipal.getVueActive1());
        image.addObserver(panneauPrincipal.getVueActive2());

        image.addObserver(FenetrePrincipale.getFenetrePrincipale());
        image.addObserver(panneauPrincipal);
        gestionnaire = new GestionCommande();
    }

    /**
     * Methode singleton qui retourne l'instance simple du controlleur
     * @return l'instance simple de la vue.
     */
    public static Controleur getControleur() {
        if(instance == null)
            instance = new Controleur();

        return instance;
    }

    /**
     * Methode qui permet de changer l'image selon le fichier choisi par l'utilisateur.
     * @param sourceImage Le fichier choisi par l'utilisateur.
     */
    public void changerImage(File sourceImage) {
        this.image.setImage(sourceImage);
    }

    /**
     * Methode qui permet de changer une perspective par une choisie par l'utilisateur.
     * Apppelle l'utilisateur afin de recuperer la perspective choisie
     * @param newPerspective l'adresse de la nouvelle perspective.
     */
    public void changerPerspective(String newPerspective) {
        Perspective perspective = UtilitaireFichier.recuperePerspective(newPerspective);
        int echelle = perspective.getEchelle();
        int offsetX = perspective.getCornerImageX();
        int offsetY = perspective.getCornerImageY();
        VueActive vueActive = panneauPrincipal.getVueChoisie();
        vueActive.getPerspectiveVueActive().setEchelle(echelle);
        vueActive.getPerspectiveVueActive().setCornerPerspective(offsetX,offsetY);
        panneauPrincipal.repaint();
    }

    /**
     * Methode permetant la creation d'un zoom et la mise en memoire de celui-ci
     * @param echelle l'incrementation de l'echelle faite par le zoom.
     */
    public void zoomer(int echelle) {
        gestionnaire.zommer(echelle);
    }

    /**
     * Methode permettant la creation d'une commande deplacer et la mise en memoire de celui-ci.
     * @param offsetX Le deplacement en X
     * @param offsetY Le deplacement en Y
     */
    public void deplacer(int offsetX, int offsetY) {
        gestionnaire.deplacer(offsetX, offsetY);
    }

    /**
     * Methode permettant la copie d'une copie de la perspective.
     */
    public void ctrlC(){
        gestionnaire.ctrlC();
    }

    /**
     * Methode permettant de coller une perspective deja copiee.
     */
    public void ctrlV(){
        gestionnaire.ctrlV();
    }

    /**
     * Methode permettant de defaire une commande.
     */
    public void defaire() {
        gestionnaire.defaireCommande();
    }

    /**
     * Methode permettant de refaire une commande defaite.
     */
    public void refaire() {
        gestionnaire.refaireCommande();
    }
}
