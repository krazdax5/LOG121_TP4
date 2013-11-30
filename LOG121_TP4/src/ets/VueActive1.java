package ets;


import ets.gui.PanneauPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.RescaleOp;
import java.util.Observable;
import java.util.Observer;


/**
 *          Historique des modifications
 ***************************************************
 * @author Jacob Marcil
 * 2013-11-26
 */
public class VueActive1 extends JPanel implements Observer {

    private boolean estActive;
    private Image imageVueActive1;
    private Perspective perspective;

    @Override
    public void update(Observable observable, Object o) {
        if(observable.toString().equals("image")) {
            ImageConcrete image = (ImageConcrete) observable;
            this.setVueActive1(image.getTheImage());
            this.repaint();
        }
        if(observable.toString().equals("perspective")) {
            Perspective perspective = (Perspective) observable;
            this.perspective = perspective;
            this.repaint();
        }
    }

    /**
     * Constructeur
     * @param image
     */
    public VueActive1(Image image){
        this.imageVueActive1 = image;
        this.perspective = new Perspective();
        this.perspective.addObserver(this);
    }

    public static VueActive1 getVueActive1(){
        return PanneauPrincipal.instanceVueActive1;
    }

    public Perspective getPerspectiveVueActive1(){
        return perspective;
    }
    /**
     * Setter de la vueActive1
     * @param image
     */
    public void setVueActive1(Image image){
        this.imageVueActive1 = image;
        this.perspective.setEchelle(1);
    }

    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
    }

    public void setActive(boolean estActive) {
        this.estActive = estActive;
    }

    public boolean estActive() {
        return estActive;
    }

    /**
     * Permet de dessiner plein écran la vue active selon la perspective
     * @param g
     */
    public void paintComponent(Graphics g){
            g.drawImage(imageVueActive1,perspective.getCornerImageX(),perspective.getCornerImageY(),
                    imageVueActive1.getWidth(null)*perspective.getEchelle(),
                    imageVueActive1.getHeight(null)*perspective.getEchelle(),0,0,imageVueActive1.getWidth(null),
                    imageVueActive1.getHeight(null), null);
    }


}
