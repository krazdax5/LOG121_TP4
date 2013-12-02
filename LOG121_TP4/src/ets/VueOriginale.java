package ets;

import javax.swing.*;
import java.awt.*;
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
 * 2013-11-25 : Ajout des methodes setImageOriginale et paintComponent()
 *              Rendre la classe Observer
 */
public class VueOriginale extends JPanel implements Observer {

    /**
     * Image Concrete de la vue originale
     */
    private ImageConcrete imageOriginale;
    /**
     * Largeur de l'image original
     */
    private int largeur;
    /**
     * Hauteur de l'image original
     */
    private int hauteur;

    /**
     * Instance de la vue original
     */
    public static VueOriginale instance;

    /**
     * Constructeur qui permet de passer en parametre l'image que l'on veut changer,
     * avec les dimensions desirees.
     * @param image L'image que l'on veut reduire
     * @param largeur Largeur que l'on veut attribuer a l'image
     * @param hauteur Longueur que l'on veut attribuer a l'image
     */
    private VueOriginale(ImageConcrete image, int largeur, int hauteur){

        /**
         * Image de la vue original
         */
        this.imageOriginale = image;
        this.largeur = largeur;
        this.hauteur = hauteur;

    }

    /**
     * Getter de la vue originale
     * @return
     */
    public static VueOriginale getVueOriginale() {
        if(instance == null) {
            instance = new VueOriginale(null,1,1);
        }

        return instance;
    }

    /**
     * Setter de l'image originale
     * @param image
     */
    public void setImageOriginale(ImageConcrete image) {
        this.imageOriginale = image;
        this.largeur = image.getTheImage().getWidth(null);
        this.hauteur = image.getTheImage().getHeight(null);
    }

    /**
     * Getter de l'image originale
     * @return
     */
    public ImageConcrete getImageOriginale() {
        return imageOriginale;
    }

    /**
     * Permet de dessinner l'image original
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageOriginale.getTheImage(),0,0,largeur,hauteur,null);

    }

    /**
     * Permet d'afficher les changements s'il y en a qui surviennent sur la vue original
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        ImageConcrete image = (ImageConcrete) observable;
        this.setImageOriginale(image);
    }
}
