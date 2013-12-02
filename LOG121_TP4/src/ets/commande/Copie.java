package ets.commande;

import ets.Perspective;
import ets.VueActive;
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

    /**
     * Vue active
     */
    VueActive vueActive;
    /**
     * Perspective initiale de la vue active
     */
    private Perspective perspectiveInitiale;
    /**
     * Entier représentant l'echelle de la perspective active
     */
    private int echelle;
    /**
     * Entier représentant le décalage en X de la perspective par rapport a la perspective active
     */
    private int offsetX;
    /**
     * Entier représentant le décalage en Y de la perspective par rapport a la perspective active
     */
    private int offsetY;

    /**
     * Constructeur de la classe Copie
     * @param perspective
     */
    public Copie(Perspective perspective) {
        this.perspectiveInitiale = perspective;
        this.echelle = perspective.getEchelle();
        this.offsetX = perspective.getCornerImageX();
        this.offsetY = perspective.getCornerImageY();
    }

    /**
     * Fonction permettant d'enregistrer la perspective de la vue active
     */
    @Override
    public void executer() {
        this.vueActive = PanneauPrincipal.getPanneauPrincipal().getVueChoisie();
        vueActive.getPerspectiveVueActive().setEchelle(echelle);
        vueActive.getPerspectiveVueActive().setCornerPerspective(offsetX, offsetY);
        vueActive.repaint();
    }

    /**
     * Fonction permettant de revenir à la perspective antérieur de la vue active
     */
    @Override
    public void defaire() {
        vueActive.getPerspectiveVueActive().setEchelle(perspectiveInitiale.getEchelle());
        vueActive.getPerspectiveVueActive().setCornerPerspective(perspectiveInitiale.getCornerImageX(),
                perspectiveInitiale.getCornerImageY());
        vueActive.repaint();
    }
}
