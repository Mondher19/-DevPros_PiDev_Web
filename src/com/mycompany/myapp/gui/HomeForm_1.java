/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author bhk
 */
public class HomeForm_1 extends Form{
Form current;
    public HomeForm_1(User U) {
        User Current_user;
        current=this; //Back 
        setTitle("Home User :"+U.getNom());
        System.out.println(U);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add User");
        Button btnLogout = new Button("Logout");
        Button btnListTasks = new Button("List User");
        btnAddTask.addActionListener(e-> new AddUserForm(current).show());
        btnListTasks.addActionListener(e-> new ListUsersForm(current).show());
        btnLogout.addActionListener(e->{
        new LoginUserForm().show();
        } );
        addAll(btnAddTask,btnListTasks,btnLogout);
        
        
    }
    
    
}
