package ets;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.RescaleOp;
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
    private static VueActive1 instance;

    @Override
    public void update(Observable observable, Object o) {
        ImageConcrete image = (ImageConcrete) observable;
        this.setVueActive1(image.getTheImage());
    }

    /**
     * Constructeur par default de VueActive1
     */
    public VueActive1(){
        this.perspective = new Perspective();
        this.perspective.addObserver(this);
    }

    /**
     * Constructeur
     * @param image
     */
    private VueActive1(Image image){
        this.imageVueActive1 = image;
        this.perspective = new Perspective();
        this.perspective.addObserver(this);
    }

    public void setPerspective(Perspective perspective){
        this.perspective = perspective;
        this.repaint();
    }

    public static VueActive1 getVueActive1(){
            if(instance == null){
                instance = new VueActive1(null);
            }
        return instance;
    }

    public Perspective getPerspectiveVueActive1(){
        return this.perspective;
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

        try{
            g.drawImage(imageVueActive1,perspective.getCornerImageX(),perspective.getCornerImageY(),imageVueActive1.getWidth(null)*perspective.getEchelle(), imageVueActive1.getHeight(null)*perspective.getEchelle(),0,0,imageVueActive1.getWidth(null), imageVueActive1.getHeight(null), null);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


}
