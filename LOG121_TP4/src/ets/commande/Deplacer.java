package ets.commande;

import ets.Perspective;

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

    public Deplacer(Perspective perspective, int offsetX, int offsetY) {
        this.perspective = perspective;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void executer() {
        perspective.setCornerPerspective(offsetX, offsetY);

    }

    @Override
    public void defaire() {

    }
}
