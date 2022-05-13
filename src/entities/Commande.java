/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Salma
 */
public class Commande {

    private int id, numtelephone, idpanier
,verif;
    private String nom, prenom, adresse, email;

    public Commande() {
    }

    

    public Commande(int id, String nom, String prenom, String adresse,int numtelephone, String email,  int idpanier) {
        this.id = id;
       
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
         this.numtelephone = numtelephone;
        this.email = email;
        
        this.idpanier = idpanier;
       
    }
 public Commande(int id, String nom, String prenom, String adresse,int numtelephone, String email) {
        this.id = id;
       
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
         this.numtelephone = numtelephone;
        this.email = email;
        
        
      
    }

    public int getVerif() {
        return verif;
    }

    public void setVerif(int verif) {
        this.verif = verif;
    }

    public Commande( int numtelephone, int idpanier, String nom, String prenom, String adresse, String email) {
        this.numtelephone = numtelephone;
        this.idpanier = idpanier;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
    }

    public Commande( int numtelephone, String nom, String email, String adresse, String prenom) {
        this.numtelephone = numtelephone;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.prenom = prenom;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public int getNumtelephone() {
        return numtelephone;
    }

    public void setNumtelephone(int numtelephone) {
        this.numtelephone = numtelephone;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(int idpanier) {
        this.idpanier = idpanier;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;


        if (this.numtelephone != other.numtelephone) {
            return false;
        }
      
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
      
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", numtelephone=" + numtelephone + ", idpanier=" + idpanier + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + '}';
    }

  

}
