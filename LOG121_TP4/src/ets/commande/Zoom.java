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
public class Zoom implements InterfaceCommande {

    private Perspective perspective;
    private int echelle;

    public Zoom(Perspective perspective,int echelle){
        this.perspective = perspective;
        this.echelle = echelle;
    }

    @Override
    public void executer() {
        perspective.setEchelle(perspective.getEchelle() + echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }

    @Override
    public void defaire() {
        perspective.setEchelle(perspective.getEchelle() - echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }
}
