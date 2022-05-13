package services;

import entities.Equipement;
import entities.Pannier;

import tools.MaConnexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PannierService implements pannierinterface<Pannier> {

    Connection connexion;
    Statement stm;

    public PannierService() {
        connexion = MaConnexion1.getInstance().getConnexion();

    }

    public void ajouterPannier(Pannier a) {
        String req = "insert into Panier(nbr,id_equipement,somme) values (?,?,?)";

        PreparedStatement ste;
        try {
            ste = connexion.prepareStatement(req);

            ste.setInt(1, a.getNbr());
            ste.setInt(2, a.getId_equipement());
            ste.setInt(3, a.getSomme());

            ste.executeUpdate();
            System.out.println("Pannier Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Pannier> afficherPannier() throws SQLException {
        List<Pannier> panniers = new ArrayList<>();
        String req = "select id,nbr,id_equipement,somme from Panier";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Pannier b = new Pannier(rst.getInt("id"),
                    rst.getInt("nbr"),
                    rst.getInt("id_equipement"),
                    rst.getInt("somme")
            );

            panniers.add(b);
        }
        return panniers;
    }

    public void modifierPannier(Pannier a) throws SQLException {

        String req = "update Panier set nbr='" + a.getNbr() + "', somme='" + a.getSomme()+ "'"
                + "where id ='" + a.getId() + "'";
        PreparedStatement ste = connexion.prepareStatement(req);

        ste.executeUpdate(req);

    }

    public void supprimerPannier(int id) throws SQLException {
        String sql = "delete from Panier where id ='" + id + "'";
        try {
            stm = connexion.createStatement();
            stm.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Integer> getIdPannier() throws SQLException {
        List<Integer> panniers = new ArrayList<>();
        String req = "select * from Panier";
        stm = connexion.createStatement();

        try {

            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                panniers.add(rst.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return panniers;

    }

    public Pannier rechercherParId(Pannier t) {

        List<Pannier> Pannier = new ArrayList<>();
        String sql = "select * FROM Panier WHERE id ='" + t.getId() + "'";
        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNbr(rs.getInt(2));
                t.setId_equipement(rs.getInt(3));
                t.setSomme(rs.getInt(4));
                Pannier.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
}
