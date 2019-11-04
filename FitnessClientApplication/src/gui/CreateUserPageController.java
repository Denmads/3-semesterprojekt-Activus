/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import domain.DomainFacade;
import domain.authentication.NewAccountInfo;
import domain.serviceInterfaces.IAuthenticationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author madsh
 */
public class CreateUserPageController implements Initializable {

    private DomainFacade domainFacade;
    private Stage stage;
    
    @FXML
    private Label errorTxt;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordReField;
    
    private ArrayList<TextField> fields;
    @FXML
    private VBox inputBox;
    @FXML
    private Label successLbl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fields = new ArrayList<>();
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(usernameField);
        fields.add(passwordField);
        fields.add(passwordReField);
    }    
    
    
    public void setFacade (DomainFacade facade) {
        domainFacade = facade;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    

    @FXML
    private void createNewUser(ActionEvent event) {
        for (TextField tf : fields) {
            if (tf.getText().isEmpty()) {
                setErrorText("All fields must be entered!");
                return;
            }
        }
        
        if (passwordField.getText().equals(passwordReField.getText())) {
            NewAccountInfo newInfo = new NewAccountInfo(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText());
            
            try {
                String errors = domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).createAccount(newInfo);
                
                if (errors.equals("")) {
                    inputBox.setEffect(new GaussianBlur(5));
                    inputBox.setDisable(true);
                    successLbl.setVisible(true);
                    
                    Timer timer = new Timer(true);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                stage.close();
                            });
                        }
                    }, 2000);
                }
                else {
                    setErrorText(errors);
                }
            } catch (ServiceNotFoundException | ClassCastException ex) {
                setErrorText(ex.getMessage());
                Logger.getLogger(CreateUserPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            setErrorText("Passwords do not match!");
        }
    }
    
    private void setErrorText (String text) {
        errorTxt.setText(text);
        errorTxt.setVisible(true);
    }
}
