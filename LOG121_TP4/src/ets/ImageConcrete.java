package ets;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Classe qui garde en memoire l'image utilisee par l'application.
 * Est observee par la fenetre principale, le panneau principal et toutes les vues.
 *
 *          Historique des modifications
 ***************************************************
 * @author Charles Levesque
 * 2013-11-
 * @author Mathieu Lachance LACM14059305
 * 2013-11-25 Ajout de la methode setImage() et rendre Observable
 */
public class ImageConcrete extends Observable {

    /**
     * Attribut privee de l'image.
     */
    private Image theImage;

    /**
     * Simple accesseur d'une image.
     * @return L'image de l'API Java standard.
     */
    public Image getTheImage() {
        return theImage;
    }

    /**
     * Constructeur prive
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

    /**
     * Methode fabrique qui permet la creation d'une image.
     * @param sourceImage Le fichier contenant l'image
     * @return  La nouvelle instance d'ImageConcrete
     */
    public static ImageConcrete createImage(File sourceImage) {
        return new ImageConcrete(sourceImage);
    }

    /**
     * Mutateur de l'image selon un fichier recu en parametre
     * @param sourceImage   Le fichier contenant l'image.
     */
    public void setImage(File sourceImage) {
        theImage = new ImageConcrete(sourceImage).getTheImage();
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Methode qui retourne le type de l'objet sous forme de chaine de caracteres.
     * Est utilise lors de la differenciation des objets observable recu en parametre.
     * @return  le type de l'objet selon la classe
     */
    public String toString() {
        return "image";
    }

}
