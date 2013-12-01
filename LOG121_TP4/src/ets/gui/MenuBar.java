package ets.gui;

import ets.Controlleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe qui s'occupe de la gestion du MenuBar
 *
 * Les lignes de codes sur le DefaultToolkit ont été inspirées des notes
 * de cours de M. Pierre Belisle pour le cours INF111.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance
 * 2013-11-
 */
public class MenuBar extends JMenuBar {

    protected MenuBar() {

        JMenu menuFichier = new JMenu();
        menuFichier.setText("Fichier");

        JMenuItem ouvrirImage = new JMenuItem();
        ouvrirImage.setText("Ouvrir Image...");
        ouvrirImage.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        ouvrirImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                final JFileChooser fileChooser = new JFileChooser();

                int valeurRetour = fileChooser.showOpenDialog(null);

                if (valeurRetour == JFileChooser.APPROVE_OPTION) {
                    String imageChoisie = fileChooser.getSelectedFile().getAbsolutePath();
                    Controlleur.getControlleur().changerImage(imageChoisie);
                }
            }
        });

        menuFichier.add(ouvrirImage);

        JMenuItem ouvrirPerspective = new JMenuItem();
        ouvrirPerspective.setText("Ouvrir perspective...");
        ouvrirPerspective.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();

                int valeurRetour = fileChooser.showOpenDialog(null);

                if (valeurRetour == JFileChooser.APPROVE_OPTION) {
                    String perspectiveChoisie = fileChooser.getSelectedFile().getAbsolutePath();
                    Controlleur.getControlleur().changerPerspective(perspectiveChoisie);
                }
            }
        });

        menuFichier.add(ouvrirPerspective);

        JMenuItem enregistrer = new JMenuItem();
        enregistrer.setText("Enregistrer...");
        enregistrer.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        menuFichier.add(enregistrer);

        this.add(menuFichier);

        JMenu menuEdition = new JMenu();
        menuEdition.setText("Edition");

        JMenuItem defaire = new JMenuItem();
        defaire.setText("Defaire...");
        defaire.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));
        defaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().defaire();
            }
        });

        menuEdition.add(defaire);

        JMenuItem refaire = new JMenuItem();
        refaire.setText("Refaire...");
        refaire.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));
        refaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().refaire();
            }
        });

        menuEdition.add(refaire);

        menuEdition.addSeparator();

        JMenuItem copier = new JMenuItem();
        copier.setText("Copier");
        copier.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));
        copier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().ctrlC(null);
            }
        });

        menuEdition.add(copier);

        JMenuItem coller = new JMenuItem();
        coller.setText("Coller");
        coller.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));
        coller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().ctrlV();
            }
        });

        this.add(menuEdition);

        JMenu menuVue = new JMenu();
        menuVue.setText("Vue");




        //************ZOOM ******************

        JMenuItem zoom = new JMenuItem();
        zoom.setText("Zoom...");

        zoom.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_SHIFT, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        zoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().zoomer(PanneauPrincipal.getPanneauPrincipal().vueActive.getPerspectiveVueActive1(),1);
            }
        });


        menuVue.add(zoom);
        //*****************FIN ZOOM ******************

        //****************Deplacer ***************
        JMenuItem deplacer = new JMenuItem();
        deplacer.setText("Deplacer...");

        deplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField translationHorizontale = new JTextField();
                JTextField translationVerticale = new JTextField();
                Object[] message = {
                        "Translation Horizontale:", translationHorizontale,
                        "Translation Verticale:", translationVerticale
                };

                int translation = JOptionPane.showConfirmDialog(null, message, "Translation", JOptionPane.OK_CANCEL_OPTION);
                if(translation == JOptionPane.OK_OPTION){
                    Controlleur.getControlleur().deplacer(PanneauPrincipal.getPanneauPrincipal().vueActive.getPerspectiveVueActive1(),
                            Integer.parseInt(translationHorizontale.getText()), Integer.parseInt(translationVerticale.getText()));

                }


            }
        });
        menuVue.add(deplacer);
        //**************Fin deplacer ******************

        this.add(menuVue);




    }

}

