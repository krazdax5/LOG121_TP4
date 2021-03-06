package ets.gui;

import ets.Controleur;
import ets.UtilitaireFichier;

import javax.activation.MimetypesFileTypeMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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

    /**
     * Constructeur du MenuBar de l'application
     * Cree tous les sous-menu et leur menuItem.
     * Fichier : Ouvrir image.
     *           Ouvrir perspective.
     *           Enregistrer perspective.
     * Defaire : Defaire
     *           Refaire
     *           Copier
     *           Coller
     * Vue     : Zoom
     *           Deplacer
     *           Etat initial
     */
    protected MenuBar() {

        // DEBUT MENU FICHIER //
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

                boolean isAnImage = false;

                int valeurRetour;

                do {

                    isAnImage = false;

                    final JFileChooser fileChooser = new JFileChooser();

                    valeurRetour = fileChooser.showOpenDialog(null);

                    if (valeurRetour == JFileChooser.APPROVE_OPTION) {
                        String imageChoisie = fileChooser.getSelectedFile().getAbsolutePath();
                        File nouvelleImage = new File(imageChoisie);

                        // Lignes de code inspirées de StackOverflow.
                        // Detecte le type de fichier lu et verifie si ce dernier est une image.
                        // Source : http://stackoverflow.com/questions/9643228/test-if-file-is-an-image

                        String mimetype= new MimetypesFileTypeMap().getContentType(nouvelleImage);
                        String type = mimetype.split("/")[0];
                        if(type.equals("image")){
                            Controleur.getControleur().changerImage(nouvelleImage);
                            isAnImage = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Ceci n'est pas une image veuillez recommencer.");
                            isAnImage=false;
                        }

                        // Fin des lignes inspirées
                    }
                }while(valeurRetour != JFileChooser.CANCEL_OPTION && !isAnImage);
            }
        });

        menuFichier.add(ouvrirImage);

        JMenuItem ouvrirPerspective = new JMenuItem();
        ouvrirPerspective.setText("Ouvrir perspective...");
        ouvrirPerspective.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fcOpen = new JFileChooser();
                fcOpen.setDialogTitle("Ouvrir une perspective (.pers)");

                int valeurRetour = fcOpen.showOpenDialog(null);

                if (valeurRetour == JFileChooser.APPROVE_OPTION) {
                    String perspectiveChoisie = fcOpen.getSelectedFile().getAbsolutePath();
                    Controleur.getControleur().changerPerspective(perspectiveChoisie);
                }
            }
        });

        menuFichier.add(ouvrirPerspective);

        JMenuItem enregistrer = new JMenuItem();
        enregistrer.setText("Enregistrer perspective...");
        enregistrer.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, Toolkit.getDefaultToolkit().
                getMenuShortcutKeyMask()));
        enregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                final JFileChooser fcSave = new JFileChooser();
                fcSave.setDialogTitle("Sauvegarder votre animation (.ani)");

                int valeurRetour = fcSave.showSaveDialog(null);
                if(valeurRetour == JFileChooser.APPROVE_OPTION) {

                    String adresse;
                    String systemeExploitation = System.getProperty("os.name").toLowerCase();

                    // Les lignes de code suivante ont été inspirée de Mkyong.com
                    // Detecte le systeme d'exploitation dans lequel nous sommes.
                    // Source : http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/

                    if(systemeExploitation.equals("mac os x")){
                        adresse = fcSave.getCurrentDirectory() + "/" + fcSave.getSelectedFile().getName();
                        UtilitaireFichier.sauvegarderPerspective(PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive(),adresse);

                    }else{
                        String fichier = fcSave.getSelectedFile().getAbsolutePath();
                        adresse = fichier.replace("\\", "\\\\");
                        UtilitaireFichier.sauvegarderPerspective(PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive(),adresse);
                    }

                    // Fin des lignes inspirée

                } else {
                    JOptionPane.showMessageDialog(null,"L'emplacement entre est erronne ...");
                }
            }
        });

        menuFichier.add(enregistrer);

        this.add(menuFichier);

        // FIN MENU FICHIER //

        // DEBUT MENU EDITION //

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
                Controleur.getControleur().defaire();
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
                Controleur.getControleur().refaire();
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
                Controleur.getControleur().ctrlC();
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
                Controleur.getControleur().ctrlV();
            }
        });

        menuEdition.add(coller);

        this.add(menuEdition);

        // FIN MENU EDITION //

        // DEBUT MENU VUE //

        JMenu menuVue = new JMenu();
        menuVue.setText("Vue");




        //************ZOOM ******************

        JMenuItem zoom = new JMenuItem();
        zoom.setText("Zoom...");

        zoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controleur.getControleur().zoomer(1);
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
                    try {
                        int nouveauOffsetX = Integer.parseInt(translationHorizontale.getText());
                        int nouveauOffsetY = Integer.parseInt(translationVerticale.getText());
                        VueActive vueActive = PanneauPrincipal.getPanneauPrincipal().getVueChoisie();
                        int ancienOffsetX = vueActive.getPerspectiveVueActive().getCornerImageX();
                        int ancienOffsetY = vueActive.getPerspectiveVueActive().getCornerImageY();
                        Controleur.getControleur().deplacer(ancienOffsetX + nouveauOffsetX, ancienOffsetY + nouveauOffsetY);
                    } catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"Veuillez entrer un nombre S.V.P.");
                        actionPerformed(e);
                    }

                }


            }
        });
        menuVue.add(deplacer);
        //**************Fin deplacer ******************

        JMenuItem etatInitial = new JMenuItem();
        etatInitial.setText("Etat initial");
        etatInitial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VueActive vueActive = PanneauPrincipal.getPanneauPrincipal().getVueChoisie();
                vueActive.getPerspectiveVueActive().setEchelle(1);
                vueActive.getPerspectiveVueActive().setCornerPerspective(0,0);
                vueActive.repaint();
            }
        });

        menuVue.add(etatInitial);

        this.add(menuVue);

        // FIN MENU VUE //


    }

}

