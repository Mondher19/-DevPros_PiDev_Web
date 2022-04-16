/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mondh
 */
public class HomeForm extends Form{
      Form current;
    public HomeForm() {
        current=this;
      
        
        setTitle("Acceuil");
        setLayout(BoxLayout.y());
        add(new Label("choisir une option "));
        Button btnAddEquipement = new Button("Ajouter un  Equipement");
        Button btnListEquipement = new Button("la Liste des Equipements");
        
      //  btnAddEquipement.addActionListener(e-> new AddEquipementForm(current).show());
        btnListEquipement.addActionListener(e-> new ListEquipementForm(current).show() );
        addAll(btnAddEquipement,btnListEquipement);
        
        
        
    }
    
    
    
}
