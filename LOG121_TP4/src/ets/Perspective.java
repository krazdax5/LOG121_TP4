package ets;

import java.io.Serializable;
import java.util.Observable;

/**
 * Classe qui permet la gestion des perspectives sur les images.
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-26
 */
public class Perspective extends Observable implements Serializable{

    /**
     * Deplacement en X de la perspective de la gui active par rapport à la perspective initiale de celle-ci.
     */
    private int offsetX;
    /**
     * Deplacement en Y de la perspective de la gui active par rapport à la perspective initiale de celle-ci.
     */
    private int offsetY;
    /**
     * Echelle de la perspective de la gui active
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
     * @param offsetX Decalement en x
     * @param offsetY Decalement en y
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
     * Cette méthode va mettre en gui active ce qui est inclus dans rectangleVue
     * @param nouvelleEchelle Nouvelle echelle de la perspecive
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
     * @return echelle de la perspective
     */
    public int getEchelle(){
        return echelle;
    }

    /**
     * Methode qui retourne le type de l'objet sous forme de chaine de caracteres.
     * Est utilise lors de la differenciation des objets observable recu en parametre.
     * @return  le type de l'objet selon la classe
     */
    public String toString() {
        return "perspective";
    }
}