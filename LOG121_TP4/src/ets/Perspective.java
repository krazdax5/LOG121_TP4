package ets;

import java.io.Serializable;
import java.util.Observable;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-26
 */
public class Perspective extends Observable implements Serializable{

    /**
     * Deplacement en X de la perspective de la vue active par rapport à la perspective initiale de celle-ci.
     */
    private int offsetX;
    /**
     * Deplacement en Y de la perspective de la vue active par rapport à la perspective initiale de celle-ci.
     */
    private int offsetY;
    /**
     * Echelle de la perspective de la vue active
     */
    private int echelle;

    /**
     * Constructeur de perspective
     */
    public Perspective(){
        echelle = 1;
        offsetX = 0;
        offsetY = 0;
    }

    /**
     * Change la valeur du coin de la perspective
     * @param offsetX
     * @param offsetY
     */
    public void setCornerPerspective(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        setChanged();
        notifyObservers();
    }

    /**
     * Retourne la position du coin de la perspective
     * @return centrePerspective
     */
    public int getCornerImageX(){

        return offsetX;
    }
    /**
     * Retourne la position du coin de la perspective
     * @return centrePerspective
     */
    public int getCornerImageY(){

        return offsetY;
    }

    /**
     * Cette méthode va mettre en vue active ce qui est inclus dans rectangleVue
     * @param nouvelleEchelle
     */
    public void setEchelle(int nouvelleEchelle){
        if(nouvelleEchelle > 0) {
            echelle = nouvelleEchelle;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Permet d'accéder à l'échelle actuelle de la perspective
     * @return echelle
     */
    public int getEchelle(){
        return echelle;
    }

    /**
     * Méthode toString qui affiche "perspective" lors de l'appel
     * @return
     */
    public String toString() {
        return "perspective";
    }
}