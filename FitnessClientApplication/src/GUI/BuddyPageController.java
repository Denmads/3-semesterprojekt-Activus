/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.authentication.AuthenticationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class BuddyPageController extends ContentPageController {

    
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
           
}
