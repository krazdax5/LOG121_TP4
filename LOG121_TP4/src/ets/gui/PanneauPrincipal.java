package ets.gui;

import ets.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class PanneauPrincipal extends JPanel implements Observer {

    private static PanneauPrincipal instance;

    JTabbedPane tabbedPane;

    public static VueActive1 instanceVueActive1;
    protected VueActive2 vueActive2;
    protected VueReduite vueReduite;

    public static PanneauPrincipal getPanneauPrincipal() {
        if(instance == null)
            instance = new PanneauPrincipal();

        return instance;
    }

    private PanneauPrincipal() {


        tabbedPane = new JTabbedPane();

        ImageConcrete imageInitiale;

        String os = System.getProperty("os.name").toLowerCase();

        String workingDir = System.getProperty("user.dir");

        if(os.equals("mac os x")){

            imageInitiale = ImageConcrete.createImage(workingDir+"/src/ets/res/img/megan_fox.jpg");
        }else {
            workingDir.replace("\\", "\\\\");
            imageInitiale = ImageConcrete.createImage(workingDir+"\\LOG121_TP4\\src\\ets\\res\\img\\megan_fox.jpg");
        }

        int hauteur = imageInitiale.getTheImage().getHeight(null);
        int largeur = imageInitiale.getTheImage().getWidth(null);

        Dimension dimensionImage = new Dimension(largeur, hauteur);

        this.setSize(dimensionImage);
        this.setPreferredSize(dimensionImage);
        this.setMaximumSize(dimensionImage);
        this.setMinimumSize(dimensionImage);

        vueReduite = VueReduite.getVueReduite();

        vueReduite.setImageReduite(imageInitiale.getTheImage());


        vueReduite.setPreferredSize(dimensionImage);


        tabbedPane.addTab("Vue Reduite", null, vueReduite,
                "Vue Reduite");


        //******* Debut vue active 1 **********
        instanceVueActive1 = new VueActive1(imageInitiale.getTheImage());

        instanceVueActive1.setPreferredSize(dimensionImage);
        instanceVueActive1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
//                Controlleur.getControlleur().deplacer(instanceVueActive1.getPerspectiveVueActive1(),
//                        mouseEvent.getX()-instanceVueActive1.getPerspectiveVueActive1().getCornerImageX(),mouseEvent.getY()-instanceVueActive1.getPerspectiveVueActive1().getCornerImageY());
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        instanceVueActive1.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                Controlleur.getControlleur().zoomer(instanceVueActive1.getPerspectiveVueActive1(), mouseWheelEvent.getWheelRotation());

            }
        });

        instanceVueActive1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                instanceVueActive1.setCursor(cursor);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        tabbedPane.addTab("Vue Active 1", null, instanceVueActive1, "Vue Active 1");
        //****** Fin vue active 1 ***********

       //******* Debut vue active 2 **********

        vueActive2 = new VueActive2();

        vueActive2.setVueActive2(imageInitiale.getTheImage());

        vueActive2.setPreferredSize(dimensionImage);

        tabbedPane.addTab("Vue Active 2", null, vueActive2, "Vue Active 2");
        // ******** Fin vue active 2 *******


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if(tabbedPane.getSelectedComponent().equals(instanceVueActive1)){
                    instanceVueActive1.setActive(true);
                    vueActive2.setActive(false);
                }
                if(tabbedPane.getSelectedComponent().equals(vueActive2)) {
                    vueActive2.setActive(true);
                    instanceVueActive1.setActive(false);
                }
                if(tabbedPane.getSelectedComponent().equals(vueReduite)) {
                    vueActive2.setActive(false);
                    instanceVueActive1.setActive(false);
                }
            }
        });

        this.add(tabbedPane);


    }

    @Override
    public void update(Observable observable, Object o) {

        ImageConcrete image = (ImageConcrete) observable;

        Dimension nouvelleDimension = new Dimension(image.getTheImage().getWidth(null)+25,
                image.getTheImage().getHeight(null)+25);

        vueReduite.setSize(nouvelleDimension);
        vueReduite.setMinimumSize(nouvelleDimension);
        vueReduite.setMaximumSize(nouvelleDimension);
        vueReduite.setPreferredSize(nouvelleDimension);

        tabbedPane.setSize(nouvelleDimension);
        tabbedPane.setMinimumSize(nouvelleDimension);
        tabbedPane.setMaximumSize(nouvelleDimension);
        tabbedPane.setPreferredSize(nouvelleDimension);

        this.setSize(nouvelleDimension);
        this.setMinimumSize(nouvelleDimension);
        this.setMaximumSize(nouvelleDimension);
        this.setPreferredSize(nouvelleDimension);

        tabbedPane.repaint();

        vueReduite.repaint();

        this.repaint();

    }
}