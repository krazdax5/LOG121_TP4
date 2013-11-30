package ets;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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


        // On gere l'exception directement si le fichier binaire
        // desire n'existe plus ou si l'utilisateur annule.
        // Dans ce cas, nous en creeront un nouveau.
        try {

            Perspective perspective;


            // Creation d'un FileInputStream qui va chercher les
            // information par rapport au fichier renvoye par
            // le JFileChooser fcOpen.
            FileInputStream fileInput = new FileInputStream(
                    perspectiveALire);

            // Transforme le fichier binaire en un objet de la
            // classe Animation (type casting).
            ObjectInputStream objectInput = new
                    ObjectInputStream(fileInput);
            perspective = (Perspective) objectInput.readObject();

            // Ferme la lecture du fichier binaire.
            objectInput.close();

            // Retourne l'objet de la classe Animation qui
            // qui se retrouvait dans le fichier binaire.
            return perspective;


        } catch(Exception e) {

            JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier ou le fichier choisi n'existe pas ...\n" +
                    "Une perspective vierge a ete cree.");

            return new Perspective();

        }
    }

}
