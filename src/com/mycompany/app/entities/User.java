/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class User {
    private int id,points;
    private String nom,prenom,mail,password;

  
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    private boolean is_verified;

    public User(String nom,String prenom, String email,String Pwd) {
     
        this.nom = nom;
           this.prenom = prenom;
        
        this.mail = email;
        this.password= Pwd;
    }

    public User(String nom, String email, String password) {
        this.nom = nom;
        this.mail = email;
        this.password = password;
    }
    public User(String username, String password) {
        this.nom = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password=" + password + '}';
    }
    
   

   

    public void setId(int id) {
        this.id = id;
    }


    public void setPoints(int points) {
        this.points = points;
    }


    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public User() {
    }
    public String idtoString ()
    {
        return String.valueOf(id);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }


    public boolean isIs_verified() {
        return is_verified;
    }


  

    
    
    
    
}
