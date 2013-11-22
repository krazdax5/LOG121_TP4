package ets;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance
 * 2013-11-19 : Début de l'implémentation
 *              Ajout des classes main() et run()
 *              Ajout du JMenuMenuBar (classe MenuBar)
 */
public class FenetrePrincipale implements Runnable {

    JFrame application;

    String titre;


    public static void main(String args[]) {

        FenetrePrincipale applicationImages = new FenetrePrincipale();

        Thread t = new Thread(applicationImages);

        t.start();
    }

    @Override
    public void run() {

        application = new JFrame();

        titre = "Application Images TP4";
        application.setTitle(titre);

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
            ouvrir.setText("Ouvrir ImageConcrete...");
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

            add(menuFichier);
        }

    }

}
