/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.app.entities.Equipement;

/**
 *
 * @author Raef
 */
public class ShowSingleForm extends Form{
    Resources theme;

    ShowSingleForm(Equipement p,Form previous) {
        theme = UIManager.initFirstTheme("/theme");
      setTitle("Détails du produit");
        setLayout(new FlowLayout(CENTER,BRB_CONSTANT_ASCENT));
        Container cont=new Container(BoxLayout.y());
        String urll = "http://localhost/projectpidev/public/uploads/" + p.getImage_e();
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("duke.png").scaled(250, 250), false);
            URLImage urlimg = URLImage.createToStorage(enc, p.getName_e(), urll);
            ImageViewer image = new ImageViewer(urlimg);
            Image imgg = image.getImage();
            
            Label lbprix=new Label("Prix : "+p.getPrix_e()+" DT");
            Label lbNom=new Label("Nom : "+p.getName_e());
      
            Label lbmarque=new Label("Marque : "+p.getMarque_e());
            Label lbcoul=new Label("Descption : "+p.getDescription_e());
          
        cont.add(imgg);
        cont.add(lbprix);
        
        cont.add(lbNom);
     
        cont.add(lbmarque);
        cont.add(lbcoul);
        
        add(cont);
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
