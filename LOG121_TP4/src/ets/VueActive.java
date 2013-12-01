package ets;


import ets.gui.PanneauPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
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
public class VueActive extends JPanel implements Observer {

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
            this.perspective = (Perspective) observable;
            this.repaint();
        }
    }

    /**
     * Constructeur
     * @param image
     */
    public VueActive(Image image){
        this.imageVueActive1 = image;
        this.perspective = new Perspective();
        this.perspective.addObserver(this);

        int hauteur = imageVueActive1.getHeight(null);
        int largeur = imageVueActive1.getWidth(null);
        Dimension dimensionImage = new Dimension(largeur, hauteur);

        this.setPreferredSize(dimensionImage);

        MouseAdapter myMouseAdapter = new MouseAdapter() {

            int premierX;
            int premierY;


            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                premierX = perspective.getCornerImageX() - mouseEvent.getX();
                premierY = perspective.getCornerImageY() - mouseEvent.getY();

                setPosition(mouseEvent);
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                setPosition(mouseEvent);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                Controlleur.getControlleur().zoomer(perspective, mouseWheelEvent.getWheelRotation());

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                setCursor(cursor);
            }

            public void setPosition(MouseEvent mouseEvent) {
                Controlleur.getControlleur().deplacer(perspective,premierX+mouseEvent.getX(),premierY+mouseEvent.getY());
                repaint();
            }
        };

        this.addMouseListener(myMouseAdapter);
        this.addMouseMotionListener(myMouseAdapter);
        this.addMouseWheelListener(myMouseAdapter);
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
        this.perspective = new Perspective();
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
            g.drawImage(imageVueActive1,perspective.getCornerImageX(), perspective.getCornerImageY(),
                    imageVueActive1.getWidth(null)*perspective.getEchelle()+perspective.getCornerImageX(),
                    imageVueActive1.getHeight(null)*perspective.getEchelle()+perspective.getCornerImageY(),0,0,imageVueActive1.getWidth(null),
                    imageVueActive1.getHeight(null), null);
    }

}
