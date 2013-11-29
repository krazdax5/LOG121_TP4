package ets.gui;


import ets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe permettant de gerer la fenetre principale de l'application avec son MenuBar
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance
 * 2013-11-19 : Début de l'implémentation
 *              Ajout des classes main() et run()
 *              Ajout du JMenuMenuBar (classe interne MenuBar)
 * 2013-11-21 : Ajout du sous-menu "Edition"
 *              Ajout du sous-menu "Vue"
 */
public class FenetrePrincipale implements Runnable, Observer {

    private static FenetrePrincipale instance;

    JFrame application;

    PanneauPrincipal panneauPrincipal;

//    ImageConcrete imageConcrete;

//    Controlleur controlleur = Controlleur.getControlleur();

    private FenetrePrincipale() {}

    public static FenetrePrincipale getFenetrePrincipale() {
        if(instance == null)
            instance = new FenetrePrincipale();

        return instance;
    }


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
