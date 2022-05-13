/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Pannier;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Salma
 */
public interface pannierinterface <T>{
     public void ajouterPannier(T p) throws SQLException;

    public void modifierPannier(T p) throws SQLException;

    public void supprimerPannier(int id) throws SQLException;

    public List<T> afficherPannier() throws SQLException;
}
