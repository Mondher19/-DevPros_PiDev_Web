/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Salma
 */
public class Pannier {

    private int id, nbr, id_equipement, somme;

    public Pannier(int nbr, int id_equipement, int somme) {
        this.nbr = nbr;
        this.id_equipement = id_equipement;
        this.somme = somme;
    }

    public Pannier(int nbr,int somme) {
        this.nbr = nbr;
        this.somme = somme;
    }

    public Pannier(int id, int nbr, int id_equipement, int somme) {
        this.id = id;
        this.nbr = nbr;
        this.id_equipement = id_equipement;
        this.somme = somme;
    }

    public Pannier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getId_equipement() {
        return id_equipement;
    }

    public void setId_equipement(int id_equipement) {
        this.id_equipement = id_equipement;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    @Override
    public String toString() {
        return "Pannier{" + "id=" + id + ", nbr=" + nbr + ", id_equipement=" + id_equipement + ", somme=" + somme + '}';
    }

}
