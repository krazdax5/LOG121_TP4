package ets;


import ets.gui.FenetrePrincipale;
import ets.gui.PanneauPrincipal;

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
        image = ImageConcrete.createImage(null);
        image.addObserver(VueReduite.getVueReduite());
        image.addObserver(FenetrePrincipale.getFenetrePrincipale());
        image.addObserver(PanneauPrincipal.getPanneauPrincipal());
        gestionnaire = new GestionCommande();
    }

    public static Controlleur getControlleur() {
        if(controlleur == null)
            controlleur = new Controlleur();

        return controlleur;
    }

    public void changerImage(String nouvelleImage) {
        this.image.setImage(nouvelleImage);
    }

    public void zoomer(Perspective perspective, int echelle) {
        gestionnaire.zommer(perspective,echelle);
    }

    public void deplacer(Perspective perspective, int centreX, int centreY) {
        gestionnaire.deplacer(perspective, centreX, centreY);
    }
}
