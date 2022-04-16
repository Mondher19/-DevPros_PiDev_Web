/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.myapp;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.AddCategorie_2Form;
import com.mycompany.myapp.gui.AddCategory;
import com.mycompany.myapp.gui.AddEquipementForm;
import com.mycompany.myapp.gui.AddUserForm;
import com.mycompany.myapp.gui.Addact;
import com.mycompany.myapp.gui.AjouterTypeT;

import com.mycompany.myapp.gui.ListCategorie_2AdminForm;
import com.mycompany.myapp.gui.ListCategorie_2Form;
import com.mycompany.myapp.gui.ListEquipementForm;
import com.mycompany.myapp.gui.ListEquipement_AdminForm;
import com.mycompany.myapp.gui.ListTasksForm;
import com.mycompany.myapp.gui.ListTypeT;
import com.mycompany.myapp.gui.ListUsersForm;
import com.mycompany.myapp.gui.Listact;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("images.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Jennifer Wilson", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_MANAGE_ACCOUNTS, e -> {
            Toolbar.setGlobalToolbar(false);
            new ProfileForm(res).show();
            Toolbar.setGlobalToolbar(true);
        });
        
        //yassine 
         Form current = null;
        
        getToolbar().addMaterialCommandToSideMenu("Ajouter un actualité", FontImage.MATERIAL_SHOPPING_CART, e-> new Addact(current).show());
         getToolbar().addMaterialCommandToSideMenu("Liste des actualités", FontImage.MATERIAL_SHOPPING_CART,  e-> new Listact(current).show());
          getToolbar().addMaterialCommandToSideMenu("  PAjouter une Categorie", FontImage.MATERIAL_EXIT_TO_APP,  e-> new AddCategory(current).show());
        getToolbar().addMaterialCommandToSideMenu(" Liste des Categories", FontImage.MATERIAL_TOPIC,  e-> new ListTasksForm(current).show());
        
        
         // aziz
       
         getToolbar().addMaterialCommandToSideMenu("Add User", FontImage.MATERIAL_SHOPPING_CART,  e -> new AddUserForm(current).show());
         getToolbar().addMaterialCommandToSideMenu("List User", FontImage.MATERIAL_SHOPPING_CART,  e-> new ListUsersForm(current).show());
         
         
         // adam
         
          getToolbar().addMaterialCommandToSideMenu("Liste des tournois", FontImage.MATERIAL_SHOPPING_CART,  e -> new ListCategorie_2AdminForm(current).show() );
          getToolbar().addMaterialCommandToSideMenu("  Ajputer un tournoi", FontImage.MATERIAL_EXIT_TO_APP,     e -> new AddCategorie_2Form(current).show() );
        
          
      
        
        // Mondher
        
       
         getToolbar().addMaterialCommandToSideMenu("Partie front", FontImage.MATERIAL_SHOPPING_CART,  e -> new ListCategorie_2Form(current).show());
         getToolbar().addMaterialCommandToSideMenu("Ajouter un equipement", FontImage.MATERIAL_SHOPPING_CART,  e -> new AddEquipementForm(current).show());
          getToolbar().addMaterialCommandToSideMenu("  Partie back Equipement", FontImage.MATERIAL_EXIT_TO_APP,  e ->  new ListEquipement_AdminForm(current).show());
        getToolbar().addMaterialCommandToSideMenu("  Partie back Categorie", FontImage.MATERIAL_TOPIC,  e -> new ListCategorie_2AdminForm(current).show());
        
        
       
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
