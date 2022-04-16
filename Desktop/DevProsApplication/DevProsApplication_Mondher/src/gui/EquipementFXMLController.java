/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Equipement;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import tools.StaticValue;
import tools.Upload;

/**
 * FXML Controller class
 *
 * @author Mondh
 */
public class EquipementFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TextField  name;
    @FXML
    private TextField marque;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private TextField image;
//    private File file;
//    private Image image1;
//    String pic;
    
    @FXML    
    private TextField categorieeq;
   
  
    
    

    
   
       
    void ajouterEquipement(ActionEvent event) 
    {

        Equipement e;
      Services.ServiceEquipement se = new  Services.ServiceEquipement();
       
        e= new Equipement(1,name.getText(), marque.getText(), description.getText(),Integer.parseInt(categorieeq.getText()),image.getText(),Float.valueOf(prix.getText()));
         
     
        
        
    se.ajoutEquipement(e);
    
    Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
    alert.setContentText("Equipement ajouté");
     
  
    } 
    
    
//    @FXML
//  private void addImg(ActionEvent event) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//            file= fileChooser.showOpenDialog(null);
//             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//
//            
//            pic=(file.toURI().toString());
//         //  pic=new Upload().upload(file,"uimg");
//           pic=new Upload().upload(file,"");
//            System.out.println(pic);
//   //   image= new Image("http://localhost/uimg/"+pic);
//            txtimg.setText(pic);
//           image1= new Image("http://127.0.0.1:3306/devpros/img/"+pic);
//  }

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
