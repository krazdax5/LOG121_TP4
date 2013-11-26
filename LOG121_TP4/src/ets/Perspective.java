package ets;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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

    private ImageConcrete image;
    private Point2D offset;
    private Rectangle2D rectangleVue;
    private int echelle;
    private final int GRANDEUR_HORIZONTAL_IMAGE = 200;
    private final int GRANDEUR_VERTICAL_IMAGE = 150;

    /**
     * Constructeur de perspective
     */
    public Perspective(){
        echelle = 1;
        offset.setLocation(0,0);
    }

    /**
     * Change la valeur du centre de la perspective
     * @param corner
     */
    public void setCornerPerspective(Point2D corner){
        offset.setLocation(corner.getX(),corner.getY());
        setChanged();
        notifyObservers();
    }

    /**
     * Retourne la position du centre de la perspective
     * @return centrePerspective
     */
    public Point2D getCornerImage(){
        return offset;
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
}