package tests;

import ets.MemoireCommandes;
import ets.commande.Coller;
import ets.commande.Deplacer;
import ets.commande.InterfaceCommande;
import ets.commande.Zoom;
import org.junit.Test;


/**
 * Classe de tests pour la classe MemoireCommandes
 *
 * @author MathieuLachance LACM14059305
 * 2013-11-21 : Creation des tests
 */
public class MemoireCommandesTest {
    @Test
    public void testCreateMemoireCommandes() throws Exception {

        MemoireCommandes memoire = MemoireCommandes.createMemoireCommandes();

        // Test de la premiere instanciation.
        assert memoire != null;

    }



    @Test
    public void testAjouterCommande() throws Exception {

        MemoireCommandes memoire = MemoireCommandes.createMemoireCommandes();

        InterfaceCommande commande1 = new Zoom();
        InterfaceCommande commande2 = new Deplacer();
        InterfaceCommande commande3 = new Coller();

        assert memoire.ajouterCommande(commande1) == commande1;
        assert memoire.ajouterCommande(commande2) == commande2;
        assert memoire.ajouterCommande(commande3) == commande3;

    }

    @Test
    public void testDefaireCommande() throws Exception {

        MemoireCommandes memoire = MemoireCommandes.createMemoireCommandes();

        InterfaceCommande commande1 = new Zoom();
        InterfaceCommande commande2 = new Deplacer();
        InterfaceCommande commande3 = new Coller();

        memoire.ajouterCommande(commande1);
        memoire.ajouterCommande(commande2);
        memoire.ajouterCommande(commande3);

        assert memoire.defaireCommande() == commande3;
        assert memoire.defaireCommande() == commande2;
        assert memoire.defaireCommande() == commande1;
        assert memoire.defaireCommande() == null;

    }


}
