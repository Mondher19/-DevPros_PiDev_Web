/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Equipement;
import entities.Pannier;
import entities.SendEmail;
import services.CommandeService;
import services.PannierService;

import tools.MaConnexion1;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.EquipementService;

/**
 * FXML Controller class
 *
 * @author wided
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField nomT;
    @FXML
    private Text alertNom;
    @FXML
    private TextField numtelT;

    @FXML
    private Text alertNumtel;
    @FXML
    private Text alertPrenom;
    @FXML
    private TextField prenomT;
    @FXML
    private Text alerttotal;
    @FXML
    private Text alertquantite;
    @FXML
    private TextField emailT;
    @FXML
    private Text alertEmail;

    @FXML
    private TextField adresseT;
    @FXML
    private Text alertadresse;

    private TextField idSuppression;
    private TableView<Commande> tableCommandes;
    private TableColumn<?, ?> id;
    private TableColumn<?, ?> nomC;
    private TableColumn<?, ?> prenomC;
    private TableColumn<?, ?> adresseC;
    private TableColumn<?, ?> numtelC;
    private TableColumn<?, ?> emailC;
    private Button b;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MaConnexion1.getInstance().getConnexion();
    ObservableList<Pannier> listP;

    @FXML
    private Text alertDate;
    @FXML
    private Button ajout;
    @FXML
    private TableView<Pannier> list;

    @FXML
    private TableColumn<?, ?> idT;
    @FXML
    private TableColumn<?, ?> nbrT;
    @FXML
    private TableColumn<?, ?> ideT;
    @FXML
    private TextField idp;
    PannierService ps = new PannierService();
    @FXML
    private Button retour;
    @FXML
    private TableColumn<?, ?> sommeT;
    @FXML
    private TextField nbreq;
    @FXML
    private Button modifier;
    @FXML
    private Label nb;
    @FXML
    private Button supprimer;

    public void initialize(URL url, ResourceBundle rb) {
        afficherListePanier();
        idp.setVisible(false);
        nbreq.setVisible(false);
        nbreq.setVisible(true);
        nb.setVisible(true);
        nb.setVisible(false);
        ObservableList<Pannier> listF;
        list.setOnMouseClicked((MouseEvent e) -> {

            Pannier cm = ps.rechercherParId(list.getSelectionModel().getSelectedItem());

            idp.setText(String.valueOf(cm.getId()));

        });

    }

    Alert alert = new Alert(Alert.AlertType.ERROR);

    public void afficherListePanier() {

        PannierService ps = new PannierService();
        try {
            List<Pannier> Pannier = ps.afficherPannier();
            listP = FXCollections.observableArrayList(Pannier);
            list.setItems(listP);
            idT.setCellValueFactory(new PropertyValueFactory<>("id"));

            nbrT.setCellValueFactory(new PropertyValueFactory<>("nbr"));

            ideT.setCellValueFactory(new PropertyValueFactory<>("id_equipement"));
            sommeT.setCellValueFactory(new PropertyValueFactory<>("somme"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterComande(ActionEvent event) throws SQLException, Exception {

        Commande s = new Commande();
        Boolean verif = true;

        if (nomT.getText().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        }
        if (emailT.getText().equals("")) {
            alertEmail.setText("Remplir le champs !!");
            verif = false;
        }
        // Control  prix
        if (numtelT.getText().equals("")) {
            alertNumtel.setText("Remplir le champs !!");
            verif = false;
        } else if (!numtelT.getText().matches("[\\d\\.]+")) {
            alertNumtel.setText("le numreo doit être un entier !!");
            verif = false;
        }

        if (prenomT.getText().equals("")) {
            alertPrenom.setText("Remplir le champs !!");
            verif = false;
        }

        if (adresseT.getText().equals("")) {
            alertadresse.setText("Remplir le champs !!");
            verif = false;
        }

        if (verif == true) {
            CommandeService cs = new CommandeService();
            String nom = nomT.getText();
            String prenom = prenomT.getText();
            String adresse = adresseT.getText();
            int num = Integer.parseInt(numtelT.getText());
            String email = emailT.getText();
            int idpanier = Integer.parseInt(idp.getText());
            Commande u = new Commande(num, idpanier, nom, prenom, adresse, email);
            cs.ajouterCommande(u);
            //JavaMailUtils2.sendMail(u);
            SendEmail cc = new SendEmail("letstravel48@gmail.com", "travel12345", "hamza.benmhenni@esprit.tn", " new cmnd ", "<center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                    + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                    + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                    + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                    + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>new cmnd for devpros </a></h1></td></tr></table></td> </tr><br> <br>"
                    + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                    + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                    + "<h2 > </h2><h4 style=\"color: #ffffff;\" +\" </h4>"
                    + "</div></td></tr> </table></td> </tr>"
                    + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                    + "<h2 style=\"margin-left:150px;\" +\" ></h2><h2 style=\"margin-left:150px;\" +\">Bonjour Nouvelle Commande  :  " + u.getEmail() + " " + " </h2><p  style=\"margin-left:150px;\" +\" >  Nouvelle commande !!! </p>"
                    + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + "</a></p></div></td></tr></table> </td></tr>"
                    + " </center>");

            //alert.setContentText("Votre commande est ajouté avec succées !!");
            //alert.showAndWait();
            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("Votre commande est ajouté avec succées !!").graphic(null)
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("equipementTest.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);
    }
    @FXML
    private void modifierPanier(ActionEvent event) throws SQLException {

        PannierService ps = new PannierService();
        EquipementService es = new EquipementService();
        if (nbreq.getText().equals("")) {
            alert.setContentText("Veuillez selectionner une panier !!");
            alert.showAndWait();
        } else {
            Equipement e = new Equipement();
            Pannier p = new Pannier();
            Pannier a = ps.rechercherParId(list.getSelectionModel().getSelectedItem());
            p.setId(a.getId());
            p.setId_equipement(a.getId_equipement());
            int idd=a.getId_equipement();
            Equipement eq = es.rechercherParId1(e,idd);
            e.setId(eq.getId());
            System.out.println(eq.getPrix());
            p.setNbr(Integer.parseInt(nbreq.getText()));
            p.setSomme(Integer.parseInt(nbreq.getText())*eq.getPrix());
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cette Commande ", "modifier Commande", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                ps.modifierPannier(p);
                afficherListePanier();

            }
        }
    }

    @FXML
    private void supprimerPanier(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette Panier ", "Suppression Panier", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            PannierService bs = new PannierService();
            try {
                Pannier u = new Pannier();
                Pannier a = ps.rechercherParId(list.getSelectionModel().getSelectedItem());
                u.setId(a.getId());
                ps.supprimerPannier(u.getId());
                afficherListePanier();
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert").text("Commande supprimer avec succÃ©").graphic(null)
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.show();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
