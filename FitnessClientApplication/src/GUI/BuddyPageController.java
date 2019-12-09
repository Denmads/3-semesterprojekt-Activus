package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import gui.cellsControllers.MessageCellController;
import models.Message;
import models.Profile;
import domain.DomainFacade;
import domain.authentication.AuthenticationService;
import domain.serviceInterfaces.IChatService;
import domain.serviceInterfaces.IProfileService;
import gui.cellsControllers.ProfileCellController;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private ListView<Profile> listViewBuddies;
    private ListView<Message> listViewMessage;
    @FXML
    private TextArea messageField;
    
    private Profile buddy;
    private ObservableList<Profile> listBuddy;
    private ObservableList<Message> listMessage;
    @FXML
    private ListView<?> ListViewBuddies;
    @FXML
    private ListView<?> ListView;
    
    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      

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
    
    private void loadBuddys(){
        listBuddy = FXCollections.observableArrayList();
        int profileId;
        try {
            profileId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            if (domainFacade.<IProfileService>getService(ServiceType.PROFILE).getAllBuddys(profileId) == null){
                
            } else {
                listBuddy = (ObservableList<Profile>) domainFacade.<IProfileService>getService(ServiceType.PROFILE).getAllBuddys(profileId);
                System.out.println(listBuddy.toString());
            }
            
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listViewBuddies.setItems(listBuddy);
        listViewBuddies.setCellFactory((ListView<Profile> view) -> {
            return new ProfileCellController(domainFacade);
        });
        listViewBuddies.refresh();
        
    }

    @Override
    public void onContentInitialize() {
        createAnimationForMessageField();
        loadBuddys();  // to load buddys from the database
    }
    
    private void loadMessage(){
        listBuddy = listViewBuddies.getItems();
        Profile p = listBuddy.get(listViewBuddies.getEditingIndex());
        int buddyId = p.getProfileId();
        int profileId;
        try {        
            profileId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            listMessage = (ObservableList<Message>) domainFacade.<IChatService>getService(ServiceType.CHAT).getChatHistory(profileId, buddyId);
            listViewMessage.setCellFactory((ListView<Message> view) -> {
                return new MessageCellController(domainFacade);
            });
            
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listViewBuddies.refresh();
    }
    
    @FXML
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

    @FXML
    private void action(){
        buddy = listViewBuddies.getSelectionModel().getSelectedItem();
        if (checkBuddy()){
            loadMessage();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation for buddy");
            alert.setHeaderText("Confirmation for buddy request");
            alert.setContentText("Do you wanna accept the buddy request from the user?");

            ButtonType buttonTypeOne = new ButtonType("Accept");
            ButtonType buttonTypeTwo = new ButtonType("Deny");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                try {
                    domainFacade.<IProfileService>getService(ServiceType.PROFILE).acceptBuddyRequest(buddy.getProfileId());
                } catch (ServiceNotFoundException ex) {
                    Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassCastException ex) {
                    Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                alert.close();
            } else if (result.get() == buttonTypeTwo) {
                try {
                    domainFacade.<IProfileService>getService(ServiceType.PROFILE).dennyBuddyRequest(buddy.getProfileId());
                } catch (ServiceNotFoundException ex) {
                    Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassCastException ex) {
                    Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                alert.close();
            } else {
                alert.close();
            }
        }
        
    }
    
    
    private boolean checkBuddy(){
        try {
            return domainFacade.<IProfileService>getService(ServiceType.PROFILE).isBuddy(buddy.getProfileId());
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
