/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author perte
 */
public class ProfileController implements Initializable {

    private SubScene subSceneMenu;
    @FXML
    private Circle circleProfilePic;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldCountry;
    @FXML
    private TextField fieldGender;
    @FXML
    private TextField fieldGym;
    @FXML
    private TextField fieldCity;
    @FXML
    private Button btnSaveProfile;
    @FXML
    private HBox hboxen;
    @FXML
    private Pane subpane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            //Should make the subscene to the manu subscene
            
     
         
           
      
    }

    
    @FXML
    private void btnSaveProfileHandler(ActionEvent event) {
        if(btnSaveProfile.getText().equals("Save profile info")){
            saveProfile();
        } else{
            modifyProfile();
        }
        
    }
    
    private void modifyProfile(){
        fieldCity.setEditable(true);
        fieldCountry.setEditable(true);
        fieldGender.setEditable(true);
        fieldGym.setEditable(true);
        fieldName.setEditable(true);
        btnSaveProfile.setText("Save profile info");
    }
    
    
    //should send data to server
    private void saveProfile(){
        fieldCity.setEditable(false);
        fieldCountry.setEditable(false);
        fieldGender.setEditable(false);
        fieldGym.setEditable(false);
        fieldName.setEditable(false);
        btnSaveProfile.setText("Modify profile info");
        //save data to server TODO
    }
    private void Menu(){
         try {
            
            Pane subScene = FXMLLoader.load(getClass().getResource("FXML/PupUpMenu/SidebarMenu.fxml"));
            subpane.getChildren().setAll(subScene);
            } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void MouseEcited(MouseEvent event) {
        subpane.getChildren().clear();
    }

    @FXML
    private void MouseEntered(MouseEvent event) {
       Menu();
    }

   

  
}
