package ets;

import ets.commande.*;
import ets.gui.PanneauPrincipal;

import java.util.Stack;

/**
 *
 *
 *          Historique des modifications
 ***************************************************
 * @author Mathieu Lachance LACM14059305
 * 2013-11-21 : Debut de l'implementation
 *              Ajout de la methode factory et des methodes ajouterCommande() et defaireCommande()
 * 2013-11-21 : Ajout de la méthode zoomer(), deplacer(), refaireCommande() et defaireCommande()
 */
public class GestionCommande {


    /**
     * Objet qui permet la creation de commandes.
     */
    private Factory factory;

    /**
     * Pile qui garde en memoire les commandes effectuees par l'utilisateur sur la vue active 1
     */
    private Stack<InterfaceCommande> pileCommandes1;

    /**
     * Pile qui garde en memoire les commandes effectuees par l'utilisateur sur la vue active 2
     */
    private Stack<InterfaceCommande> pileCommandes2;

    /**
     * Pile qui garde en memoire les commandes defaites par l'utilsateur sur la vue active 1
     */
    private Stack<InterfaceCommande> pileCommandesDefaites1;

    /**
     * Pile qui garde en memoire les commandes defaites par l'utilsateur sur la vue active 2
     */
    private Stack<InterfaceCommande> pileCommandesDefaites2;

    /**
     * Pile qui garde en memoire la commande copiee.
     */
    private Stack<Copie> pileCopie;


    /**
     * Constructeur prive qui permet l'implementation du patron factory
     */
    public GestionCommande() {
        factory = new Factory();
        pileCommandes1 = new Stack<InterfaceCommande>();
        pileCommandes2 = new Stack<InterfaceCommande>();
        pileCommandesDefaites1 = new Stack<InterfaceCommande>();
        pileCommandesDefaites2 = new Stack<InterfaceCommande>();
        pileCopie = new Stack<Copie>();
    }

    /**
     * Methode qui permet d'ajouter une commande a la pile de commandes
     * @param commande La commande a ajouter
     */
    private void ajouterCommande(InterfaceCommande commande) {
        if(PanneauPrincipal.getPanneauPrincipal().getVueChoisie() == PanneauPrincipal.getPanneauPrincipal().getVueActive1())
            pileCommandes1.push(commande);
        else
            pileCommandes2.push(commande);
    }
    private void ajouterCommandeDefaite(InterfaceCommande commande) {
        if(PanneauPrincipal.getPanneauPrincipal().getVueChoisie() == PanneauPrincipal.getPanneauPrincipal().getVueActive1())
            pileCommandesDefaites1.push(commande);
        else
            pileCommandesDefaites2.push(commande);
    }

    /**
     * Methode qui permet de retirer une commande de la pile de commandes
     * et de l'ajouter a la pile de commandes defaites.
     */
    public void defaireCommande() {
        if(PanneauPrincipal.getPanneauPrincipal().getVueChoisie() == PanneauPrincipal.getPanneauPrincipal().getVueActive1() && !pileCommandes1.isEmpty()) {
            InterfaceCommande commandeADefaire = pileCommandes1.pop();
            commandeADefaire.executer();
            this.ajouterCommandeDefaite(commandeADefaire);
        } else {
            if(!pileCommandes2.isEmpty()){
                InterfaceCommande commandeADefaire = pileCommandes2.pop();
                commandeADefaire.executer();
                this.ajouterCommandeDefaite(commandeADefaire);
            }
        }
    }

    public void refaireCommande() {
        if(PanneauPrincipal.getPanneauPrincipal().getVueChoisie() == PanneauPrincipal.getPanneauPrincipal().getVueActive1() && !pileCommandesDefaites1.isEmpty()) {
            InterfaceCommande commandeARefaire = pileCommandesDefaites1.pop();
            commandeARefaire.executer();
            this.ajouterCommande(commandeARefaire);
        } else {
            if(!pileCommandesDefaites2.isEmpty()) {
                InterfaceCommande commandeARefaire = pileCommandesDefaites2.pop();
                commandeARefaire.executer();
                this.ajouterCommande(commandeARefaire);
            }
        }
    }

    private void viderCommandeDefaites() {
        if(PanneauPrincipal.getPanneauPrincipal().getVueChoisie() == PanneauPrincipal.getPanneauPrincipal().getVueActive1()) {
            while(!pileCommandesDefaites1.empty())
                pileCommandesDefaites1.pop();
        } else {
            while(!pileCommandesDefaites2.empty())
                pileCommandesDefaites2.pop();
        }
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
            pileCommandes1.push(pileCopie.peek());
        }
    }

    public void zommer(Perspective perspective, int echelle){
        Zoom zoom = factory.createZoom(perspective, echelle);
        zoom.executer();
        this.ajouterCommande(zoom);
        this.viderCommandeDefaites();
    }
    public void deplacer(Perspective perspective, int offsetX, int offsetY) {
        Deplacer deplace = factory.createDeplacer(perspective, offsetX, offsetY);
        deplace.executer();
        this.ajouterCommande(deplace);
        this.viderCommandeDefaites();
    }
}
