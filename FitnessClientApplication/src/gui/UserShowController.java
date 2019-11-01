/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Profile;
import Models.Request;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class UserShowController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private Label Username;
    @FXML
    private Label gym;
    @FXML
    private Label cuntry;
    @FXML
    private Label city;

    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setUserPane(AnchorPane UserPane) {
        this.UserPane = UserPane;
    }

    public void setUsername(Label Username) {
        this.Username = Username;
    }

    public void setGym(Label gym) {
        this.gym = gym;
    }

    public void setCuntry(Label cuntry) {
        this.cuntry = cuntry;
    }

    public void setCity(Label city) {
        this.city = city;
    }
    
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        DataSender dateSender = DataSender.getInstance();
        setData(dateSender.getProfile());
        
    }    
    public void setData(Profile profile){
        Username.setText(profile.getUsername());
        gym.setText(profile.getGym());
        cuntry.setText(profile.getCountry());
        city.setText(profile.getCity());
    }
    
   
}
