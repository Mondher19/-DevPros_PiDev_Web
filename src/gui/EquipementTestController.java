/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Equipement;
import entities.Pannier;
import services.CommandeService;
import services.EquipementService;
import services.PannierService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author wided
 */
public class EquipementTestController implements Initializable {

    @FXML
    private TableColumn<?, ?> nameT;
    @FXML
    private TableColumn<?, ?> marqueT;
    @FXML
    private TableColumn<?, ?> descriptionT;
    @FXML
    private TableColumn<?, ?> prixT;
    @FXML
    private TextField name;
    @FXML
    private TextField marque;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private TableView<Equipement> list;
    ObservableList<Equipement> listF;
    @FXML
    private TableColumn<?, ?> idT;
    @FXML
    private Button ajout;
     @FXML
    private Button gM;
    
    @FXML
    private Label pl;
    int n = 0;
    @FXML
    private Label mo;
    @FXML
    private TextField id;
    private Button pc;
    @FXML
    private Button ps;
    @FXML
    private Label nbr1;

    /**
     * Initializes the controller class.
     */
    public void afficherListeEquipement() {

        EquipementService bs = new EquipementService();
        try {
            List<Equipement> Equipement = bs.afficherEquipement();
            listF = FXCollections.observableArrayList(Equipement);
            list.setItems(listF);
            idT.setCellValueFactory(new PropertyValueFactory<>("id"));

            nameT.setCellValueFactory(new PropertyValueFactory<>("name"));

            marqueT.setCellValueFactory(new PropertyValueFactory<>("marque"));
            descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
            prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    EquipementService c = new EquipementService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setVisible(false);
        nbr1.setText(String.valueOf(n));
        list.setOnMouseClicked((MouseEvent e) -> {

            Equipement cm = c.rechercherParId(list.getSelectionModel().getSelectedItem());
            id.setText(String.valueOf(cm.getId()));
            name.setText(cm.getName());

            marque.setText(cm.getMarque());

            description.setText(cm.getDescription());
            prix.setText(String.valueOf(cm.getPrix()));
        });

        afficherListeEquipement();
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

     @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        int nbre = Integer.parseInt(nbr1.getText());
        if (name.getText().equals("")) {
            alert.setContentText("Veuillez selectionner un equipemenet !!");
            alert.showAndWait();
            
        } else if((nbre==0)||(nbre<0)) {
            alert.setContentText("Veuillez selectionner un nombre supérieur a 0 !!");
            alert.showAndWait();
        }else{
            
            int ide = Integer.parseInt(id.getText());
            int somme = Integer.parseInt(prix.getText()) * nbre;
            Pannier u = new Pannier(nbre, ide, somme);
            PannierService ps = new PannierService();
            ps.ajouterPannier(u);
            alert.setContentText("Equipement ajouté au panier avec succées ");
            alert.showAndWait();
        }
    }

    @FXML
    private void plus(MouseEvent event) {
        nbr1.setText(String.valueOf(n = n + 1));
    }

    @FXML
    private void moin(MouseEvent event) {
        nbr1.setText(String.valueOf(n = n - 1));
    }

    @FXML
    private void passercommande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterCommande.fxml"));
        Parent root = loader.load();
        ps.getScene().setRoot(root);
    } 
     @FXML
    private void gM(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GestionMenu.fxml"));
        Parent root = loader.load();
        gM.getScene().setRoot(root);
    }

}
