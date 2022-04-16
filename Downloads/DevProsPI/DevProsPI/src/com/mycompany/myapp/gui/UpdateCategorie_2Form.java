/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.app.entities.Equipement;
import com.mycompany.app.entities.TypeEquipement;
import com.mycompany.myapp.services.ServiceEquipement;
import com.mycompany.myapp.services.ServiceType;

/**
 *
 * @author Raef
 */
public class UpdateCategorie_2Form extends Form {
  Resources theme;
    UpdateCategorie_2Form(TypeEquipement p, Form previous) {
          theme = UIManager.initFirstTheme("/theme");
        setTitle("Modifier  produit");
        setLayout(BoxLayout.y());
        
        Label tfNamel = new Label("Nom ");
        TextField tfName = new TextField(p.getName_et());
        
        Label tfImagel = new Label("Marque");
        TextField tfimage = new TextField(p.getImage_et());
        
        
       
     //   addStringValue("Category", listens);
      
        
       
       
        
//        Label tftel1 = new Label("Téléphone");
     // TextField tftel = new TextField(p.getTel());

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfimage.getText().length() == 0) ) {
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                } else {
                    try {
                        p.setId_et(p.getId_et());
                        p.setName_et(tfName.getText());
                        p.setImage_et(tfimage.getText());                      
                      
//                        p.setTel(Integer.parseInt(tftel.getText()));
                        ServiceType.getInstance().updateCategorie(p);

                        Dialog.show("", "Le produit a été modifié avec succès", new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Price must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfNamel,tfName,tfImagel,tfimage);
     //add(tftel);
        add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListEquipement_AdminForm(previous).show());

    }

}