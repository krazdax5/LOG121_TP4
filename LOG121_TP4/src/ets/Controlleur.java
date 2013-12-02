package ets;


import ets.gui.FenetrePrincipale;
import ets.gui.PanneauPrincipal;

import java.io.File;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Controlleur {


    private static Controlleur instance;

    private PanneauPrincipal panneauPrincipal;
    private GestionCommande gestionnaire;

    ImageConcrete image;

    public static void main(String args[]) {

        FenetrePrincipale applicationImages = FenetrePrincipale.getFenetrePrincipale();

        Thread threadApplication = new Thread(applicationImages);

        threadApplication.start();
    }

    private Controlleur() {
        panneauPrincipal = PanneauPrincipal.getPanneauPrincipal();

        image = ImageConcrete.createImage(null);
        image.addObserver(VueOriginale.getVueOriginale());

        image.addObserver(panneauPrincipal.getVueActive1());
        image.addObserver(panneauPrincipal.getVueActive2());

        image.addObserver(FenetrePrincipale.getFenetrePrincipale());
        image.addObserver(panneauPrincipal);
        gestionnaire = new GestionCommande();
    }

    public static Controlleur getControlleur() {
        if(instance == null)
            instance = new Controlleur();

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
        gestionnaire.defaireCommande();
    }

    public void refaire() {
        gestionnaire.refaireCommande();
    }
}
