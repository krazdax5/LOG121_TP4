package ets;

import ets.commande.InterfaceCommande;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Classe qui s'occupe de garder les commandes effectuees par l'utilisateur en memoire.
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-21 : Debut de l'implementation
 *              Ajout de la methode factory et des methodes ajouterCommande() et defaireCommande()
 */
public class MemoireCommandes {

    /**
     * Pile qui garde en memoire les commandes effectuees par l'utilisateur
     */
    Stack<InterfaceCommande> pileCommandes;

    /**
     * Pile qui garde en memoire les commandes defaites par l'utilsateur
     */
    Stack<InterfaceCommande> pileCommandesDefaites;


    /**
     * Constructeur prive qui permet l'implementation du patron factory
     */
    private MemoireCommandes() {
        pileCommandes = new Stack<InterfaceCommande>();
        pileCommandesDefaites = new Stack<InterfaceCommande>();
    }

    /**
     * Methode factory qui instancie un objet de la classe MemoireCommandes.
     * @return  Un objet de la classe MemoireCommandes
     */
    public static MemoireCommandes createMemoireCommandes() {
        return new MemoireCommandes();
    }

    /**
     * Methode qui permet d'ajouter une commande a la pile de commandes
     * @param commande La commande a ajouter
     */
    public InterfaceCommande ajouterCommande(InterfaceCommande commande) {
        return pileCommandes.push(commande);
    }

    /**
     * Methode qui permet de retirer une commande de la pile de commandes
     * et de l'ajouter a la pile de commandes defaites.
     */
    public InterfaceCommande defaireCommande() {
        try {
            return pileCommandesDefaites.push(pileCommandes.pop());
        } catch(EmptyStackException e) {
            return null;
        }
    }
    
}
