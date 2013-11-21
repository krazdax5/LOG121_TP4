package ets;

import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Patron : Immutable
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class VueReduite  {

    /**
     * Constructeur qui permet de passer en parametre l'image que l'on veut changer,
     * avec les dimensions desirees.
     * @param image L'image que l'on veut reduire
     * @param largeur Largeur que l'on veut attribuer a l'image
     * @param longueur Longueur que l'on veut attribuer a l'image
     */
    public VueReduite (Image image, int largeur, int longueur){

        BufferedImage buf = new BufferedImage(largeur,longueur, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = buf.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image,0,0,largeur,longueur,null);
        g.dispose();


    }


    

}
