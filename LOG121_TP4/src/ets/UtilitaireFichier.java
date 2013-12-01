package ets;

import javax.swing.*;
import java.io.*;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class UtilitaireFichier {

    public static Perspective recuperePerspective(String perspectiveALire) {

        try {

            Perspective perspective;

            FileInputStream fileInput = new FileInputStream(
                    perspectiveALire);

            ObjectInputStream objectInput = new
                    ObjectInputStream(fileInput);
            perspective = (Perspective) objectInput.readObject();

            objectInput.close();

            return perspective;


        } catch(Exception e) {

            JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier ou le fichier choisi n'existe pas ...\n" +
                    "Une perspective vierge a ete cree.");

            return new Perspective();

        }
    }


    public static void sauvegarderPerspective(Perspective perspective,String adresse) {

            File fileBin = new File(adresse);

            try {
                FileOutputStream fileOutput = new
                        FileOutputStream(fileBin);
                ObjectOutputStream objectOutput = new
                        ObjectOutputStream(fileOutput);
                objectOutput.writeObject(perspective);
                objectOutput.close();
            } catch(Exception e) {
                fileBin = new File(adresse);
            }

    }

}
