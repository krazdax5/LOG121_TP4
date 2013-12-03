package ets.commande;

/**
 * Interface qui donne comme contrat d'implementer executer() et defaire()
 * pour toutes ses sous-classes.
 *
 *          Historique des modifications
 ***************************************************
 * @author Pierre-Luc Landry
 * 2013-11-19
 */
public interface InterfaceCommande {

    /**
     * Methode qui permet d'executer une commande
     */
    public void executer();

    /**
     * Methode qui permet de defaire une commande deja effectuee.
     */
    public void defaire();
}
