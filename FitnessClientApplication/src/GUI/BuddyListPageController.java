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
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class BuddyListPageController extends PageHandler implements Initializable {

    
    private AnchorPane pane;
    private ImageView Menubtn;
    private VBox Vboks;
    private AuthenticationService authenticationService;
    @FXML
    private ListView<?> ListViewBuddies;
    @FXML
    private TextArea messageField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        
        createAnimationForMessageField();
        
      //TODO new to get the curret Profile
//        for (int buddyID : currentProfile.getBuddyIds()) {
//            Profile buddy = profileService.getProfile(buddyID);
//            ProfileItem(buddy);
//                    
//        }
        
    }    
    
    private void createAnimationForMessageField () {
        Timeline messageFocusTimeline = new Timeline();
        KeyValue kv = new KeyValue(messageField.prefHeightProperty(), 100d, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        messageFocusTimeline.getKeyFrames().add(kf);
        
        Timeline messageLostFocusTimeline = new Timeline();
        KeyValue kv1 = new KeyValue(messageField.prefHeightProperty(), 50d, Interpolator.LINEAR);
        KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1);
        messageLostFocusTimeline.getKeyFrames().add(kf1);
        
        messageField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    messageFocusTimeline.play();
                }
                else {
                    messageLostFocusTimeline.play();
                }
            }
        });
    }

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
