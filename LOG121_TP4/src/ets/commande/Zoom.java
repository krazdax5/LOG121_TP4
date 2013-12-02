package ets.commande;

import ets.Perspective;
import ets.gui.PanneauPrincipal;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-12-02
 */
public class Zoom implements InterfaceCommande {

    /**
     * Perspective de la vue
     */
    private Perspective perspective;
    /**
     * Echelle de la perspective prenant des valeurs de 1 à l'infini
     */
    private int echelle;

    /**
     * Constructeur de la classe Zoom
     * @param perspective
     * @param echelle
     */
    public Zoom(Perspective perspective,int echelle){
        this.perspective = perspective;
        this.echelle = echelle;
    }

    /**
     * Fonction permettant d'augmenter l'echelle de la perspective de la vue active de 1
     */
    @Override
    public void executer() {
        perspective.setEchelle(perspective.getEchelle() + echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }

    /**
     * Fonction permettant de diminuer l'echelle de la perspective de la vue active de 1
     */
    @Override
    public void defaire() {
        perspective.setEchelle(perspective.getEchelle() - echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }
}
