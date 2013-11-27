package ets.gui;

import ets.ImageConcrete;
import ets.VueActive1;
import ets.VueActive2;
import ets.VueReduite;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: lucie
 * Date: 2013-11-26
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public class PanneauPrincipal extends JPanel implements Observer {

    private static PanneauPrincipal instance;

    JTabbedPane tabbedPane;

    VueActive1 vueActive1;
    VueActive2 vueActive2;
    VueReduite vueReduite;

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


        vueActive1 = new VueActive1();

//        vueActive1.setPreferredSize(dimensionImage);

        tabbedPane.addTab("Vue Active 1", null, vueActive1, "Vue Active 1");

        vueActive2 = new VueActive2();

        tabbedPane.addTab("Vue Active 2", null, vueActive2, "Vue Active 2");


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