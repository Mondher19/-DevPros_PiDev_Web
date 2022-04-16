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
import com.mycompany.app.entities.TypeEquipement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
;

/**
 *
 * @author Raef
 */
public class ServiceType {
    public ArrayList<TypeEquipement> types;
    
    public static ServiceType instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceType() {
         req = new ConnectionRequest();
    }

    public static ServiceType getInstance() {
        if (instance == null) {
            instance = new ServiceType();
        }
        return instance;
    }
    
    
   
    public ArrayList<TypeEquipement> parseTypes(String jsonText){
        try {
            types =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                TypeEquipement p = new TypeEquipement();
                
              int id_et=(int) Float.parseFloat(obj.get("id").toString());

                p.setId_et((int)id_et);
                p.setName_et(obj.get("name").toString());          
                p.setImage_et(obj.get("image").toString());
            
                
                 
                types.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return types;
    }
     
     
     public ArrayList<TypeEquipement> getAllTypes(){
       
         
         String url = Statics.BASE_URL+"/listcat_1_json";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                types = parseTypes(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return types;
    }
    
     
     public boolean deleteCategorie_2(TypeEquipement p) {
        String url = Statics.BASE_URL + "/remove_cat_json_cat_2/" + p.getId_et();
               
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
     
     
        public boolean addCategorie(TypeEquipement p) {
        String url = Statics.BASE_URL + "/addcat_jason/?name=" +p.getName_et()+ "&image=" +p.getImage_et() ;
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
        
           public boolean updateCategorie(TypeEquipement p) {
        String url = Statics.BASE_URL + "/updatecat_json/" + p.getId_et()+ "?name=" +p.getName_et()+ "&image=" +p.getImage_et() ;
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