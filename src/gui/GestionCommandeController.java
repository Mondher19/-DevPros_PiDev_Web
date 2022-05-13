/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Pannier;
import entities.Pdf;
import services.CommandeService;
import services.PannierService;

import tools.MaConnexion1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author wided
 */
public class GestionCommandeController implements Initializable {

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
   @FXML
    private TableColumn<?, ?> sommeC;
    @FXML
    private TextField idSuppression;
    @FXML
    private TableView<Commande> tableCommandes;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nomC;
    @FXML
    private TableColumn<?, ?> prenomC;
    @FXML
    private TableColumn<?, ?> adresseC;
    @FXML
    private TableColumn<?, ?> numtelC;
    @FXML
    private TableColumn<?, ?> emailC;
      @FXML
    private Button gM;
@FXML
    private TableView<Pannier> tabp;
    @FXML
    private TableColumn<Pannier, Integer> descColumn;
    @FXML
    private TableColumn<Pannier, Integer> dureeoColumn;
    @FXML
    private ComboBox<String> choisir; 
        public ObservableList<Pannier> ProduitData = FXCollections.observableArrayList();

    @FXML
    private Button btntrier;
    private ImageView im_qrcode;
    private String deriterio;
    private static final String DIR = "QRDir";
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MaConnexion1.getInstance().getConnexion();
    ObservableList<Commande> listF;
    private List<Commande> commandes = new ArrayList<>();
     private PannierService vs = new PannierService();

    /**
     * Initializes the controller class.
     *
     *
     *
     *
     */
    @FXML
    private TextField filterFieldC;

    @FXML
    private Text alertDate;
    @FXML
    private Text alertIdReservations;

    public void afficherListeCommande() {

        CommandeService bs = new CommandeService();
        try {
            List<Commande> commandes = bs.afficherCommande();
            listF = FXCollections.observableArrayList(commandes);
            tableCommandes.setItems(listF);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            numtelC.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
            nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));

            emailC.setCellValueFactory(new PropertyValueFactory<>("email"));

            prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
              sommeC.setCellValueFactory(new PropertyValueFactory<>("idpanier"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    CommandeService c = new CommandeService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //QRCODE
        deriterio = new File("").getAbsolutePath();
        deriterio += File.separator + DIR;
        File file = new File(deriterio);
        if (!file.isDirectory()) {
            file.mkdir();
        }

        tableCommandes.setOnMouseClicked((MouseEvent e) -> {

            Commande cm = c.rechercherParId(tableCommandes.getSelectionModel().getSelectedItem());

            nomT.setText(cm.getNom());

            prenomT.setText(cm.getPrenom());

            adresseT.setText(cm.getAdresse());
            numtelT.setText(String.valueOf(cm.getNumtelephone()));
            emailT.setText(cm.getEmail());

            idSuppression.setText(String.valueOf(cm.getId()));
        });

        afficherListeCommande();
        RechercheCommande();

        choisir.getItems().addAll(
                "id",
                "nom",
                "email"
        );
    }

    private void RechercheCommande() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Commande> filteredData = new FilteredList<>(listF, f -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFieldC.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(commande -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (commande.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(commande.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (String.valueOf(commande.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Commande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCommandes.comparatorProperty());
        tableCommandes.setItems(sortedData);
    }

    private void ajouterCommande(ActionEvent event) {

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

            s = new Commande(Integer.parseInt(numtelT.getText()), nomT.getText(), emailT.getText(), adresseT.getText(), prenomT.getText());
            if (!listF.contains(s)) {

                System.out.println("erreur");

                CommandeService bs = new CommandeService();
                try {

                    bs.ajouterCommande(s);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Commande ajoutée avec succés");
                    alert.show();
                    nomT.setText("");

                    numtelT.setText("");
                    prenomT.setText("");
                    emailT.setText("");

                    idSuppression.setText("");

                    afficherListeCommande();
                    Notifications notificationBuilder = Notifications.create()
                            .title("Alert").text("Commande ajouteé avec succÃ©").graphic(null)
                            .position(Pos.BOTTOM_RIGHT);
                    notificationBuilder.darkStyle();
                    notificationBuilder.show();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" R ");
                alert.setContentText("Commande existe deja");
                alert.show();
            }
        }

    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette Commande ", "Suppression Commande", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            CommandeService bs = new CommandeService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                bs.supprimerCommande(id);

                idSuppression.setText("");
                afficherListeCommande();
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert").text("Commande supprimer avec succÃ©").graphic(null)
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void Trier(ActionEvent event) {

        CommandeService us = new CommandeService();
        List<Commande> commande = us.Trier((choisir.getSelectionModel().getSelectedItem()).toString());
        try {
            listF = FXCollections.observableArrayList(commande);
            tableCommandes.setItems(listF);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));

            emailC.setCellValueFactory(new PropertyValueFactory<>("email"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierCommande(ActionEvent event) throws SQLException {

        Boolean verif = true;


        if (numtelT.getText().equals("")) {
            alertNumtel.setText("Remplir le champs !!");
            verif = false;
        } else if (!numtelT.getText().matches("[\\d\\.]+")) {
            alertNumtel.setText("le numreo doit être un entier !!");
            verif = false;
        }
        if (nomT.getText().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        }
        if (emailT.getText().equals("")) {
            alertEmail.setText("Remplir le champs !!");
            verif = false;
        }
        // Control  


        if (prenomT.getText().equals("")) {
            alertPrenom.setText("Remplir le champs !!");
            verif = false;
        }

        if (adresseT.getText().equals("")) {
            alertadresse.setText("Remplir le champs !!");
            verif = false;
        }

        if (verif == true) {

            CommandeService bs = new CommandeService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cette Commande ", "modifier Commande", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                Commande Commande = new Commande();
                Commande a = bs.rechercherParId(tableCommandes.getSelectionModel().getSelectedItem());
                Commande.setId(a.getId());
                Commande.setNom(nomT.getText());
                Commande.setPrenom(prenomT.getText());
                Commande.setAdresse(adresseT.getText());
                Commande.setNumtelephone(Integer.parseInt(numtelT.getText()));
                Commande.setEmail(emailT.getText());
                bs.modifierCommande(Commande);
                afficherListeCommande();

            }
        }
    } 
    
    
    
      @FXML
    private void generatePdf(ActionEvent event) {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("listCommande");
            System.out.println("validé");
        } catch (Exception ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 

    @FXML
    private void ViewPanier(ActionEvent event)    throws SQLException {
        
     
        Commande T = tableCommandes.getSelectionModel().getSelectedItem();
               int id= T.getId();
               
               System.out.println(id);
                 ProduitData.clear();
               List<Pannier> Produit = getpannierbyId(id);
            ProduitData.addAll(Produit);
    	descColumn.setCellValueFactory(new PropertyValueFactory<Pannier,Integer>("nbr"));
    	dureeoColumn.setCellValueFactory(new PropertyValueFactory<Pannier,Integer>("somme"));
    	

        

        tabp.setItems(ProduitData);
    }
      
     List<Pannier> getpannierbyId(int id) throws SQLException{
        List<Pannier> arr = new ArrayList<>();
         try {
        PreparedStatement pre = connexion.prepareStatement("SELECT nbr ,somme from panier o ,commande p where p.id_panier=o.id and p.id=?;"); //ORDER BY P asc
         pre.setInt(1, id);
         ResultSet rs = pre.executeQuery();

             while(rs.next()){
                     //Int Nbr = rs.getInt("nbr");
  int Nbr= Integer.parseInt(rs.getString("nbr"));
                    int Somme= Integer.parseInt(rs.getString("somme"));
                        
                    
                     Pannier m=new Pannier(Nbr,Somme);
                    arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;
    } 
     @FXML
    private void gM(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GestionMenu.fxml"));
        Parent root = loader.load();
        gM.getScene().setRoot(root);
    }

}
