/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Commande;
import services.CommandeService;

import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author wided
 */
public class devpros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
      
     
        /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date_f = format.parse("2022-02-18");
        */
      
      ///////////////////// t //////////////////////////////////
       Commande f1 = new Commande(1,"Ham","Hamza","Esprit",12345,"hamza@esprit.tn");
     Commande f2 = new Commande(14,"Ham2","Hamza","Esprit2",12345,"hamza@esprit.tn");
       
       CommandeService sb = new CommandeService();

        try {
            sb.modifierCommande(f2);
            System.out.println("Commande Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            sb.ajouterCommande(f1);
            System.out.println("Commande Ajouté");
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            sb.supprimerCommande(14);
System.out.println("Commande supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
try {
           System.out.println(sb.afficherCommande());
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
}

