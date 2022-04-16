/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.TypeTournoi;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.TypeTournoi;
import com.mycompany.myapp.myapp.SideMenuBaseForm;
import com.mycompany.myapp.services.ServiceTypeT;

/**
 *
 * @author eyaam
 */
public class ModifierTypeT extends SideMenuBaseForm {
    
       public ModifierTypeT(Resources res,Form previous,TypeTournoi fi) {
        super("Modifier Type Tournoi ", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
       
     //   super.addSideMenu(res);
       
        tb.addSearchCommand(e -> {});
       
       
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
       
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2,
                            facebook, twitter
                    )
                )
        ));

        Label type = new Label(String.valueOf(fi.getNom_type()));
       // TextField type = new TextField("", "entrer Vue!!");
        type.setUIID("TextFieldBlack");
       addStringValue("NomType", type);

       // Label description = new Label(String.valueOf(fi.getDescription()));
       TextField description = new TextField("", "entrer description");
        description.setUIID("TextFieldBlack");
        addStringValue("DescType", description);

       
       
       
  
               
        Button Edit = new Button("Edit");
        Edit.addActionListener((evt) -> {
            ServiceTypeT sp = new ServiceTypeT();
            
            fi.setNom_type(String.valueOf(type.getText()));
            fi.setDesc_type(String.valueOf(description.getText()));
            sp.editPanier(fi);
            Dialog.show("Success","Emplacement modifier avec success",new Command("OK"));
          //  new NewsfeedForm(res).show();
           
        });
        addStringValue("", FlowLayout.encloseRightMiddle(Edit));
       
    }
   
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
     //   add(createLineSeparator(0xeeeeee));
 }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
