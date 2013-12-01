package ets;

import ets.commande.Copie;
import ets.commande.Deplacer;
import ets.commande.Zoom;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Factory {

    public Zoom createZoom(Perspective perspective, int echelle) {
        return new Zoom(perspective, echelle);
    }

    public Deplacer createDeplacer(Perspective perspective, int offsetX, int offsetY) {
        return new Deplacer(perspective,offsetX, offsetY);
    }

    public Copie createCopie(Perspective perspective){
        return new Copie(perspective);
    }
}
