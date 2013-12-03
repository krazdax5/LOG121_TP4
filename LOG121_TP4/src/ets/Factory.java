package ets;

import ets.commande.Copie;
import ets.commande.Deplacer;
import ets.commande.Zoom;

/**
 * Classe qui permet la creation des commandes. Met en oeuvre le patron Factory.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 Implementation de la classe
 */
public class Factory {

    /**
     * Fonction créant un nouveau Zoom grâce à l'appelle du constructeur
     * @param echelle
     * @return
     */
    public Zoom createZoom(int echelle) {
        return new Zoom(echelle);
    }

    /**
     * Fonction créant un nouveau Deplacer grâce à l'appelle du constructeur
     * @param offsetX
     * @param offsetY
     * @return
     */
    public Deplacer createDeplacer(int offsetX, int offsetY) {
        return new Deplacer(offsetX, offsetY);
    }

    /**
     * Fonction créant une nouvelle Copie grâce à l'appelle du constructeur
     * @return
     */
    public Copie createCopie(){
        return new Copie();
    }
}
