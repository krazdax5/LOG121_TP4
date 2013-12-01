package ets.commande;

import ets.Perspective;
import ets.gui.PanneauPrincipal;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class Copie implements InterfaceCommande {

    private Perspective perspective;

    public Copie(Perspective perspective) {
        this.perspective = perspective;
    }

    @Override
    public void executer() {
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().setPerspective(perspective);
    }

    @Override
    public void defaire() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
