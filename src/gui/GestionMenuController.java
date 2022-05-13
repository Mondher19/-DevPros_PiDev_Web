/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;



 
public class GestionMenuController implements Initializable {

   @FXML
    private Button ga;
    @FXML
    private Button gu;
    @FXML
    private Button gr;
     @FXML
    private Button gavis;
      @FXML
    private Button greclamtion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void gu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GestionCommande.fxml"));
        Parent root = loader.load();
        gu.getScene().setRoot(root);
    } 
     @FXML
    private void gr(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("equipementTest.fxml"));
        Parent root = loader.load();
        gr.getScene().setRoot(root);
    }
    
}
