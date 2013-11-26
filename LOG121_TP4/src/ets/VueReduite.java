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


//        BufferedImage buf = new BufferedImage(largeur,hauteur, BufferedImage.TYPE_INT_ARGB);
//
//        Graphics2D g = buf.createGraphics();
//
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g.drawImage(image,0,0,largeur,hauteur,null);


//
//        BufferedImage buf = new BufferedImage(largeur-10,hauteur-10, Image.SCALE_SMOOTH);
//
//        ImageIcon icon = new ImageIcon(buf);
//
//        label.setIcon(icon);

//        JLabel label = new JLabel(new ImageIcon(image));
//
//        Dimension dim = new Dimension(largeur-10,hauteur-10);
//
//        label.setMaximumSize(dim);
//        label.setMinimumSize(dim);
//        label.setPreferredSize(dim);
//
//
//        this.add(label);
//        this.setVisible(true);

    }

    public void setImageReduite(Image image) {
        this.imageReduite = image;
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(imageReduite,0,0,largeur,hauteur,null);

    }

    @Override
    public void update(Observable observable, Object o) {
        ImageConcrete image = (ImageConcrete) observable;
        this.setImageReduite(image.getTheImage());
    }
}
