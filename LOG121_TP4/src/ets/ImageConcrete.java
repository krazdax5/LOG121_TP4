package ets;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Patron : NullObject
 *
 *          Historique des modifications
 ***************************************************
 * @author Charles Levesque
 * 2013-11-
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 Ajout de la methode setImage() et rendre Observable
 */
public class ImageConcrete extends Observable {

    private Image theImage = null;

    /**
     * Simple accesseur d'une image.
     * @return L'image de l'API Java standard.
     */
    public Image getTheImage() {
        return theImage;
    }

    /**
     * M&eactue;thode priv&eacute;
     */
    private ImageConcrete(File sourceImage){
        if(sourceImage != null) {
            try{
                theImage = ImageIO.read(sourceImage);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Erreur lors de la lecture du fichier.");
            }
        }
    }

    public static ImageConcrete createImage(File sourceImage) {
        return new ImageConcrete(sourceImage);
    }

    public void setImage(File sourceImage) {
        theImage = new ImageConcrete(sourceImage).getTheImage();
        this.setChanged();
        this.notifyObservers();
    }

    public String toString() {
        return "image";
    }

}
