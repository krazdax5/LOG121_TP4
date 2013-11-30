package ets.commande;

import ets.Controlleur;
import ets.Perspective;
import ets.VueActive1;
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
        PanneauPrincipal.instanceVueActive1.repaint();
    }

    @Override
    public void defaire() {
        perspective.setEchelle(perspective.getEchelle() - echelle);
        PanneauPrincipal.instanceVueActive1.repaint();
    }
}
