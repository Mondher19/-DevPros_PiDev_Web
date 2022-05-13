package services;

import entities.Equipement;
import tools.MaConnexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipementService{

    Connection connexion;
    Statement stm;

    public EquipementService() {
        connexion = MaConnexion1.getInstance().getConnexion();

    }

    public void ajouterEquipement(Equipement a) throws SQLException {
        String req = "insert into equipement (name,marque,description,prix) values (?,?,?,?)";

        PreparedStatement ste = connexion.prepareStatement(req);

        ste.setString(1, a.getName());
        ste.setString(2, a.getMarque());
        ste.setString(3, a.getDescription());
        ste.setInt(4, a.getPrix());

        ste.executeUpdate();

    }

    public List<Equipement> afficherEquipement() throws SQLException {
        List<Equipement> Equipement = new ArrayList<>();
        String req = "select id,name,marque,description,prix from Equipement";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Equipement f = new Equipement(
                    rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("marque"),
                    rst.getString("description"),
                    rst.getInt("prix"));

            Equipement.add(f);
        }
        return Equipement;
    }

    public void modifierEquipement(Equipement a) throws SQLException {

        String req = "update Equipement set name='" + a.getName()+ "', marque='" + a.getMarque()+ "', description='" + a.getDescription()+ "', prix='" + a.getPrix()+ "'"
                + "where id ='" + a.getId() + "'";
        PreparedStatement ste = connexion.prepareStatement(req);

        ste.executeUpdate(req);

    }
    
     public void modifierprix(Equipement a) throws SQLException {

        String req = "update Equipement set prix='" + a.getPrix()+ "'"
                + "where id ='" + a.getId() + "'";
        PreparedStatement ste = connexion.prepareStatement(req);

        ste.executeUpdate(req);

    }

    public void supprimerEquipement(int id) throws SQLException {
        String sql = "delete from Equipement where id ='" + id + "'";
        try {
            stm = connexion.createStatement();
            stm.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Equipement> Trier(String critere) {

        List<Equipement> Equipement = new ArrayList();

        String req = "select * from Equipement order by " + critere;
        try {

            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                Equipement f = new Equipement();
                f.setId(rst.getInt(1));

                f.setName(rst.getString(2));
                f.setId(rst.getInt(3));
                f.setMarque(rst.getString(4));
                f.setPrix(rst.getInt(5));

                Equipement.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Equipement;
    }

    public Equipement rechercherParId(Equipement t) {

        List<Equipement> Equipement = new ArrayList<>();
        String sql = "select id,name,marque,description,prix FROM Equipement WHERE id ='" + t.getId() + "'";
        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                t.setMarque(rs.getString(3));
                t.setDescription(rs.getString(4));
                t.setPrix(rs.getInt(5));
                Equipement.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

     public Equipement rechercherParId1(Equipement t,int id) {

        List<Equipement> Equipement = new ArrayList<>();
        String sql = "select id,name,marque,description,prix FROM Equipement WHERE id ='" + id + "'";
        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                t.setMarque(rs.getString(3));
                t.setDescription(rs.getString(4));
                t.setPrix(rs.getInt(5));
                Equipement.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

}
