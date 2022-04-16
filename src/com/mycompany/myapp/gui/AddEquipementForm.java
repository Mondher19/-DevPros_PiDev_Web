/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.app.entities.Equipement;
import com.mycompany.app.entities.TypeEquipement;
import com.mycompany.myapp.services.ServiceEquipement;
import com.mycompany.myapp.services.ServiceType;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 *
 * @author bhk
 */
public class AddEquipementForm extends Form{
    
    
    

    public AddEquipementForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        
      
      
        
        
        setTitle("Ajouter un equipement");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("","Nom");
        TextField tfMarque = new TextField("","Marque");
        TextField tfDesc = new TextField("","Description");
        TextField tfimage = new TextField("","Image ");
         TextField tfPrix = new TextField("","Prix ");
          Button btnUpload = new Button("Upload");
          
          
          btnUpload.addActionListener((evt) -> {
            if (!"".equals(tfimage.getText())) {
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                
                cr.setUrl(Statics.URL_UPLOAD);
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
                cr.setFilename("file", tfimage.getText() + ".jpg");//any unique name you want

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);
            }else{
                Dialog.show("Error", "Invalid image name", "Ok", null);
            }
        });
          
          
      
        
         ComboBox tftypes=new ComboBox();
         try {
           for(TypeEquipement tp : ServiceType.getInstance().getAllTypes()){
               
               tftypes.addItem(tp.getId_et());
           }
           } catch (Exception e) {
       }
   
         

       
       // add(listens);
        
        
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Equipement t = new Equipement(1,tfName.getText(),tfMarque.getText(),tfDesc.getText(),tfimage.getText(),Float.parseFloat(tfPrix.getText()));
                       
                       
               
                        if( ServiceEquipement.getInstance().addEquipement(t, tftypes.getSelectedItem().toString()))
                            Dialog.show("Success user added","Product added ",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfMarque,tfimage,btnUpload,tfDesc,tftypes,tfPrix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
/*
extends Form {
    public AddVoitureForm(Form previous){
        setTitle("Ajoutez une nouvelle voiture");
        setLayout(BoxLayout.y());
        
        TextField tfMatricule = new TextField("","Matricule");
        TextField tfModele= new TextField("", "Modele");
        TextField tfMarque = new TextField("","Marque");
        TextField tfPrix= new TextField("", "Prix");
        TextField tfDescription = new TextField("","Description");
        TextField tfboitema= new TextField("", "Boite_ma");
        TextField tfVille= new TextField("", "Ville");
        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfMatricule.getText().length()==0)||(tfModele.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Voiture v = new Voiture();
                        v.setMatricule(tfMatricule.getText());
                        v.setModele(tfModele.getText());
                        v.setMarque(tfMarque.getText());
                        v.setPrix(Integer.parseInt(tfPrix.getText()));
                        v.setDescription(tfDescription.getText());
                        v.setBoite_ma(tfboitema.getText());
                        v.setVille(tfVille.getText());
                        if( new ServiceVoiture().addVoiture(v))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }                    
                }                
            }
        });
        addAll(tfMatricule,tfModele,tfMarque,tfPrix,tfDescription,tfboitema,tfVille,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        
        
    }
    
    
}
Aa
*/