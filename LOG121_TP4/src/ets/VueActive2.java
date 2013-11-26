package ets;


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
public class VueActive2 implements Observer {

    private Image imageVueActive2;
    private Perspective perspective;
    private int hauteur;
    private int largeur;

    @Override
    public void update(Observable observable, Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
        ImageConcrete image = (ImageConcrete) observable;
        this.setVueActive2(image.getTheImage());
    }

    /**
     * Constructeur par default
     */
    public VueActive2(){}

    /**
     * Constructeur
     * @param image
     * @param largeur
     * @param hauteur
     */
    public VueActive2(Image image, int largeur, int hauteur){
        this.imageVueActive2 = image;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    /**
     * Setter de vueActive2
     * @param image
     */
    public void setVueActive2(Image image){
        this.imageVueActive2 = image;
        this.hauteur = image.getHeight(null);
        this.largeur = image.getWidth(null);
    }

    /**
     * Permet le dessin de vueActive2 selon la perspective
     * @param g
     */
    public void paintComponent(Graphics g){
        g.drawImage(imageVueActive2, (int)perspective.getCornerImage().getX(), (int)perspective.getCornerImage().getY(),
                (int)(perspective.getCornerImage().getX()+largeur)*perspective.getEchelle(),
                (int)(perspective.getCornerImage().getY()+hauteur)*perspective.getEchelle(), 0, 0 , largeur, hauteur, null);

    }


}
