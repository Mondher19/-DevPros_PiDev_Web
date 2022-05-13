/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author Salma
 
 */
public interface commandeinterface <T>{
      public void ajouterCommande(T p) throws SQLException;

    public void modifierCommande(T p) throws SQLException;

    public void supprimerCommande(int id) throws SQLException;

    public List<T> afficherCommande() throws SQLException;
}
