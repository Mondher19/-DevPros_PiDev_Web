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
public class UpdateEquipementForm extends Form {
  Resources theme;
    UpdateEquipementForm(Equipement p, Form previous) {
          theme = UIManager.initFirstTheme("/theme");
        setTitle("Modifier  produit");
        setLayout(BoxLayout.y());
        
        Label tfNamel = new Label("Nom ");
        TextField tfName = new TextField(p.getName_e());
        
        Label tfMarquel = new Label("Marque");
        TextField tfMarque = new TextField(p.getMarque_e());
        
        Label tfDescl = new Label("Description");
        TextField tfDesc = new TextField(p.getDescription_e());
        
        Label tfimagel = new Label("image");
        TextField tfimage = new TextField(p.getImage_e());
        
        
        Label tfPrixl = new Label("Prix");
        TextField tfPrix = new TextField((int) p.getPrix_e());

       
      
        
         Label listens_catl = new Label("Catégorie");
         
       ComboBox tftypes=new ComboBox();
         try {
           for(TypeEquipement tp : ServiceType.getInstance().getAllTypes()){
               
               tftypes.addItem(tp.getId_et());
           }
           } catch (Exception e) {
       }
       
     //   addStringValue("Category", listens);
      
        
       
       
        
//        Label tftel1 = new Label("Téléphone");
     // TextField tftel = new TextField(p.getTel());

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfMarque.getText().length() == 0) ) {
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                } else {
                    try {
                        p.setId_e(p.getId_e());
                        p.setName_e(tfName.getText());
                        p.setMarque_e(tfMarque.getText());
                        p.setDescription_e(tfDesc.getText());
                        p.setImage_e(tfimage.getText());                      
                        p.setPrix_e(Float.parseFloat(tfPrix.getText()));
                      
//                        p.setTel(Integer.parseInt(tftel.getText()));
                        ServiceEquipement.getInstance().UpdateEquipement(p, tftypes.getSelectedItem().toString());

                        Dialog.show("", "Le produit a été modifié avec succès", new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Price must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfNamel,tfName,tfMarquel,tfMarque,tfimagel,tfimage,tfDescl,tfDesc,tfPrixl,tfPrix,listens_catl,tftypes);
     //add(tftel);
        add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListEquipement_AdminForm(previous).show());

    }

}