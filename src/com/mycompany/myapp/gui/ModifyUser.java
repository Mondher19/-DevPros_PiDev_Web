/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;


/**
 *
 * @author WALID
 */
public class ModifyUser extends Form {
    
    public ModifyUser(Form previous,User user1) {
        setTitle("Modify a user");
        setLayout(BoxLayout.y());
        Label lbid=new Label(user1.idtoString());
        TextField tfusername = new TextField("","Username");
        TextField tfemail = new TextField("","prenom");
        TextField tBio = new TextField("","Email");
     
        TextField tfpassword = new TextField("","Password");
        TextField tfpassword2 = new TextField("","Confirm Password");
        tfusername.setText(user1.getNom());
        tfemail.setText(user1.getMail());
        tBio.setText(user1.getPrenom());
        
        Button btnValider = new Button("Update");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfusername.getText().length()==0)|| (tfemail.getText().length()==0) || (tfpassword.getText().length()==0) || (tfpassword2.getText().length()==0) &&
                        !(tfpassword.getText().toString()!=tfpassword2.getText().toString()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User U = new User(tfusername.getText().toString(), tfemail.getText().toString(), tfpassword.getText().toString());     
                       
                        U.setId(user1.getId());
                        if( ServiceUser.getInstance().ModifyUser(U))
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
        
        addAll(lbid,tfusername,tfemail,tBio,tfpassword,tfpassword2,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
