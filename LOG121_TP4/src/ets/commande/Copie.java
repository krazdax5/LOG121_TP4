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

    private VueActive vueActive;
    private Perspective perspectiveInitiale;
    private int echelle;
    private int offsetX;
    private int offsetY;

    public Copie(Perspective perspective) {
        this.perspectiveInitiale = perspective;
        this.echelle = perspective.getEchelle();
        this.offsetX = perspective.getCornerImageX();
        this.offsetY = perspective.getCornerImageY();
    }

    @Override
    public void executer() {
        this.vueActive = PanneauPrincipal.getPanneauPrincipal().getVueChoisie();
        vueActive.getPerspectiveVueActive1().setEchelle(echelle);
        vueActive.getPerspectiveVueActive1().setCornerPerspective(offsetX, offsetY);
        vueActive.repaint();
    }

    @Override
    public void defaire() {
        vueActive.getPerspectiveVueActive1().setEchelle(perspectiveInitiale.getEchelle());
        vueActive.getPerspectiveVueActive1().setCornerPerspective(perspectiveInitiale.getCornerImageX(),
                perspectiveInitiale.getCornerImageY());
        vueActive.repaint();
    }

}
