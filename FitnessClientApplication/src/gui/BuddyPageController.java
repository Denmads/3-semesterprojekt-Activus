package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import Models.Message;
import Models.Profile;
import domain.DomainFacade;
import domain.authentication.AuthenticationService;
import domain.serviceInterfaces.IChatService;
import domain.serviceInterfaces.IProfileService;
import gui.cellsControllers.ProfileCellController;
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
    @FXML
    private ListView<Profile> listViewBuddies;
    @FXML
    private ListView<Message> listViewMessage;
    @FXML
    private TextArea messageField;
    
    private Profile buddy;
    private ObservableList<Profile> listBuddy;
    private ObservableList<Message> listMessage;
    
    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
//        createAnimationForMessageField();
//        loadBuddys(); //cant get domainfacade in initialize
        
        
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
        int profileId;
        try {
            profileId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            listBuddy = (ObservableList<Profile>) domainFacade.<IProfileService>getService(ServiceType.PROFILE).getAllBuddys(profileId);
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
        //loadBuddys();  // to load buddys from the database
        lb(); // load some buddys in to 8.11
    }
    
    private void lb(){
        listBuddy = FXCollections.observableArrayList();
        
        Profile p = new Profile(0);
        p.setAge(15);
        p.setCity("Mollerub");
        p.setGender("Male");
        p.setUsername("Bent");
        p.setLastName("Mordrup");
        p.setGym("The world");
        
        listBuddy.add(p);
        
        Profile p2 = new Profile(1);
        p2.setAge(17);
        p2.setUsername("fald");
        p2.setLastName("Mordrup");
        p2.setGym("The world");
        listBuddy.add(p2);
        
        Profile p3 = new Profile(2);
        p3.setAge(16);
        p3.setGender("Male");
        p3.setUsername("Kurt");
        p3.setLastName("Mordrup");
        p3.setGym("The world");
        
        listBuddy.add(p3);
        
        Profile p4 = new Profile(3);
        p4.setAge(30);
        p4.setUsername("Hald");
        p4.setLastName("Mordrup");
        p4.setGym("The world");
        listBuddy.add(p4);
        
        Profile p5 = new Profile(4);
        p5.setAge(19);
        p5.setGender("Male");
        p5.setUsername("Mads");
        p5.setLastName("Mordrup");
        p5.setGym("The world");
        
        listBuddy.add(p5);
        
        Profile p6 = new Profile(5);
        p6.setAge(23);
        p6.setUsername("Jarl");
        p6.setLastName("Mordrup");
        p6.setGym("The world");
        listBuddy.add(p6);
        
        listViewBuddies.setItems(listBuddy);
        listViewBuddies.setCellFactory((ListView<Profile> view) -> {
            return new ProfileCellController(domainFacade);
        });
        listViewBuddies.refresh();
    }
    
    private void loadMessage(){
        listBuddy = listViewBuddies.getItems();
        Profile p = listBuddy.get(listViewBuddies.getEditingIndex());
        int buddyId = p.getProfileId();
        int profileId;
        try {        
            profileId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            listMessage = (ObservableList<Message>) domainFacade.<IChatService>getService(ServiceType.CHAT).getChatHistory(profileId);
//            listViewMessage.setCellFactory((ListView<Message> view) -> {
//                return new MessageCellController(domainFacade);
//            });
            
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(BuddyPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listViewBuddies.refresh();
    }
}