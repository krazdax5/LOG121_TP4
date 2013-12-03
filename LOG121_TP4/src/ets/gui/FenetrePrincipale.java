package ets.gui;


import ets.ImageConcrete;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe permettant de gerer la fenetre principale de l'application avec son MenuBar
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance
 * 2013-11-19 : Début de l'implémentation
 */
public class FenetrePrincipale implements Runnable, Observer {

    /**
     * Attribut singleton qui refere a l'instance simple de la fenetre principale
     */
    private static FenetrePrincipale instance;

    /**
     * JFrame representant l'application même.
     */
    private JFrame application;

    /**
     * Le panneau principal de l'application contenant toutes les vues.
     */
    private PanneauPrincipal panneauPrincipal;

    /**
     * Constructeur prive de l'application.
     */
    private FenetrePrincipale() {}

    /**
     * Methode singleton qui retourne l'instance simple de la fenetre principale.
     * @return l'instance simple de la fenetre principale.
     */
    public static FenetrePrincipale getFenetrePrincipale() {
        if(instance == null)
            instance = new FenetrePrincipale();

        return instance;
    }


    /**
     * Methode run qui permet de rouler l'application. Elle agit aussi en temps que constructeur de l'application.
     */
    @Override
    public void run() {

        application = new JFrame();

        application.setTitle("Application Images TP4");

        application.setJMenuBar(new MenuBar());

        panneauPrincipal = PanneauPrincipal.getPanneauPrincipal();
        application.setContentPane(panneauPrincipal);

        application.getContentPane().getSize();

        int hauteur = application.getContentPane().getHeight()+125;
        int largeur = application.getContentPane().getWidth()+50;

        Dimension dimensionFenetre = new Dimension(largeur,hauteur);

        application.setPreferredSize(dimensionFenetre);
        application.setMaximumSize(dimensionFenetre);
        application.setMinimumSize(dimensionFenetre);


        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.setVisible(true);

    }

    /**
     * Methode update qui permet a la fenetre principale de se mettre a jour
     * lorsque l'image change.
     * @param observable L'objet observe par la fenetre principale.
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        ImageConcrete image = (ImageConcrete) observable;
        Dimension dimensionNouvelleImage = new Dimension(image.getTheImage().getWidth(null)+50,
                image.getTheImage().getHeight(null)+75);

        application.setSize(dimensionNouvelleImage);
        application.setMinimumSize(dimensionNouvelleImage);
        application.setMaximumSize(dimensionNouvelleImage);
        application.setPreferredSize(dimensionNouvelleImage);

        application.repaint();

        application.pack();
    }

}
