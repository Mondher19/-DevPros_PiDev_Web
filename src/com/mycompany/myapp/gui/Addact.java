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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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

public class Addact extends SideMenuBaseForm {

    Resources theme;
    Form current;

    public Addact (Form previous) {

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
        
         setTitle("Ajouter une actualité");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","nom du actualité");
        TextField tfDesc = new TextField("","description");
        TextField tfImg = new TextField("","image");
       
        
        
        Button btnValider = new Button("Ajouter");

                btnValider.addActionListener(new ActionListener() {
                   

        @Override
         public void actionPerformed(ActionEvent evt) {
                        if ((tfNom.getText().length()==0) && (tfDesc.getText().length()==0) )
                            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                        else
                        {
                            try {
                                Act  p = new Act (tfNom.getText().toString(),tfDesc.getText().toString(),tfImg.getText().toString() );
                                if( ServiceAct.getInstance().addProduct(p))
                                {
                                   Dialog.show("Success","Connection accepted",new Command("OK"));
                                }else
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                            } catch (NumberFormatException e) {
                                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            }

                        }


            }
        });
        
        addAll(tfNom,tfDesc,tfImg,btnValider);
        
    
           


      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   
    
     setupSideMenu(theme);   
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  

}
