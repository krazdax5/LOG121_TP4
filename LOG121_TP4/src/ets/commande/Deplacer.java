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
public class Deplacer implements InterfaceCommande {

    private Perspective perspective;
    private int offsetX;
    private int offsetY;
    private int offsetXInitial;
    private int offsetYInitial;

    public Deplacer(Perspective perspective, int offsetX, int offsetY) {
        this.perspective = perspective;
        this.offsetXInitial = perspective.getCornerImageX();
        this.offsetYInitial = perspective.getCornerImageY();
        this.offsetX = offsetX;
        this.offsetY = offsetY;


    }

    @Override
    public void executer() {
        perspective.setCornerPerspective(offsetX, offsetY);
//        PanneauPrincipal.vueActive.repaint();

    }

    @Override
    public void defaire() {

    }
}
