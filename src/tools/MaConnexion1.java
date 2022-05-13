/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author macbook
 */
public class MaConnexion1 {
    
    final String url ="jdbc:mysql://localhost:3306/devpros";
    final String login ="root";
    final String pwd="";
    private static  MaConnexion1 instance;
    Connection connexion;
    
    
    private  MaConnexion1 (){
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static  MaConnexion1  getInstance(){
    if (instance == null)
        instance = new  MaConnexion1 ();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
    
    
}
