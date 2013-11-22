package ets;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Patron : NullObject
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class ImageConcrete implements IImage {
    private Image theImage = null;
    private String imageMeganeFox = "/Users/charleslevesque/git_workspace/LOG121_TP4/LOG121_TP4/src/ets/res/img/megan_fox.png";

    /**
     * Simple accesseur d'un image.
     * @return L'image de l'API Java standard.
     */
    public Image getTheImage() {
        return theImage;
    }

    /**
     * M&eactue;thode priv&eacute;
     */
    private ImageConcrete(){
        try{
            File sourceImage = new File(imageMeganeFox);
            theImage = ImageIO.read(sourceImage);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static ImageConcrete createImage() {
        return new ImageConcrete();
    }
}
