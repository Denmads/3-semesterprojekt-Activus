/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import models.Profile;
import domain.DomainFacade;
import domain.serviceInterfaces.IAuthenticationService;
import domain.serviceInterfaces.IProfileService;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    private TextField[] textFields;
    @FXML
    private Button btnDeleteAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFields = new TextField[]{fieldCountry, fieldGender, fieldGym, fieldCity, fieldFirstName, fieldLastName, fieldAge};
    }

    @Override
    public void setDomainFacade(DomainFacade facade) {
        try {
            super.setDomainFacade(facade);
            Profile currentProfile = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile();
            fieldAge.setText(currentProfile.getAge() + "");
            fieldCity.setText(currentProfile.getCity());
            fieldCountry.setText(currentProfile.getCountry());
            fieldFirstName.setText(currentProfile.getFirstName());
            fieldGender.setText(currentProfile.getGender());
            fieldGym.setText(currentProfile.getGym());
            fieldLastName.setText(currentProfile.getLastName());
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(ProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        btnDeleteAccount.setVisible(true);
        btnDeleteAccount.setDisable(false);
        btnSaveProfile.setText("Save profile info");
    }

    //should send data to server
    private void saveProfile() {
        try {
            for (TextField tf : textFields) {
                tf.setEditable(false);
            }
            btnDeleteAccount.setVisible(false);
            btnDeleteAccount.setDisable(true);
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
            boolean updated = domainFacade.<IProfileService>getService(ServiceType.PROFILE).updateProfile(temp);
            //save data to server TODO
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(ProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteAccountHandler(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete account?");
        alert.setHeaderText("Do you really want to delete your account?");
        alert.setContentText("You will be logged out and data will be deleted. Are you okay with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            try {
                int id = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
                boolean deleted = domainFacade.<IProfileService>getService(ServiceType.PROFILE).deleteAccount(id);
                if (deleted){
                    domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).logout();
                }
            } catch (ServiceNotFoundException | ClassCastException ex) {
                Logger.getLogger(ProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
    }

}
