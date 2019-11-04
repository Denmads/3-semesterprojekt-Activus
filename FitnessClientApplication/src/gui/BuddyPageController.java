package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import Models.Message;
import Models.Profile;
import domain.authentication.AuthenticationService;
import domain.serviceInterfaces.IChatService;
import domain.serviceInterfaces.IProfileService;
import gui.cellsControllers.ProfileCellController;
import gui.cellsControllers.cellAllExerciseControler;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
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
    private ListView<Profile> ListViewBuddies;
    @FXML
    private TextArea messageField;
    
    private Profile buddy;
    private ObservableList<Profile> listBuddy;
    
    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        
        createAnimationForMessageField();
        
        
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
    
    private boolean sendMessage(){
        
        try {
            String text = messageField.getText();
            int reciverId = buddy.getProfileId();
            int senderId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            Message message = new Message(senderId, reciverId, text);
            domainFacade.<IChatService>getService(ServiceType.CHAT).sendMessage(message);
            return true;
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private void loadBuddys(){
        listBuddy = FXCollections.observableArrayList();
        //TODO new to get the curret Profile
        
        
        ListViewBuddies.setItems(listBuddy);
        ListViewBuddies.setCellFactory((ListView<Profile> view) -> {
            return new ProfileCellController(domainFacade);
        });
        ListViewBuddies.refresh();
    }
}