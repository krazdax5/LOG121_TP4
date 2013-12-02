package ets.gui;

import ets.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
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

    protected VueActive vueActive1;
    protected VueActive vueActive2;
    protected VueOriginale vueReduite;

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
            File image = new File(workingDir+"/src/ets/res/img/megan_fox.jpg");
            imageInitiale = ImageConcrete.createImage(image);
        }else {
            workingDir.replace("\\", "\\\\");
            File image = new File(workingDir+"\\LOG121_TP4\\src\\ets\\res\\img\\megan_fox.jpg");
            imageInitiale = ImageConcrete.createImage(image);
        }

        int hauteur = imageInitiale.getTheImage().getHeight(null);
        int largeur = imageInitiale.getTheImage().getWidth(null);

        Dimension dimensionImage = new Dimension(largeur, hauteur);

        this.setSize(dimensionImage);
        this.setPreferredSize(dimensionImage);
        this.setMaximumSize(dimensionImage);
        this.setMinimumSize(dimensionImage);

        vueReduite = VueOriginale.getVueOriginale();

        vueReduite.setImageOriginale(imageInitiale);

        vueReduite.setPreferredSize(dimensionImage);


        tabbedPane.addTab("Vue Reduite", null, vueReduite,
                "Vue Reduite");


        //******* Debut vue active 1 **********
        vueActive1 = new VueActive(imageInitiale);

        tabbedPane.addTab("Vue Active 1", null, vueActive1, "Vue Active 1");
        //****** Fin vue active 1 ***********

       //******* Debut vue active 2 **********

        vueActive2 = new VueActive(imageInitiale);

        tabbedPane.addTab("Vue Active 2", null, vueActive2, "Vue Active 2");
        // ******** Fin vue active 2 *******


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if(tabbedPane.getSelectedComponent().equals(vueActive1)){
                    vueActive1.setActive(true);
                    vueActive2.setActive(false);
                }
                if(tabbedPane.getSelectedComponent().equals(vueActive2)) {
                    vueActive2.setActive(true);
                    vueActive1.setActive(false);
                }
                if(tabbedPane.getSelectedComponent().equals(vueReduite)) {
                    vueActive2.setActive(false);
                    vueActive1.setActive(false);
                }
            }
        });

        this.add(tabbedPane);


    }

    public VueActive getVueActive1() {
        return vueActive1;
    }

    public VueActive getVueActive2() {
        return vueActive2;
    }

    public VueActive getVueChoisie() {
        if(vueActive1.estActive())
            return vueActive1;
        else if(vueActive2.estActive())
            return vueActive2;
        else {
            JOptionPane.showMessageDialog(null,"La vue reduite est presentement selectionnee\n" +
                    "La vue active 1 a ete choisie alors...");
            return vueActive1;
        }
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