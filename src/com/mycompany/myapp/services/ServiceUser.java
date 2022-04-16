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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> Users;
    public User CurrentUser;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(User U) {
       String url = Statics.BASE_URL + "/addusermobile";
       req.setUrl(url);
          System.out.println(url);
     
       req.addArgument("username", U.getNom());
       req.addArgument("prenom", U.getPrenom());
       
       req.addArgument("email", U.getMail());
       req.addArgument("password", U.getPassword());
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
    
    public boolean deleteUser(User U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/delusermobile/"+U.getId();
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.idtoString());
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
    
    
     public User Login(User U) {
         this.getAllUsers();
        System.out.println(U);
        
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/loginmobile";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("username", U.getNom());
       req.addArgument("password", U.getPassword());
         User Us = null;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String rep=new String(req.getResponseData());
                System.out.println(rep);
                if(rep.trim().equalsIgnoreCase("error"))
                {
                    Dialog.show("error", "login ou pwd invalid", "ok","cancel");
                }
                else
                {
                    ArrayList<User> Userss;
                
                Userss = parseUser1(new String(req.getResponseData()));
                System.out.println("user :"+Userss);
                req.removeResponseListener(this);
                
                 for(User u:Userss)
                {
                     CurrentUser=u;
                }
            
                }
                System.out.println(CurrentUser);
            }

            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CurrentUser;
    }
    public ArrayList<User> parseUser1(String jsonText){
        try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
                User U = new User();
                System.out.println(list);
               //float id = Float.parseFloat(obj.get("id").toString());
             // U.setId((int)id);
                U.setId(((int)Float.parseFloat(tasksListJson.get("id").toString())));
                U.setMail(tasksListJson.get("mail").toString());
                U.setNom(tasksListJson.get("nom").toString());
                U.setPrenom(tasksListJson.get("prenom").toString());
              
               // U.setPassword(tasksListJson.get("password").toString());
               
                Users.add(U);
                return Users;
            
            
            
        } catch (IOException ex) {
            
        }
        return Users;
    }

    public ArrayList<User> parseUsers(String jsonText){
 
               
                     try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();          
             Map<String,Object> GamesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));  
            List<Map<String,Object>> list = (List<Map<String,Object>>)GamesListJson.get("root");
                         for (Map<String,Object> obj : list) {
                             User U = new User();
                             U.setId(((int)Float.parseFloat(obj.get("id").toString())));
                            
                             U.setNom(obj.get("nom").toString());
                             U.setPrenom(obj.get("prenom").toString());
                             U.setPrenom(obj.get("password").toString());
                            
                          
                            // U.setPassword(obj.get("password").toString());
                             Users.add(U);
                         }
            
            
        } catch (IOException ex) {
            
        }
        return Users;
    }
    
    public ArrayList<User> getAllUsers(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/getusersmobile";
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    public boolean ModifyUser(User U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/updateusermobile/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.getId()+"");
       req.addArgument("nom", U.getNom());
       req.addArgument("prenom", U.getNom());
       req.addArgument("password", U.getPassword());
       req.addArgument("email", U.getMail());
       
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
    public boolean DeleteUser(User U) {
        System.out.println(U);
        System.out.println("********");
       String url = Statics.BASE_URL + "/removegamescat/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.getId()+"");
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
