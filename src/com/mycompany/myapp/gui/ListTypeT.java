/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.TypeTournoi;
import com.mycompany.myapp.myapp.SideMenuBaseForm;
import com.mycompany.myapp.services.ServiceTypeT;
import java.util.ArrayList;

/**
 *
 * @author eyaam
 */
public class ListTypeT extends SideMenuBaseForm{
     Form current;
    public ListTypeT(Resources res) {
                 super("Newsfeed", BoxLayout.y());
           //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <TypeTournoi> paniers = new ArrayList();
                        ServiceTypeT sa =new ServiceTypeT();
                    paniers=sa.getAlltTour();
                        Button btnadd = new Button("Ajouter");
                     add(btnadd);
                     btnadd.addActionListener((evt) -> {
                         new AjouterTypeT(res).show();
                    });
                             for (TypeTournoi fi : paniers) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Nom : "+String.valueOf(fi.getNom_type()));
                            m.setTextLine2("Description :"+String.valueOf(fi.getDesc_type()));
                           
                            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette utilisateur ?", "Supprimer", "Annuler")) {
                        if( ServiceTypeT.getInstance().deletePanier(fi)){
                            {
                                   Dialog.show("Success","supprimer",new Command("OK"));
                                  
                            }
                   
                }
            }
                else
                {
                      if (Dialog.show("Confirmation", "Voulez vous Modifier cette article?", "Oui", "Non")) {
                                   new ModifierTypeT(res,current,fi).show();
                                       }

                }
            }
        });

                            add(m);
                             }
                     revalidate();
                       
                    });
                   
                });
               
    getToolbar().addSearchCommand( e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);

   
    
        getToolbar().addMaterialCommandToSideMenu("list", FontImage.MATERIAL_UPDATE, e -> new ListTypeT(res).show());
      //  getToolbar().addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_CART, e -> new MonPanier(res).show());
     
    
     getToolbar().addMaterialCommandToSideMenu("Type", FontImage.MATERIAL_UPDATE, e -> new AjouterTypeT(res));
    
     //getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show())
    
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
                             
}