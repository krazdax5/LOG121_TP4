package ets;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Patron : Immutable
 *
 *          Historique des modifications
 ***************************************************
 * @author Charles Levesque
 * 2013-11-
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 : Ajout des methodes setImageReduite et paintComponent()
 *              Rendre la classe Observer
 */
public class VueReduite  extends JPanel implements Observer {

    private Image imageReduite;
    private int largeur;
    private int hauteur;

    /**
     * Constructeur qui permet de passer en parametre l'image que l'on veut changer,
     * avec les dimensions desirees.
     * @param image L'image que l'on veut reduire
     * @param largeur Largeur que l'on veut attribuer a l'image
     * @param hauteur Longueur que l'on veut attribuer a l'image
     */
    public VueReduite (Image image, int largeur, int hauteur){

        this.imageReduite = image;
        this.largeur = largeur;
        this.hauteur = hauteur;

    }

    public void setImageReduite(Image image) {
        this.imageReduite = image;
        this.largeur = image.getWidth(null);
        this.hauteur = image.getHeight(null);
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(imageReduite,0,0,hauteur, largeur/*,0,0,largeur*2,hauteur*2*/,null);

    }

    @Override
    public void update(Observable observable, Object o) {
        ImageConcrete image = (ImageConcrete) observable;
        this.setImageReduite(image.getTheImage());
    }
}
