package ets;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;

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
public class ImageConcrete extends Observable implements IImage {

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
    private ImageConcrete(String adresse){
        try{
            File sourceImage = new File(adresse);
            theImage = ImageIO.read(sourceImage);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static ImageConcrete createImage(String adresse) {
        return new ImageConcrete(adresse);
    }

    public void setImage(String adresse) {
        theImage = new ImageConcrete(adresse).getTheImage();
        this.setChanged();
        this.notifyObservers();
    }

}
