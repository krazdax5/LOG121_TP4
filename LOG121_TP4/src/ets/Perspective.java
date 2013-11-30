package ets;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Observable;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-26
 */
public class Perspective extends Observable {


    private Point2D offset;
    private int offsetX;
    private int offsetY;
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
        echelle = nouvelleEchelle;
        setChanged();
        notifyObservers();
    }

    /**
     * Permet d'accéder à l'échelle actuelle de la perspective
     * @return echelle
     */
    public int getEchelle(){
        return echelle;
    }

    public String toString() {
        return "perspective";
    }
}