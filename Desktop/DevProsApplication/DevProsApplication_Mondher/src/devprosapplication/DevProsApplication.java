/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devprosapplication;

import Services.ServiceEquipement;
import entities.Equipement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mondh
 */
public class DevProsApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            
        
       ServiceEquipement ps = new ServiceEquipement();
        try {
            Equipement e1= new Equipement(1,"nom","marquuuu","desccc",3,"ppp.png",(float) 200.5);
            ps.supprimerEquipement(30);
            ps.supprimerEquipement(32);
             ps.supprimerEquipement(20);
           ps.ajoutEquipement(e1);
           ps.updateEquipement(e1, 0);
            
            System.out.println("personne supprimé");
                System.out.println(ps.afficher().toString());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
