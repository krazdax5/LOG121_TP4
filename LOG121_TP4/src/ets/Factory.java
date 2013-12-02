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

    /**
     * Fonction créant un nouveau Zoom grâce à l'appelle du constructeur
     * @param perspective
     * @param echelle
     * @return
     */
    public Zoom createZoom(Perspective perspective, int echelle) {
        return new Zoom(perspective, echelle);
    }

    /**
     * Fonction créant un nouveau Deplacer grâce à l'appelle du constructeur
     * @param perspective
     * @param offsetX
     * @param offsetY
     * @return
     */
    public Deplacer createDeplacer(Perspective perspective, int offsetX, int offsetY) {
        return new Deplacer(perspective,offsetX, offsetY);
    }

    /**
     * Fonction créant une nouvelle Copie grâce à l'appelle du constructeur
     * @param perspective
     * @return
     */
    public Copie createCopie(Perspective perspective){
        return new Copie(perspective);
    }
}
