package ets.commande;

import ets.Perspective;
import ets.gui.PanneauPrincipal;

/**
 * Commande Zoom : effectue un zoom selon l'echelle envoyee en parametre
 *                 sur la gui choisie par l'utilisateur.
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-29
 */
public class Zoom implements InterfaceCommande {

    /**
     * Perspective de la gui
     */
    private Perspective perspective;
    /**
     * Echelle de la perspective prenant des valeurs de 1 à l'infini
     */
    private int echelle;

    /**
     * Constructeur de la classe Zoom
     * @param echelle
     */
    public Zoom(int echelle){
        this.perspective = PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive();
        this.echelle = echelle;
    }

    /**
     * Fonction permettant d'augmenter l'echelle de la perspective de la gui active de 1
     */
    @Override
    public void executer() {
        perspective.setEchelle(perspective.getEchelle() + echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }

    /**
     * Fonction permettant de diminuer l'echelle de la perspective de la gui active de 1
     */
    @Override
    public void defaire() {
        perspective.setEchelle(perspective.getEchelle() - echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }
}
