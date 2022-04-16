/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Equipement;
import entities.TypeEquipement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.Myconn;

/**
 *
 * @author Mondh
 */
public class ServiceTypeEquipement {
    
     
    static Connection cn = Myconn.getInstance().getConnection();
 
    
       
        public  void ajoutTypeEquipement(TypeEquipement  l) 
       {           
           
           try {
               String requete="INSERT INTO categorie2eq (name,image) values ('"+l.getName_et()+"','"+l.getImage_et()+"')";
              
              Statement st;
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        
         
            public  void supprimerTypeEquipement(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from categorie2eq where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
                       
    public void updateTypeEquipement(TypeEquipement l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="update categorie2eq set nom='"+l.getName_et()+"' ,  image='"+l.getImage_et()+"'  'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les categorie
    
    public List<TypeEquipement> listeTypeEquipement(int id){
    List<TypeEquipement> mylist = new ArrayList();
    try{
    String requet = "select name,image from categorie2eq  ";
    Statement st2 = cn.createStatement();
        ResultSet rs = st2.executeQuery(requet);
        while(rs.next())
        {
        TypeEquipement ps = new TypeEquipement();
        ps.setName_et(rs.getString(1));
        ps.setImage_et(rs.getString(2));
        
        
       

        mylist.add(ps);
        }
    }
    catch(SQLException Ex)
            {System.out.print("errreur de selection");}
    
    
    return mylist;
    }
             
            
    //a modifier 
    public void afficherListEquipement(int id)
            {
               List<TypeEquipement> arrayList = listeTypeEquipement(id);
        for(TypeEquipement m : arrayList){
           System.out.println(m.getName_et()+","+m.getImage_et());
        }
            
            }        
    
}
