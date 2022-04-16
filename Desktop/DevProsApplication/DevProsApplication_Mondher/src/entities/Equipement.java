/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mondh
 */
public class Equipement {
    
    
    private int id_e;
    private String name_e;
    private String marque_e;
    private String description_e;
    private int typequipement_e;
    private String image_e;
    private float prix_e ;
    
    
    public Equipement() {
    }

    public Equipement(int id_e, String name_e, String marque_e, String description_e, int typequipement_e, String image_e, float prix_e) {
        this.id_e = id_e;
        this.name_e = name_e;
        this.marque_e = marque_e;
        this.description_e = description_e;
        this.typequipement_e = typequipement_e;
        this.image_e = image_e;
        this.prix_e = prix_e;
    }
    
    

  

    public Equipement(int typequipement,String name, String marque, String description, String image, float prix) {
        this.name_e = name;
        this.marque_e = marque;
        this.description_e = description;
        this.typequipement_e = typequipement;
        this.image_e = image;
        this.prix_e = prix;
    }

    public int getId_e() {
        return id_e;
    }

    public void setId_e(int id) {
        this.id_e = id;
    }

    public String getName_e() {
        return name_e;
    }

    public void setName_e(String name) {
        this.name_e = name;
    }

    public String getMarque_e() {
        return marque_e;
    }

    public void setMarque_e(String marque) {
        this.marque_e = marque;
    }

    public String getDescription_e() {
        return description_e;
    }

    public void setDescription_e(String description) {
        this.description_e = description;
    }

    public int getTypequipement_e() {
        return typequipement_e;
    }

    public void setTypequipement_e(int typequipement) {
        this.typequipement_e = typequipement;
    }

    public String getImage_e() {
        return image_e;
    }

    public void setImage_e(String image) {
        this.image_e = image;
    }

    public float getPrix_e() {
        return prix_e;
    }

    public void setPrix_e(float prix) {
        this.prix_e = prix;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id_e + ", name=" + name_e + ", marque=" + marque_e + ", description=" + description_e + ", typequipement=" + typequipement_e + ", image=" + image_e + ", prix=" + prix_e + '}';
    }
    
}
