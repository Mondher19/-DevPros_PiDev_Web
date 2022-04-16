/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.Resources;
import com.mycompany.app.entities.Equipement;
import com.mycompany.myapp.myapp.SideMenuBaseForm;

import com.mycompany.myapp.services.ServiceEquipement;


/**
 *
 * @author Raef
 */
public class LowListProductForm extends SideMenuBaseForm{
     Resources theme;
    Form current;

    public LowListProductForm(Form previous){
    
     
        
        
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
        
        
        
        
      
        
        
        
        
        
        
        
        Label Labfiltrer=new Label("Filter par :");
              ComboBox list=new ComboBox();
        list.addItem("Tous les produits");
        list.addItem("prix élevé -> prix bas");
        list.addItem("prix bas -> prix prix élevé");
            addAll(Labfiltrer,list);
            
            list.addActionListener((e)->{
        if(list.getSelectedIndex()==0){
          
        new ListEquipementForm(previous).show();
        }
        
        if(list.getSelectedIndex()==1){
            new HighListProductForm(previous).show();
          
        }
        if(list.getSelectedIndex()==2){
  
            new LowListProductForm(previous).show();
        }
        
            });

        for (Equipement p : ServiceEquipement.getInstance().getLowProduits()) {

            
            
            Label tfname = new Label();
            tfname.setText("Nom : " + p.getName_e());
            Label tfprice = new Label();
            tfprice.setText("Prix : " + p.getPrix_e()+" DT");
              Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt3 = new Container(BoxLayout.x());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
            Button tfShowSingle = new Button("Détails");
            Button tfUpdate = new Button("Modifier");
            Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
            cnt3.addAll(tfShowSingle,tfPanier, tfUpdate, tfdelete);
            cnt2.addAll(tfname, tfprice,cnt3);

                String urll = "http://localhost/projectpidev/public/uploads/" + p.getImage_e();
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("duke.png").scaled(250, 250), false);
            URLImage urlimg = URLImage.createToStorage(enc, p.getName_e(), urll);
            ImageViewer image = new ImageViewer(urlimg);
            Image im = image.getImage();

            cnt1.add(im);
            cnt1.add(cnt2);
            add(cnt1);
            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer  " + p.getName_e()+ " !!", "OK", "Cancel")) {
                    if (ServiceEquipement.getInstance().deleteEquipement(p)) {
 ToastBar.showMessage("Le produit est supprimé",FontImage.MATERIAL_WARNING);
                        new ListEquipementForm(previous).show();
                    } else {
                        new ListEquipementForm(previous).show();
                    }
                }

            });
            tfShowSingle.addActionListener((e)->{
                
                
            ServiceEquipement.getInstance().getEquipementSingle(p);
            
            new ShowSingleForm(p,current).show(
            );
            
            
            
            
            });
            
            
            tfUpdate.addActionListener(e1->{
            
            
            
            
            new UpdateEquipementForm(p,current).show();
            
            
            
            
            });
            
            
              tfPanier.addActionListener((e2) -> {
            
            ServiceEquipement.getInstance().AddPanierEquipement(p);
            
            if(ServiceEquipement.getInstance().AddPanierEquipement(p)){
            
              ToastBar.showMessage("Le produit a été ajouté à votre panier ", FontImage.MATERIAL_INFO);
            
            
            }
            
            
            
            
            });
            
            

        }
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
     setupSideMenu(theme); 
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    
    
    
    }