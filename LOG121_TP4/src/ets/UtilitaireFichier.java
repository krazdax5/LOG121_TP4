package ets;

import javax.swing.*;
import java.io.*;

/**
 * Classe qui permet la manipulation de fichier.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-12-01 : Creation et implementation de la classe
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
