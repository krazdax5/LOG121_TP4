package ets.commande;

import ets.Perspective;
import ets.VueActive1;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Zoom implements InterfaceCommande {

    private Perspective perspective;

    public Zoom(Perspective perspective){
        this.perspective = perspective;
    }

    @Override
    public void executer() {
        perspective.setEchelle(perspective.getEchelle() + 1);
    }

    @Override
    public void defaire() {
        perspective.setEchelle(perspective.getEchelle() - 1);
    }
}
