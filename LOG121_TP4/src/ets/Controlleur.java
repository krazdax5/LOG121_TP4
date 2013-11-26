package ets;


/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Controlleur {

    GestionCommande gestionnaire;

    VueReduite vignette;
    ImageConcrete image;

    public static void main(String args[]) {

        FenetrePrincipale applicationImages = new FenetrePrincipale();

        Thread t = new Thread(applicationImages);

        t.start();
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
