package ets;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    PanneauPrincipal panneauPrincipal;

    ImageConcrete imageConcrete;


    public static void main(String args[]) {

        FenetrePrincipale applicationImages = new FenetrePrincipale();

        Thread t = new Thread(applicationImages);

        t.start();
    }

    @Override
    public void run() {

        JFrame application = new JFrame();

        application.setTitle("Application Images TP4");

        Dimension dimensionEcran = new Dimension();
        dimensionEcran.setSize(1000,455);

        // Pour avoir la dimension de l'ecran :
        // Toolkit.getDefaultToolkit().getScreenSize();

        application.setPreferredSize(dimensionEcran);
        application.setMinimumSize(dimensionEcran);
        application.setMaximumSize(dimensionEcran);

        // Position au milieu de l'ecran
        application.setLocationRelativeTo(null);


        application.setJMenuBar(new MenuBar());

        panneauPrincipal = new PanneauPrincipal();
        application.setContentPane(panneauPrincipal);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.setVisible(true);

    }

    private class PanneauPrincipal extends JPanel {

        JPanel vueActive1;
        JPanel vueActive2;
        VueReduite vueReduite;

        private PanneauPrincipal() {

//            this.setLayout(new BorderLayout());

            this.setBackground(Color.LIGHT_GRAY);


            vueActive1 = new JPanel();

            vueActive1.setBackground(Color.BLACK);

            Dimension dimEssai = new Dimension();
            dimEssai.setSize(400,400);
            vueActive1.setPreferredSize(dimEssai);


            this.add(vueActive1);

            vueActive2 = new JPanel();

            vueActive2.setBackground(Color.GRAY);

            vueActive2.setPreferredSize(dimEssai);


            this.add(vueActive2);

//            JPanel vueReduite = new JPanel();

            imageConcrete = ImageConcrete.createImage("/Users/Mathieu/Desktop/Automne 2013.png");

            vueReduite = new VueReduite(imageConcrete.getTheImage(), 125, 125);

            imageConcrete.addObserver(vueReduite);

            vueReduite.setBackground(Color.WHITE);

            Dimension dimReduite = new Dimension();
            dimReduite.setSize(125, 125);

            vueReduite.setPreferredSize(dimReduite);


            this.add(vueReduite);

        }

        private void setImage(String adresse) {

//            Image image = ImageConcrete.createImage(adresse).getTheImage();
//
//            vueReduite.setImageReduite(image);

            imageConcrete.setImage(adresse);

            vueReduite.repaint();

        }

    }

    private class MenuBar extends JMenuBar {

        private MenuBar() {

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
                    final JFileChooser fileChooser = new JFileChooser();

                    int valeurRetour = fileChooser.showOpenDialog(null);

                    if(valeurRetour == JFileChooser.APPROVE_OPTION) {
                        String fichierChoisi = fileChooser.getSelectedFile().getAbsolutePath();
                        panneauPrincipal.setImage(fichierChoisi);
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

}
