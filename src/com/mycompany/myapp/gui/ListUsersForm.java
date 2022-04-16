/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListUsersForm extends Form {
Form current;

    public  Container  addItem(User u)
    {
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.idtoString());
        Label lbnom=new Label(u.getNom());
        Label lbbio=new Label(u.getPrenom());
        Label btnmail=new Label(u.getMail());
        Button btdelete=new Button("Delete");
        Button btnmodify=new Button("Modify");
        
        cnt.addAll(lbid,lbnom,lbbio,btnmail,btnmodify,btdelete);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
        btnmodify.addActionListener(e-> new ModifyUser(current,u).show()); 
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceUser.getInstance().DeleteUser(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<User> list=ServiceUser.getInstance().getAllUsers();
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );
    return cnt2;
    }
    public ListUsersForm(Form previous) {
        setTitle("List Users");
        setLayout(BoxLayout.y());
        Button btreload=new Button("Reload");
        ArrayList<User> list=ServiceUser.getInstance().getAllUsers();
        SpanLabel sp = new SpanLabel();
        add(btreload);
        for (User user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
