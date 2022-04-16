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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author bhk
 */
public class LoginUserForm extends Form{

    public LoginUserForm() {
        setTitle("Login");
        setLayout(BoxLayout.y());
        
        TextField tfusername = new TextField("","Username");
        TextField tfpassword = new TextField("","Password");
        Button btnAddTask = new Button("Register");
tfpassword.setConstraint(TextField.PASSWORD);
        Button btnValider = new Button("Login");
   //     Button btnlist = new Button("listuser");
        btnAddTask.addActionListener(e-> new AddUserForm(this).show());
  //      btnlist.addActionListener(e-> new ListUsersForm(this).show());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfusername.getText().length()==0)||(tfpassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User U = new User(tfusername.getText().toString(), tfpassword.getText().toString());   
                        User Us=ServiceUser.getInstance().Login(U);
                        System.out.println("1"+Us);
                        if(Us.getId()!=0)
                        {
                                                    System.out.println("2"+Us);
                           btnValider.addActionListener(e-> new HomeForm_1(Us).show());
                           
                        }else
                            Dialog.show("ERROR", "Invalid Credentials", new Command("OK"));
                    } catch (NumberFormatException e) {
                        
                    }
                    
                    
                }
                
                
            }
        });
        addAll(tfusername,tfpassword,btnValider,btnAddTask);
                
    }
    
    
}
