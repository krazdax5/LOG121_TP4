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

    /**
     * Perspective actuelle de la vue active
     */
    private Perspective perspective;
    /**
     * Entier signifiant la distance en X du déplacement de la perspective en fonction de la perspective initiale
     */
    private int offsetX;
    /**
     * Entier signifiant la distance en Y du déplacement de la perspective en fonction de la perspective initiale
     */
    private int offsetY;
    /**
     * Garde en mémoire le offset en X de l'avant derniere modification
     */
    private int offsetXInitial;
    /**
     * Garde en mémoire le offset en Y de l'avant derniere modification
     */
    private int offsetYInitial;

    /**
     * Constructeur de la classe Deplacer
     * @param perspective
     * @param offsetX
     * @param offsetY
     */
    public Deplacer(Perspective perspective, int offsetX, int offsetY) {
        this.perspective = perspective;
        this.offsetXInitial = perspective.getCornerImageX();
        this.offsetYInitial = perspective.getCornerImageY();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Fonction permettant de changer les offsets de la perspective de la vue actives
     */
    @Override
    public void executer() {
        perspective.setCornerPerspective(offsetX, offsetY);

    }
    /**
     * Fonction permettant de revenir à la position antérieur au dernier déplacement de la perspective de la vue active
     */
    @Override
    public void defaire() {
        perspective.setCornerPerspective(offsetXInitial,offsetYInitial);
    }
}
