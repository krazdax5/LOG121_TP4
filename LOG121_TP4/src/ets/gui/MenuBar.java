package ets.gui;

import ets.Controlleur;
import ets.Perspective;
import ets.UtilitaireFichier;
import ets.VueActive;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
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

                boolean isAnImage = false;

                int valeurRetour;

                do {

                    isAnImage = false;

                    final JFileChooser fileChooser = new JFileChooser();

                    valeurRetour = fileChooser.showOpenDialog(null);

                    if (valeurRetour == JFileChooser.APPROVE_OPTION) {
                        String imageChoisie = fileChooser.getSelectedFile().getAbsolutePath();
                        File nouvelleImage = new File(imageChoisie);

                        String mimetype= new MimetypesFileTypeMap().getContentType(nouvelleImage);
                        String type = mimetype.split("/")[0];
                        if(type.equals("image")){
                            Controlleur.getControlleur().changerImage(nouvelleImage);
                            isAnImage = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Ceci n'est pas une image veuillez recommencer.");
                            isAnImage=false;
                        }
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
                    Controlleur.getControlleur().changerPerspective(perspectiveChoisie);
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
                    String os = System.getProperty("os.name").toLowerCase();

                    if(os.equals("mac os x")){
                        adresse = fcSave.getCurrentDirectory() + "/" + fcSave.getSelectedFile().getName();
                        UtilitaireFichier.sauvegarderPerspective(PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive1(),adresse);

                    }else{
                        String fichier = fcSave.getSelectedFile().getAbsolutePath();
                        adresse = fichier.replace("\\", "\\\\");
                        UtilitaireFichier.sauvegarderPerspective(PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive1(),adresse);
                    }

                } else {
                    JOptionPane.showMessageDialog(null,"L'emplacement entre est erronne ou il existe deja une fichier ...");
                }
            }
        });

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
                Controlleur.getControlleur().ctrlC(PanneauPrincipal.getPanneauPrincipal().getVueChoisie().getPerspectiveVueActive1());
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

        menuEdition.add(coller);

        this.add(menuEdition);

        JMenu menuVue = new JMenu();
        menuVue.setText("Vue");




        //************ZOOM ******************

        JMenuItem zoom = new JMenuItem();
        zoom.setText("Zoom...");

        zoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controlleur.getControlleur().zoomer(PanneauPrincipal.getPanneauPrincipal().vueActive1.getPerspectiveVueActive1(),1);
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
                        int ancienOffsetX = vueActive.getPerspectiveVueActive1().getCornerImageX();
                        int ancienOffsetY = vueActive.getPerspectiveVueActive1().getCornerImageY();
                        Controlleur.getControlleur().deplacer(vueActive.getPerspectiveVueActive1(),
                                ancienOffsetX + nouveauOffsetX, ancienOffsetY + nouveauOffsetY);
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
                vueActive.getPerspectiveVueActive1().setEchelle(1);
                vueActive.getPerspectiveVueActive1().setCornerPerspective(0,0);
                vueActive.repaint();
            }
        });

        menuVue.add(etatInitial);

        this.add(menuVue);




    }

}

