/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.app.entities.Equipement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mondher bha en personne
 */
public class ServiceEquipement {
    
     public ArrayList<Equipement> equipements;
    
    public static ServiceEquipement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEquipement() {
         req = new ConnectionRequest();
    }

    public static ServiceEquipement getInstance() {
        if (instance == null) {
            instance = new ServiceEquipement();
        }
        return instance;
    }

     public boolean addEquipement(Equipement p,String tp) {
        String url = Statics.BASE_URL + "/add_jason?name=" +p.getName_e()+ "&categorie_2=" +tp+ "&marque="+p.getMarque_e()+"&description=" +p.getDescription_e()+"&image=" +p.getImage_e()+"&prix="+p.getPrix_e();
      req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     public boolean deleteEquipement(Equipement p) {
        String url = Statics.BASE_URL + "/remove_json/" + p.getId_e();
               
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
    
     
     public ArrayList<Equipement> parseEquipement(String jsonText){
        try {
            equipements =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Equipement p = new Equipement();
                
           /*     Map<String,Object> objType =(Map<String,Object>) obj.get("typequipement_e");
                
                float idType=Float.parseFloat(objType.get("idTp").toString());
                
                int idTypeI=(int) idType;
                p.setTypequipement_e(idTypeI); */
                
                int id_e=(int) Float.parseFloat(obj.get("id").toString());
                
                p.setId_e((int)id_e);
                p.setName_e(obj.get("name").toString());
                p.setMarque_e(obj.get("marque").toString());               
                p.setPrix_e(((float)Float.parseFloat(obj.get("prix").toString())));                       
                p.setImage_e(obj.get("image").toString());
               p.setDescription_e(obj.get("description").toString());

             
//             
//                 Map<String,Object> objuser =(Map<String,Object>) obj.get("userid");
//                float idUser=Float.parseFloat(objuser.get("id").toString());
//               int idUserI=(int) idUser;
//               p.setUserId(idUserI);
//                
                equipements.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return equipements;
    }
     
     
     public ArrayList<Equipement> getAllEquipements(){
       
         
         String url = Statics.BASE_URL+"/listproduct_jason";      
        req.setUrl(url);
      /*  req.setPost(false); */
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            /**/          @Override
            public void actionPerformed(NetworkEvent evt) {
                equipements = parseEquipement(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return equipements;
    }
     
       public ArrayList<Equipement> getEquipementSingle_cat(int z){
       
         String url = Statics.BASE_URL+"/listequipement_json/"+ z;      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return equipements;
    }
     
     
     
 
    
    
     
      public ArrayList<Equipement> getEquipementSingle(Equipement p){
       
         
         String url = Statics.BASE_URL+"/listproduct_1_json/"+ p.getId_e();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return equipements;
    }
      
      
      
       public boolean UpdateEquipement(Equipement p,String tp) {
        String url = Statics.BASE_URL + "/update_json/" + p.getId_e()+ "?name=" +p.getName_e()+ "&categorie_2=" +tp+ "&marque="+p.getMarque_e()+"&description=" +p.getDescription_e()+"&image=" +p.getImage_e()+"&prix="+p.getPrix_e();
               
               
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
    
       
       
       
        public boolean AddPanierEquipement(Equipement p) {
        String url = Statics.BASE_URL + "/panier/addPanierMobile/" + p.getId_e();
               
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
        
       
     
      public boolean deleteEquipementPanierMobile(Equipement p) {
        String url = Statics.BASE_URL + "/panier/RemovePanierMobile/" + p.getId_e();
               
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       
       
       
}