package ets;

import ets.commande.*;

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

    private Factory factory;

    /**
     * Pile qui garde en memoire les commandes effectuees par l'utilisateur
     */
    private Stack<InterfaceCommande> pileCommandes;

    /**
     * Pile qui garde en memoire les commandes defaites par l'utilsateur
     */
    private Stack<InterfaceCommande> pileCommandesDefaites;


    private Stack<Copie> pileCopie;


    /**
     * Constructeur prive qui permet l'implementation du patron factory
     */
    public GestionCommande() {
        factory = new Factory();
        pileCommandes = new Stack<InterfaceCommande>();
        pileCommandesDefaites = new Stack<InterfaceCommande>();
        pileCopie = new Stack<Copie>();
    }

    /**
     * Methode qui permet d'ajouter une commande a la pile de commandes
     * @param commande La commande a ajouter
     */
    private InterfaceCommande ajouterCommande(InterfaceCommande commande) {
        return pileCommandes.push(commande);
    }

    /**
     * Methode qui permet de retirer une commande de la pile de commandes
     * et de l'ajouter a la pile de commandes defaites.
     */
    private InterfaceCommande defaireCommande() {
        return pileCommandes.pop();
    }

    private InterfaceCommande refaireCommande() {
        return pileCommandesDefaites.pop();
    }

    /**
     * Methode qui permet de de concerver en mémoire dans une pile la
     * perspective que l'on a quand on copie l'image
     */
    public void ctrlC(Perspective perspective){

        Copie ctrlC = factory.createCopie(perspective);
        if(!pileCopie.isEmpty())
            pileCopie.pop();
        pileCopie.push(ctrlC);

    }

    /**
     * Methode qui permet de prendre la derniere valeur de la pile de
     * copie et la remettre dans la pile de commande
     */
    public void ctrlV(){
        if(!pileCopie.isEmpty()) {
            Copie ctrlV = pileCopie.peek();
            ctrlV.executer();
            pileCommandes.push(pileCopie.peek());
        }
    }

    public void zommer(Perspective perspective, int echelle){
        Zoom zoom = factory.createZoom(perspective, echelle);
        zoom.executer();
        this.ajouterCommande(zoom);

    }
    public void deplacer(Perspective perspective, int offsetX, int offsetY) {
        Deplacer deplace = factory.createDeplacer(perspective, offsetX, offsetY);
        deplace.executer();
        this.ajouterCommande(deplace);
    }
    public void refaire() {
        if(!pileCommandesDefaites.isEmpty()){
            InterfaceCommande commandeRefaite = this.refaireCommande();
            commandeRefaite.executer();
            pileCommandes.push(commandeRefaite);
        }
    }
    public void defaire() {
        if(!pileCommandes.isEmpty()){
            InterfaceCommande commandeDefaite = this.defaireCommande();
            commandeDefaite.defaire();
            pileCommandesDefaites.push(commandeDefaite);
        }
    }
}
