/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.authentication.AuthenticationService;
import Domain.DomainFacade;
import Domain.profile.ProfileService;
import Enums.RequestType;
import Enums.ServiceType;
import Models.Profile;
import Models.Request;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class BuddyListPageController extends PageHandler implements Initializable {

    
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView Menubtn;
    @FXML
    private AnchorPane tilePane;
    @FXML
    private VBox Vboks;
    private AuthenticationService authenticationService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        
      //TODO new to get the curret Profile
//        for (int buddyID : currentProfile.getBuddyIds()) {
//            Profile buddy = profileService.getProfile(buddyID);
//            ProfileItem(buddy);
//                    
//        }
        
    }    

    @FXML
    private void ActionOnMouseClick(MouseEvent event) {
        if(event.getSource()==Menubtn){
            changeFxml(pane, "FXML/Menu.fxml");
        }
    }
         private void ProfileItem(Profile profile){
          DataSender dateSender = DataSender.getInstance();
          dateSender.setProfile(profile);
          AnchorPane g = new AnchorPane();
          changeFxml(g, "FXML/UserShow.fxml");  
          Vboks.getChildren().add(g);
          Label l = new Label();           
          Vboks.getChildren().add(l);
     }   
           
}
