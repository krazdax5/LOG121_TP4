package ets;


import ets.gui.FenetrePrincipale;
import ets.gui.PanneauPrincipal;

import java.awt.*;
import java.util.Observer;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Controlleur {


    private static Controlleur controlleur;

    private PanneauPrincipal panneauPrincipal;
    GestionCommande gestionnaire;

    ImageConcrete image;

    public static void main(String args[]) {

        FenetrePrincipale applicationImages = FenetrePrincipale.getFenetrePrincipale();

        Thread t = new Thread(applicationImages);

        t.start();
    }

    private Controlleur() {
        panneauPrincipal = PanneauPrincipal.getPanneauPrincipal();

        image = ImageConcrete.createImage(null);
        image.addObserver(VueReduite.getVueReduite());

        image.addObserver(VueActive1.getVueActive1());
        image.addObserver(VueActive2.getVueActive2());

        image.addObserver(FenetrePrincipale.getFenetrePrincipale());
        image.addObserver(panneauPrincipal);
        gestionnaire = new GestionCommande();
    }

    public static Controlleur getControlleur() {
        if(controlleur == null)
            controlleur = new Controlleur();

        return controlleur;
    }

    public void changerImage(String adresse) {
        this.image.setImage(adresse);
    }

    public void changerPerspective(String nouvellePerspective) {
        Perspective perspective = UtilitaireFichier.recuperePerspective(nouvellePerspective);
        PanneauPrincipal.instanceVueActive1.setPerspective(perspective);
    }

    public void zoomer(Perspective perspective, int echelle) {
        gestionnaire.zommer(perspective, echelle);
    }

    public void deplacer(Perspective perspective, int offsetX, int offsetY) {
        gestionnaire.deplacer(perspective, offsetX, offsetY);
    }

    public void ctrlC(Perspective perspective){
        gestionnaire.ctrlC(perspective);
    }

    public void ctrlV(){
        gestionnaire.ctrlV();
    }

    public void defaire() {
        gestionnaire.defaire();
    }

    public void refaire() {
        gestionnaire.refaire();
    }
}
