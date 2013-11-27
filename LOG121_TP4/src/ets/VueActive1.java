package ets;


import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-26
 */
public class VueActive1 extends JPanel implements Observer {

    private Image imageVueActive1;
    private Perspective perspective;
    private int hauteur;
    private int largeur;

    @Override
    public void update(Observable observable, Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
        ImageConcrete image = (ImageConcrete) observable;
        this.setVueActive1(image.getTheImage());
    }

    /**
     * Constructeur par default de VueActive1
     */
    public VueActive1(){}

    /**
     * Constructeur
     * @param image
     * @param largeur
     * @param hauteur
     */
    public VueActive1(Image image, int largeur, int hauteur){
        this.imageVueActive1 = image;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    /**
     * Setter de la vueActive1
     * @param image
     */
    public void setVueActive1(Image image){
        this.imageVueActive1 = image;
        this.hauteur = image.getHeight(null);
        this.largeur = image.getWidth(null);
    }

    /**
     * Permet de dessiner plein écran la vue active selon la perspective
     * @param g
     */
    public void paintComponent(Graphics g){
        g.drawImage(imageVueActive1, (int)perspective.getCornerImage().getX(), (int)perspective.getCornerImage().getY(),
                (int)(perspective.getCornerImage().getX()+largeur)*perspective.getEchelle(),
                (int)(perspective.getCornerImage().getY()+hauteur)*perspective.getEchelle(), 0, 0 , largeur, hauteur, null);

    }


}
