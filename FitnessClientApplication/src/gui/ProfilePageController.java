/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import Models.Profile;
import domain.profile.ProfileService;
import domain.serviceInterfaces.IAuthenticationService;
import domain.serviceInterfaces.IProfileService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author perte
 */
public class ProfilePageController extends ContentPageController {

    @FXML
    private Circle circleProfilePic;
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
    private TextField fieldFirstName;
    @FXML
    private TextField fieldLastName;
    @FXML
    private TextField fieldAge;
    @FXML
    private TextField fieldSport;

    private TextField[] textFields;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFields = new TextField[]{fieldCountry, fieldGender, fieldGym, fieldCity, fieldFirstName, fieldLastName, fieldAge, fieldSport};
    }

    @FXML
    private void btnSaveProfileHandler(ActionEvent event) {
        if (btnSaveProfile.getText().equals("Save profile info")) {
            saveProfile();
        } else {
            modifyProfile();
        }
    }

    private void modifyProfile() {
        for (TextField tf : textFields) {
            tf.setEditable(true);
        }
        btnSaveProfile.setText("Save profile info");
    }

    //should send data to server
    private void saveProfile() {
        try {
            for (TextField tf : textFields) {
                tf.setEditable(false);
            }
            btnSaveProfile.setText("Modify profile info");
            int id = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            Profile temp = new Profile(id);

            temp.setGym(fieldGym.getText());
            temp.setCity(fieldCity.getText());
            temp.setAge(Integer.parseInt(fieldAge.getText()));
            temp.setFirstName(fieldFirstName.getText());
            temp.setLastName(fieldLastName.getText());
            temp.setGender(fieldGender.getText());
            temp.setCountry(fieldCountry.getText());
            temp.setSport(fieldSport.getText());
            boolean updated = domainFacade.<IProfileService>getService(ServiceType.PROFILE).updateProfile(temp);
            //save data to server TODO
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(ProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
