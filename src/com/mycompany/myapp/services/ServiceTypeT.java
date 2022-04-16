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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.TypeTournoi;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author SIHEM
 */
public class ServiceTypeT{

    public ArrayList<TypeTournoi> emp;
   
    public static ServiceTypeT instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceTypeT() {
         req = new ConnectionRequest();
    }

    public static ServiceTypeT getInstance() {
        if (instance == null) {
            instance = new ServiceTypeT();
        }
        return instance;
    }
   


public ArrayList<TypeTournoi> parsePanies(String jsonText){
                try {
 char firstChar = jsonText.charAt(0);
                    if(firstChar != '[')
                    {
                    jsonText="["+jsonText+"]";
                    }
                    System.out.println(jsonText);
            emp=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                TypeTournoi a = new TypeTournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setDesc_type(obj.get("desctype").toString());
                a.setNom_type(obj.get("nomtype").toString());
                emp.add(a);


            }
        } catch (IOException ex) {
            
        }
        return emp;
    }
 

    public ArrayList<TypeTournoi> getAlltTour(){
        String url = Statics.BASE_URL+"/type/tournoi/afficheTour";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emp = parsePanies(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return emp;
    }

//ajout 
    public void ajoutTour(TypeTournoi a) {
        
        String url =Statics.BASE_URL+"addEmpJSON?NomType="+a.getNom_type()+"&DescType="+a.getDesc_type(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    

        public boolean editPanier(TypeTournoi u) {
        String url = Statics.BASE_URL + "/type/tournoi/updatetour/"+u.getId()+
                "?NomType="+u.getNom_type()+"&DescType="+u.getDesc_type();
               req.setUrl(url);
               System.out.println(url);
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

    public boolean deletePanier(TypeTournoi fi) {
        String url = Statics.BASE_URL + "/type/tournoi/supprimerTour/" + fi.getId();
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
