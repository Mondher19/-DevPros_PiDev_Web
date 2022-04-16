/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.entities;

/**
 *
 * @author Mondher en personne
 */
public class TypeEquipement {
    
    private int id_et;
    private String name_et;
    private String image_et ;

    public TypeEquipement(int id, String name, String image) {
        this.id_et = id;
        this.name_et = name;
        this.image_et = image;
    }

    public TypeEquipement(String name, String image) {
        this.name_et = name;
        this.image_et = image;
    }

    public TypeEquipement() {
       
    }

    public int getId_et() {
        return id_et;
    }

    public void setId_et(int id) {
        this.id_et = id;
    }

    public String getName_et() {
        return name_et;
    }

    public void setName_et(String name) {
        this.name_et = name;
    }

    public String getImage_et() {
        return image_et;
    }

    public void setImage_et(String image) {
        this.image_et = image;
    }

    @Override
    public String toString() {
        return "TypeEquipement{" + "id=" + id_et + ", name=" + name_et + ", image=" + image_et + '}';
    }
    
    
    
    
    
    
    
}
