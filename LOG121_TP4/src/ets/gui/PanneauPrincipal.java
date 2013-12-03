package ets.gui;

import ets.ImageConcrete;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe qui s'occupe de la gestion du panneau principal de l'application
 * Il est compose de plusieurs vue active et d'une vue reduite/originale.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-21 : Ajout du JMenuMenuBar (classe interne MenuBar)
 *              Ajout du sous-menu "Edition"
 *              Ajout du sous-menu "Vue"
 */
public class PanneauPrincipal extends JPanel implements Observer {

    /**
     * Attribut singleton qui consiste en l'instance simple du panneau principal.
     */
    private static PanneauPrincipal instance;

    /**
     * Le panneau qui permet la gestion du layout.
     */
    private JTabbedPane tabbedPane;

    /**
     * La vue active 1
     */
    protected VueActive vueActive1;
    /**
     * La vue active 2
     */
    protected VueActive vueActive2;
    /**
     * La vue originale.
     */
    protected VueOriginale vueOriginale;

    /**
     * Methode singleton qui retourne l'instance du PanneauPrincipal.
     * @return l'instance simple du panneau principal.
     */
    public static PanneauPrincipal getPanneauPrincipal() {
        if(instance == null)
            instance = new PanneauPrincipal();

        return instance;
    }

    /**
     * Constructeur du Panneau Principal
     */
    private PanneauPrincipal() {


        tabbedPane = new JTabbedPane();

        ImageConcrete imageInitiale;

        // Les lignes de code suivante ont été inspirées de Mkyong.com
        // Detecte le systeme d'exploitation dans lequel nous sommes.
        // Source : http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/

        String systemeExploitation = System.getProperty("os.name").toLowerCase();

        String repertoireActuel = System.getProperty("user.dir");

        if(systemeExploitation.equals("mac os x")){
            File image = new File(repertoireActuel+"/src/ets/res/img/megan_fox.jpg");
            imageInitiale = ImageConcrete.createImage(image);
        } else {
            repertoireActuel.replace("\\", "\\\\");
            File image = new File(repertoireActuel+"\\src\\ets\\res\\img\\megan_fox.jpg");
            imageInitiale = ImageConcrete.createImage(image);
        }

        // Fin des lignes inspirées

        int hauteur = imageInitiale.getTheImage().getHeight(null);
        int largeur = imageInitiale.getTheImage().getWidth(null);

        Dimension dimensionImage = new Dimension(largeur, hauteur);

        this.setSize(dimensionImage);
        this.setPreferredSize(dimensionImage);
        this.setMaximumSize(dimensionImage);
        this.setMinimumSize(dimensionImage);

        vueOriginale = VueOriginale.getVueOriginale();

        vueOriginale.setImageOriginale(imageInitiale);

        vueOriginale.setPreferredSize(dimensionImage);


        tabbedPane.addTab("Vue Originale", null, vueOriginale,
                "Vue Originale");


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
                if(tabbedPane.getSelectedComponent().equals(vueOriginale)) {
                    vueActive2.setActive(false);
                    vueActive1.setActive(false);
                }
            }
        });

        this.add(tabbedPane);

    }

    /**
     * Accesseur de la vue active 1
     * @return la vue active 1
     */
    public VueActive getVueActive1() {
        return vueActive1;
    }

    /**
     * Accesseur de la vue active 2
     * @return la vue active 2
     */
    public VueActive getVueActive2() {
        return vueActive2;
    }

    /**
     * Accesseur de la vue choisie/selectionnee par l'utilisateur.
     * @return la vue selectionnee
     */
    public VueActive getVueChoisie() {
        if(vueActive1.estActive())
            return vueActive1;
        else if(vueActive2.estActive())
            return vueActive2;
        else {
            JOptionPane.showMessageDialog(null,"La Vue Originale est presentement selectionnee\n" +
                    "La vue active 1 a ete choisie alors...");
            return vueActive1;
        }
    }

    /**
     * Methode qui permet d'update le panneau selon la nouvelle image ouverte par l'utilisateur.
     * @param observable L'objet observe par PanneauPrincipal
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {

        ImageConcrete image = (ImageConcrete) observable;

        Dimension nouvelleDimension = new Dimension(image.getTheImage().getWidth(null)+25,
                image.getTheImage().getHeight(null)+25);

        vueOriginale.setSize(nouvelleDimension);
        vueOriginale.setMinimumSize(nouvelleDimension);
        vueOriginale.setMaximumSize(nouvelleDimension);
        vueOriginale.setPreferredSize(nouvelleDimension);

        tabbedPane.setSize(nouvelleDimension);
        tabbedPane.setMinimumSize(nouvelleDimension);
        tabbedPane.setMaximumSize(nouvelleDimension);
        tabbedPane.setPreferredSize(nouvelleDimension);

        this.setSize(nouvelleDimension);
        this.setMinimumSize(nouvelleDimension);
        this.setMaximumSize(nouvelleDimension);
        this.setPreferredSize(nouvelleDimension);

        tabbedPane.repaint();

        vueOriginale.repaint();

        this.repaint();

    }
}