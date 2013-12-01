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
    private int echelle;
    private int offsetX;
    private int offsetY;

    public Copie(Perspective perspective) {
        this.perspective = perspective;
        this.echelle = perspective.getEchelle();
        this.offsetX = perspective.getCornerImageX();
        this.offsetY = perspective.getCornerImageY();
    }

    @Override
    public void executer() {
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive1().setEchelle(echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive1().setCornerPerspective(offsetX, offsetY);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }

    @Override
    public void defaire() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
