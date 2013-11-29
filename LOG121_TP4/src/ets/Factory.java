package ets;

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

    public Zoom createZoom(Perspective perspective) {
        return new Zoom(perspective);
    }

    public Deplacer createDeplacer(Perspective perspective, int offsetX, int offsetY) {
        return new Deplacer(perspective,offsetX, offsetY);
    }
}
