/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Act;
import com.mycompany.myapp.services.ServiceAct;
import com.mycompany.myapp.myapp.ProfileForm;
import com.mycompany.myapp.myapp.SideMenuBaseForm;

/**
 *
 * @author yassine
 */
public class affichage extends SideMenuBaseForm {
      Resources theme;
    Form current;
    
    
        public affichage(Act p,Form previous) {
            
                   current = this;
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
    
       
   Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
     
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Nos produits", "Title")
                                )
                            )
                );
        
        
        
        
 tb.setTitleComponent(titleCmp);
       
       
        
        MultiButton mb = new MultiButton("Nom =" +p.getNom());
        MultiButton ms = new MultiButton("Descrption =" +p.getDesc());
        MultiButton mn = new MultiButton("Image =" +p.getImg());
      
//            System.out.println(user.getId());
        Button update = new Button("modifier");
        update.setUIID("update");
            update.addActionListener(e -> new Modifieract(p,previous).show());
        Button delete = new Button("supprimer");
        delete.setUIID("delete");
           delete.addActionListener(e -> new Deleteact(p,previous).show());
//            for(int i = 0; i < p; i++)
//            {
//                System.out.println();
//                }
            add(mb);
            add(ms);
            add(mn);
           
            add(delete);
            add(update);
            
 // Form current = null;
       Resources res = null ;
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ProfileForm(res).show());
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
         
    