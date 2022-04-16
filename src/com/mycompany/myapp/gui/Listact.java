/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.app.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipement;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Act;
import com.mycompany.myapp.myapp.SideMenuBaseForm;
import com.mycompany.myapp.services.ServiceAct;

/**
 *
 * @author Raef
 */
public class Listact extends SideMenuBaseForm {

    Resources theme;
    Form current;

    public Listact (Form previous) {

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
        
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
  //      fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
        
        
        
        for (Act p : ServiceAct.getInstance().getAllPlats()) {
        
        MultiButton afficher = new MultiButton(p.toString());
      //afficher.addActionListener(e->new affichage(p, previous));
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
            add (afficher);
            add(delete);
            add(update);
            
            }
        
        
      
           


      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   
    
     setupSideMenu(theme);   
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}