package Services;

import entities.Equipement;

 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.Myconn;

/**
 *
 * @author ASUS
 */
public class ServiceEquipement {
     
    
    static Connection cn = Myconn.getInstance().getConnection();
 
    
       
        public  void ajoutEquipement(Equipement  l) 
       {           
           
           try {
               String requete="INSERT INTO Equipement(name,marque,description,categorie2eq_id ,image,prix) values ('"+l.getName_e()+"','"+l.getMarque_e()+"','"+l.getDescription_e()+"','"+l.getTypequipement_e()+"','"+l.getImage_e()+"','"+l.getPrix_e()+"')";
              
              Statement st;
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        
         
            public  void supprimerEquipement(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from Equipement where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
                       
    public void updateEquipement(Equipement l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="update Equipement set nom='"+l.getName_e()+"' ,  image='"+l.getImage_e()+"' ,  prix='"+l.getPrix_e()+"' , categorie2eq_id ='"+l.getTypequipement_e()+"' , description='"+l.getDescription_e()+"'  ,  marque='"+l.getMarque_e()+" 'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les equipement
    
    public List<Equipement> listerEquipement(int id){
    List<Equipement> mylist = new ArrayList();
    try{
    String requet = "select name,image,prix,description,marque,categorie2eq_id from Equipement  ";
    Statement st2 = cn.createStatement();
        ResultSet rs = st2.executeQuery(requet);
        while(rs.next())
        {
        Equipement ps = new Equipement();
        ps.setName_e(rs.getString(1));
        ps.setImage_e(rs.getString(2));
        
        ps.setPrix_e(rs.getInt(3));
        ps.setDescription_e(rs.getString(4));
        
        ps.setMarque_e(rs.getString(5));
       
        ps.setId_e(rs.getInt(6));
        
        


        mylist.add(ps);
        }
    }
    catch(SQLException Ex)
            {System.out.print("errreur de selection");}
    
    
    return mylist;
    }
 
    public List<Equipement> afficher() throws SQLException {
        String req = "Select * from Equipement ";
        Statement statement= cn.createStatement();
        ResultSet rst = statement.executeQuery(req);
        System.out.println(rst.toString());
        List<Equipement> personnes = new ArrayList<Equipement>();
        while(rst.next()){
            
           Equipement p = new Equipement(rst.getInt("categorie2eq_id"),rst.getString("name"),rst.getString("marque"),rst.getString("description"),rst.getInt("id"),rst.getString("image"),rst.getFloat("prix"));
            personnes.add(p);
            
        
        }
        return personnes;
        
    }
             
            
    //a modifier 
    public void afficherListEquipement(int id)
            {
               List<Equipement> arrayList = listerEquipement(id);
        for(Equipement m : arrayList){
           System.out.println(m.getName_e()+","+m.getImage_e()+","+m.getPrix_e()+","+m.getDescription_e()+","+m.getMarque_e());
        }
            
            }        

    
}