package ets.gui;

import ets.Controlleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: lucie
 * Date: 2013-11-26
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */

public class MenuBar extends JMenuBar {

    protected MenuBar() {

        JMenu menuFichier = new JMenu();
        menuFichier.setText("Fichier");

        JMenuItem ouvrir = new JMenuItem();
        ouvrir.setText("Ouvrir Image...");
        ouvrir.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        ouvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Controlleur controlleur = Controlleur.getControlleur();
                final JFileChooser fileChooser = new JFileChooser();

                int valeurRetour = fileChooser.showOpenDialog(null);

                if(valeurRetour == JFileChooser.APPROVE_OPTION) {
                    String fichierChoisi = fileChooser.getSelectedFile().getAbsolutePath();
                    controlleur.changerImage(fichierChoisi);
                }
            }
        });

        menuFichier.add(ouvrir);

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

        menuEdition.add(defaire);

        JMenuItem refaire = new JMenuItem();
        refaire.setText("Refaire...");
        refaire.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        menuEdition.add(refaire);

        menuEdition.addSeparator();

        JMenuItem copier = new JMenuItem();
        copier.setText("Copier");
        copier.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        menuEdition.add(copier);

        JMenuItem coller = new JMenuItem();
        coller.setText("Coller");
        coller.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));

        menuEdition.add(coller);

        this.add(menuEdition);

        JMenu menuVue = new JMenu();
        menuVue.setText("Vue");

        JMenuItem zoom = new JMenuItem();
        zoom.setText("Zoom...");

        menuVue.add(zoom);

        JMenuItem deplacer = new JMenuItem();
        deplacer.setText("Deplacer...");

        menuVue.add(deplacer);

        this.add(menuVue);

    }

}

