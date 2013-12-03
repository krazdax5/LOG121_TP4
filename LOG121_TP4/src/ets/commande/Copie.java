package ets.commande;

import ets.gui.VueActive;
import ets.gui.PanneauPrincipal;

/**
 * Commande Copier :    le constructeur garde en memoire la perspective a coller.
*                       la methode executer fait la commande coller
 *                      la methode defaire defait la commande.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance
 * 2013-11-25
 */
public class Copie implements InterfaceCommande {

    /**
     * Vue active
     */
    private VueActive vueActive;
    /**
     * Echelle initiale de la gui active
     */
    private int echelleInitiale;
    /**
     * Offset en X initial de la gui active
     */
    private int offsetXInitial;
    /**
     * Offset en Y initial de la gui active
     */
    private int offsetYInitial;
    /**
     * Entier représentant l'echelle de la perspective a copier
     */
    private int echelle;
    /**
     * Offset en X de la perspective a copier
     */
    private int offsetX;
    /**
     * Offset en Y de la perspective a copier
     */
    private int offsetY;


    /**
     * Constructeur de la classe Copie
     */
    public Copie() {
        this.echelle = PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive().getEchelle();
        this.offsetX = PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive().getCornerImageX();
        this.offsetY = PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive().getCornerImageY();
    }

    /**
     * Fonction permettant d'executer la fonction coller sur la gui chosie
     */
    @Override
    public void executer() {
        this.vueActive = PanneauPrincipal.getPanneauPrincipal().getVueChoisie();
        this.echelleInitiale = vueActive.getPerspectiveVueActive().getEchelle();
        this.offsetXInitial = vueActive.getPerspectiveVueActive().getCornerImageX();
        this.offsetYInitial = vueActive.getPerspectiveVueActive().getCornerImageY();
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive().setEchelle(echelle);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive().setCornerPerspective(offsetX, offsetY);
        PanneauPrincipal.getPanneauPrincipal().getVueChoisie().repaint();
    }

    /**
     * Fonction permettant de revenir à la perspective antérieure de la gui active
     */
    @Override
    public void defaire() {
        vueActive.getPerspectiveVueActive().setEchelle(echelleInitiale);
        vueActive.getPerspectiveVueActive().setCornerPerspective(offsetXInitial,
                offsetYInitial);
        vueActive.repaint();
    }
}
