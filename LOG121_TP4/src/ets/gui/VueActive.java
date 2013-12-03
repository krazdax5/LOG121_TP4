package ets.gui;

import ets.Controleur;
import ets.ImageConcrete;
import ets.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
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
public class VueActive extends JPanel implements Observer {

    /**
     * Boolean permettant de connaître quel gui est active.
     * Si le boolean est à "true" la gui est active
     */
    private boolean estActive;
    /**
     * Image de la gui active
     */
    private ImageConcrete imageVueActive;
    /**
     * Perspective de la gui active
     */
    private Perspective perspective;

    /**
     * Met à jour la gui active lorsque l'objet obsevable remarque un changement dans l'état de la gui active
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        if(observable.toString().equals("image")) {
            ImageConcrete image = (ImageConcrete) observable;
            this.setImage(image);
            this.repaint();
        }
        if(observable.toString().equals("perspective")) {
            this.perspective = (Perspective) observable;
            this.repaint();
        }
    }

    /**
     * Constructeur de la classe VueActive
     * @param image
     */
    public VueActive(ImageConcrete image){
        this.imageVueActive = image;
        this.perspective = new Perspective();
        this.perspective.addObserver(this);

        int hauteur = imageVueActive.getTheImage().getHeight(null);
        int largeur = imageVueActive.getTheImage().getWidth(null);
        Dimension dimensionImage = new Dimension(largeur, hauteur);

        this.setPreferredSize(dimensionImage);

        MouseAdapter myMouseAdapter = new MouseAdapter() {

            int premierX;
            int premierY;


            /**
             * Permet de mettre en mémoire la position lorsque la souris pèse sur la perspective
             * @param mouseEvent
             */
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                premierX = perspective.getCornerImageX() - mouseEvent.getX();
                premierY = perspective.getCornerImageY() - mouseEvent.getY();

                setPosition(mouseEvent);
            }

            /**
             * Change la position de la perspective lorsque la souris dragged l'image
             * @param mouseEvent
             */
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                setPosition(mouseEvent);
            }

            /**
             * Change l'échelle de la perspective lors du WheelMoved de la souris
             * @param mouseWheelEvent
             */
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                Controleur.getControleur().zoomer(mouseWheelEvent.getWheelRotation());

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                setCursor(cursor);
            }

            /**
             * Setter permettant de placer la position de la perspective
             * @param mouseEvent
             */
            public void setPosition(MouseEvent mouseEvent) {
                Controleur.getControleur().deplacer(premierX+mouseEvent.getX(),premierY+mouseEvent.getY());
                repaint();
            }
        };

        this.addMouseListener(myMouseAdapter);
        this.addMouseMotionListener(myMouseAdapter);
        this.addMouseWheelListener(myMouseAdapter);
    }

    /**
     * Getter de la Perspective de la gui active
     * @return
     */
    public Perspective getPerspectiveVueActive(){
        return perspective;
    }
    /**
     * Setter de la vueActive
     * @param image
     */
    public void setImage(ImageConcrete image){
        this.imageVueActive = image;
        this.perspective = new Perspective();
    }

    /**
     * Setter permettant de définir qu'elle gui est active
     * @param estActive
     */
    public void setActive(boolean estActive) {
        this.estActive = estActive;
    }

    /**
     * Rend la gui ayant appeller cette fonction active
     * @return
     */
    public boolean estActive() {
        return estActive;
    }

    /**
     * Permet de dessiner la gui active par rapport à la perspective de celle-ci
     * @param g
     */
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(imageVueActive.getTheImage(),perspective.getCornerImageX(), perspective.getCornerImageY(),
                imageVueActive.getTheImage().getWidth(null)*perspective.getEchelle()+perspective.getCornerImageX(),
                imageVueActive.getTheImage().getHeight(null)*perspective.getEchelle()+perspective.getCornerImageY(),0,0, imageVueActive.getTheImage().getWidth(null),
                imageVueActive.getTheImage().getHeight(null), null);
        g.drawImage(VueOriginale.getVueOriginale().getImageOriginale().getTheImage(), this.getWidth() - 90, 15, 75, 75, null);
        g.drawRect(this.getWidth()-90,15,75,75);
    }

}
