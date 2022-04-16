/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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


/**
 *
 * @author bhk
 */
public class AddCategorie_2Form extends Form{

    public AddCategorie_2Form(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un Type de Tournoi");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("","Nom");
        TextField tfImage = new TextField("","Image");
       
       
        
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
                        TypeEquipement t = new TypeEquipement(tfName.getText(),tfImage.getText());
                       
                       
               
                        if( ServiceType.getInstance().addCategorie(t))
                            Dialog.show("Success user added","Product added ",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfImage,btnValider);
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