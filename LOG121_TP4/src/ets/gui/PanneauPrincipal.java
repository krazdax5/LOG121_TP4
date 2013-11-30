package ets.gui;

import ets.*;

import javax.swing.*;
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


        String workingDir = System.getProperty("user.dir");

        ImageConcrete imageInitiale = ImageConcrete.createImage(workingDir+"/src/ets/res/img/image1.jpg");

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

                Controlleur.getControlleur().deplacer(instanceVueActive1.getPerspectiveVueActive1(),
                        (int)mouseEvent.getPoint().getX(),(int)mouseEvent.getPoint().getY());

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
                //To change body of implemented methods use File | Settings | File Templates.
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

        this.add(tabbedPane);

//            this.add(tabbedPane);
//            this.add(tabbedPane1);

//            this.setLayout(new BorderLayout());
//
//            this.setBackground(Color.LIGHT_GRAY);
//
//
//            vueActive1 = new JPanel();
//
//            vueActive1.setBackground(Color.BLACK);
//
//            Dimension dimEssai = new Dimension();
//            dimEssai.setSize(400,400);
//            vueActive1.setPreferredSize(dimEssai);
//
//
//            this.add(vueActive1);
//
//            vueActive2 = new JPanel();
//
//            vueActive2.setBackground(Color.GRAY);
//
//            vueActive2.setPreferredSize(dimEssai);
//
//
//            this.add(vueActive2);
//
////            JPanel vueReduite = new JPanel();
//
//            imageInitiale = ImageConcrete.createImage("/Users/Mathieu/Desktop/Automne 2013.png");
//
//            vueReduite = new VueReduite(imageInitiale.getTheImage(), 125, 125);
//
//            imageInitiale.addObserver(vueReduite);
//
//            vueReduite.setBackground(Color.WHITE);
//
//            Dimension dimReduite = new Dimension();
//            dimReduite.setSize(125, 125);
//
//            vueReduite.setPreferredSize(dimReduite);
//
//
//            this.add(vueReduite);

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