package ets;


import ets.gui.FenetrePrincipale;
import ets.gui.PanneauPrincipal;
import ets.gui.VueActive;
import ets.gui.VueOriginale;

import java.io.File;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 : Implementation de la classe
 */
public class Controleur {


    private static Controleur instance;

    private PanneauPrincipal panneauPrincipal;
    private GestionCommande gestionnaire;

    ImageConcrete image;

    public static void main(String args[]) {

        FenetrePrincipale applicationImages = FenetrePrincipale.getFenetrePrincipale();

        Thread threadApplication = new Thread(applicationImages);

        threadApplication.start();
    }

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

    public static Controleur getControleur() {
        if(instance == null)
            instance = new Controleur();

        return instance;
    }

    public void changerImage(File sourceImage) {
        this.image.setImage(sourceImage);
    }

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

    public void zoomer(int echelle) {
        gestionnaire.zommer(echelle);
    }

    public void deplacer(int offsetX, int offsetY) {
        gestionnaire.deplacer(offsetX, offsetY);
    }

    public void ctrlC(){
        gestionnaire.ctrlC();
    }

    public void ctrlV(){
        gestionnaire.ctrlV();
    }

    public void defaire() {
        gestionnaire.defaireCommande();
    }

    public void refaire() {
        gestionnaire.refaireCommande();
    }
}
