package ets;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
public class FenetrePrincipale implements Runnable {


    public static void main(String args[]) {

        FenetrePrincipale applicationImages = new FenetrePrincipale();

        Thread t = new Thread(applicationImages);

        t.start();
    }

    @Override
    public void run() {

        JFrame application = new JFrame();

        application.setTitle("Application Images TP4");

        Dimension dimensionEcran = Toolkit.getDefaultToolkit().getScreenSize();

        application.setPreferredSize(dimensionEcran);
        application.setMinimumSize(dimensionEcran);
        application.setMaximumSize(dimensionEcran);


        application.setJMenuBar(new MenuBar());

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.setVisible(true);

    }

    public class MenuBar extends JMenuBar {

        public MenuBar() {

            JMenu menuFichier = new JMenu();
            menuFichier.setText("Fichier");

            JMenuItem ouvrir = new JMenuItem();
            ouvrir.setText("Ouvrir Image...");
            ouvrir.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_O, Toolkit.getDefaultToolkit().
                    getMenuShortcutKeyMask()));

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

}
