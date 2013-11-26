package ets;

import ets.commande.InterfaceCommande;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-21 : Debut de l'implementation
 *              Ajout de la methode factory et des methodes ajouterCommande() et defaireCommande()
 * 2013-11-21 : Ajout de la méthode zoomer(), deplacer(), refaire() et defaire()
 */
public class GestionCommande {

    Factory factory;

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
    private GestionCommande() {
        factory = new Factory();
        pileCommandes = new Stack<InterfaceCommande>();
        pileCommandesDefaites = new Stack<InterfaceCommande>();
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

    public InterfaceCommande refaireCommande() {
        try {
            return pileCommandes.push(pileCommandesDefaites.pop());
        } catch(EmptyStackException e) {
            return null;
        }
    }

    public void zommer(Perspective perspective, int echelle){
//        factory
    }
    public void deplacer(Perspective perspective, int centreX, int centreY) {

    }
    public void refaire() {

    }
    public void defaire() {

    }
}
