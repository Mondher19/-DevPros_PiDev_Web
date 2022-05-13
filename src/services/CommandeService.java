package services;

import entities.Commande;
import tools.MaConnexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements commandeinterface<Commande> {

    Connection connexion;
    Statement stm;

    public CommandeService() {
        connexion = MaConnexion1.getInstance().getConnexion();

    }

    @Override
    public void ajouterCommande(Commande a) throws SQLException {
        String req = "insert into Commande (nom,prenom,adresse,numtelephone,email,id_panier) values (?,?,?,?,?,?)";

        PreparedStatement ste = connexion.prepareStatement(req);

        ste.setString(1, a.getNom());
        ste.setString(2, a.getPrenom());
        ste.setString(3, a.getAdresse());
        ste.setInt(4, a.getNumtelephone());
        ste.setString(5, a.getEmail());
        ste.setInt(6, a.getIdpanier());

        ste.executeUpdate();

    }

    @Override

    public List<Commande> afficherCommande() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from Commande";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commande f = new Commande(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("adresse"),
                    rst.getInt("numtelephone"),
                    rst.getString("email"),
                     rst.getInt("id_panier"));
                   
            commandes.add(f);
        }
        return commandes;
    }

    @Override
    public void modifierCommande(Commande a) throws SQLException {

        String req = "update Commande set nom='" + a.getNom() + "', prenom='" + a.getPrenom() + "', adresse='" + a.getAdresse() + "', numtelephone='" + a.getNumtelephone() + "',  email='" + a.getEmail() + "'"
                + "where id ='" + a.getId() + "'";
        PreparedStatement ste = connexion.prepareStatement(req);

        ste.executeUpdate(req);

    }

    @Override
    public void supprimerCommande(int id) throws SQLException {
        String sql = "delete from Commande where id ='" + id + "'";
        try {
            stm = connexion.createStatement();
            stm.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Commande> Trier(String critere) {

        List<Commande> commande = new ArrayList();

        String req = "select * from Commande order by " + critere;
        try {

            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                Commande f = new Commande();
                f.setId(rst.getInt(1));

                f.setNom(rst.getString(2));
                f.setPrenom(rst.getString(3));
                f.setAdresse(rst.getString(4));
                f.setNumtelephone(rst.getInt(5));
                f.setEmail(rst.getString(6));

                commande.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return commande;
    }

    public Commande rechercherParId(Commande t) {

        List<Commande> Commande = new ArrayList<>();
        String sql = "select id,nom,prenom,adresse,numtelephone,email,id_panier FROM commande WHERE id ='" + t.getId() + "'";
        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setPrenom(rs.getString(3));
                t.setAdresse(rs.getString(4));
                t.setNumtelephone(rs.getInt(5));
                t.setEmail(rs.getString(6));
           t.setIdpanier(rs.getInt(7));
                Commande.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    } 
    
    
    public Commande rechercherParEmail(Commande t) {

        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT id,nom,prenom,numtelephone,adresse,email,id_panier FROM Commande WHERE email=? ";
        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setString(1, t.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                 t.setNom(rs.getString(2));
                t.setPrenom(rs.getString(3));
                 t.setAdresse(rs.getString(4));
                t.setNumtelephone(rs.getInt(5));
               
               
                t.setEmail(rs.getString(6));
               
                t.setIdpanier(rs.getInt(7));
              
              
                commandes.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

}
