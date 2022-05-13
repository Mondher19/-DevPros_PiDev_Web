/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author wided
 */
public class Equipement {
    private int id,prix;
    private String name,marque,description;

    public Equipement() {
    }

    public Equipement(int id, String name, String marque, String description, int prix) {
        this.id = id;
        this.name = name;
        this.marque = marque;
        this.description = description;
        this.prix = prix;
    }

    public Equipement(int id, int prix) {
        this.id = id;
        this.prix = prix;
    }

    public Equipement(int prix) {
        this.prix = prix;
    }

    public Equipement(int prix, String name, String marque, String description) {
        this.prix = prix;
        this.name = name;
        this.marque = marque;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", prix=" + prix + ", name=" + name + ", marque=" + marque + ", description=" + description + '}';
    }
    
    
}
