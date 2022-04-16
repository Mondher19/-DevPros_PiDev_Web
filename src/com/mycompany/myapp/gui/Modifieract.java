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
import com.mycompany.myapp.entities.Act;
import com.mycompany.myapp.services.ServiceAct;

/**
 *
 * @author yassine
 */
public class Modifieract extends Form {

    Form current;

   public Modifieract(Act p , Form previous) {
        setTitle("Modifier actualité");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(p.getId()), "id");
        TextField tfNom = new TextField(p.getNom(), "name");
        TextField tfDesc = new TextField(p.getDesc(), "description");
        TextField tfImg = new TextField(p.getImg(), "image");
       

        Button btnValider = new Button("Modifier");
        Button btnRet = new Button("Retourner");
       //btnRet.addActionListener(e-> new HomeForm(p).show() );

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) && (tfDesc.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Act p = new Act(Integer.parseInt(id.getText()), tfNom.getText(), tfDesc.getText(), tfImg.getText());
                        System.out.println(p.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceAct.getInstance().updatee(p)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
            
        });
        

        addAll(id, tfNom, tfDesc, tfImg, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
}